package Conectividad;

import Logica.Cuenta;
import Logica.Fachada;
import Persistencia.FachadaBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.Iterator;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.table.DefaultTableModel;

import com.icegreen.greenmail.user.UserException;
import com.icegreen.greenmail.user.UserManager;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import com.icegreen.greenmail.util.ServerSetupTest;
import com.sun.mail.smtp.SMTPTransport;
import com.dumbster.smtp.SmtpMessage;
import com.icegreen.greenmail.Managers;
import com.icegreen.greenmail.store.FolderException;
import com.icegreen.greenmail.store.MailFolder;
import com.icegreen.greenmail.user.GreenMailUser;
import com.icegreen.greenmail.smtp.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SmtpServ extends Thread{

    public static GreenMail mailServer;
    private Thread t;
    private static final String USER_PASSWORD = "123";
    private static final String USER_PASSWORD2 = "123";
    private static final String USER_NAME = "aloustau";
    private static final String USER_NAME2 = "jgardio";
    private static final String EMAIL_USER_ADDRESS = "aloustau@inet.com";
    private static final String EMAIL_USER_ADDRESS2 = "jgardio@ipa.com";
    private static final String EMAIL_TO = "someone@localhost.com";
    private static final String EMAIL_SUBJECT = "Test E-Mail";
    private static final String EMAIL_TEXT = "This is a test e-mail.";
    private static final String LOCALHOST = "127.0.0.1";
    private ArrayList<Cuenta> setCuentas;
    private static SmtpServ instancia;
    private int cantMails;

    private int stop = 0;
	 
    //Esta clase utiliza el patron Singleton
    public static SmtpServ getInstancia() throws SQLException{
            if (instancia == null)
                    instancia = new SmtpServ();

            return instancia;
    }

    /*public SmtpServ() throws SQLException{
            //setUp();
    }*/
	 
    public void setUp() throws SQLException, IOException {
        ServerSetup setup = new ServerSetup(3025, "localhost", "smtp");
        mailServer = new GreenMail(setup);
        if(this.stop == 0){
                 mailServer.start();
                cargaCuenta();
        }else{
            mailServer.reset();
            cargaCuenta();
            this.stop = 0;
        }
    }
	 
    public void tearDown() {
        mailServer.stop();
        this.stop = 1;
    }
	   
    //Método que carga los usuarios en la API para que puedan autenticarse en el servidor
    public void cargaCuenta() throws SQLException, IOException{
                
        String nom_cuenta, nom_dom, email_cuenta, pass, cedula;
        java.sql.ResultSet rs = FachadaBD.getInstancia().get_cuentas_BD();

        while(rs.next()){
            nom_cuenta = rs.getString("nom_cuenta");
            nom_dom = rs.getString("nom_dominio");
            cedula = rs.getString("cedula_usuario");

            pass = FachadaBD.getInstancia().obtengo_pass_usuario(cedula);

            email_cuenta = nom_cuenta + "@" + nom_dom;
            mailServer.setUser(email_cuenta, nom_cuenta, pass);
            //System.out.println("cargar_cuentas " + email_cuenta + " " + nom_cuenta + " " + pass);
        }
    } 
	    
    public void setMailsBD() throws IOException, MessagingException, UserException, InterruptedException, SQLException {
        String NomUsu, NomRecp, DomUsu, DomRecp;
        int UserIdE, UserIdR;   	
        Random rnd = new Random();

        MimeMessage[] messages = mailServer.getReceivedMessages();
        MimeMessage m = messages[0];

        //System.out.println("SmtpServ setMailsBD: " + m);
 
    } 
	    
    public void getMailsBD() throws SQLException, AddressException, MessagingException, UserException, IOException {
 
        String mail_from, mail_to, mail_subject, mail_text;

        String nom_cuenta_emisor, nom_dom_emisor, nom_cuenta_receptor, nom_dom_receptor /*, ¿pass?*/;
        String cedula_receptor, pass_receptor;

        java.sql.ResultSet rs = FachadaBD.getInstancia().get_correos_BD();
        GreenMailUser Usuario = null;
	    	
        while(rs.next()){
	    		
            nom_cuenta_emisor = rs.getString("nom_usuario_emisor");
            nom_dom_emisor = rs.getString("nom_dominio_emisor");
            nom_cuenta_receptor = rs.getString("nom_usuario_receptor");
            nom_dom_receptor = rs.getString("nom_dominio_receptor");
            mail_from = nom_cuenta_emisor+"@"+nom_dom_emisor;
            mail_to = nom_cuenta_receptor+"@"+nom_dom_receptor;
            mail_subject = rs.getString("asunto");
            mail_text = rs.getString("texto");

            //Crea un mensaje con JavaMail
            MimeMessage message = new MimeMessage((Session) null);
            message.setFrom(new InternetAddress(mail_from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail_to));
            message.setSubject(mail_subject);
            message.setText(mail_text);

            cedula_receptor = rs.getString("cedula_usuario_receptor");
            pass_receptor = FachadaBD.getInstancia().obtengo_pass_usuario(cedula_receptor);
            Usuario = mailServer.setUser(mail_to, nom_dom_receptor, cedula_receptor);

            //Almacenamos los mensaje en memoria para el INBOX de cada usuario
            Usuario.deliver(message);
        }
	    	
        MimeMessage[] messages = mailServer.getReceivedMessages();
        this.cantMails = messages.length;
    }

    @Override
    public void run(){
        //String NomUsu, NomRecp, DomUsu, DomRecp;
        String nom_cuenta_emisor = null, nom_dom_emisor = null, nom_cuenta_receptor = null, nom_dom_receptor = null;
        int /*UserIdE, UserIdR,*/ cont/*, aux*/;
        String cedula_emisor = null, cedula_receptor = null;
        Random rnd = new Random();
        MimeMessage[] messages;
        cont = 0;
        while(true){    //System.out.println("aaaaa smtp");
            messages = mailServer.getReceivedMessages();

            if (cont < messages.length){
                while(cont < messages.length){
                    MimeMessage m = messages[cont];

                    try {
                        nom_cuenta_receptor = m.getHeader("To")[0].toString().substring(0, m.getHeader("To")[0].toString().indexOf("@"));
                        nom_dom_receptor = m.getHeader("To")[0].toString().substring(m.getHeader("To")[0].toString().indexOf("@")+1);
                        nom_cuenta_emisor = m.getFrom()[0].toString().substring(0, m.getFrom()[0].toString().indexOf("@"));
                        nom_dom_emisor = m.getFrom()[0].toString().substring(m.getFrom()[0].toString().indexOf("@")+1);

                        //UserIdE = FCLogica.IdUsuario(NomUsu, DomUsu);
                        cedula_emisor = FachadaBD.getInstancia().obtengo_cedula_usuario(nom_cuenta_emisor, nom_dom_emisor);
                        //UserIdR = FCLogica.IdUsuario(NomRecp, DomRecp);
                        //System.out.println("smtpserv " + nom_cuenta_emisor + " " + nom_dom_emisor + " " + nom_cuenta_receptor + " " + nom_dom_receptor);
                        cedula_receptor = FachadaBD.getInstancia().obtengo_cedula_usuario(nom_cuenta_receptor, nom_dom_receptor);

                        //System.out.println("Nuevo e-mail de " + m.getFrom()[0].toString() + " | " + "Para: " +  m.getHeader("To")[0].toString() + " | " + "Asunto: " +  m.getSubject());
                        //System.out.println("SMTPSERV.run " + m.toString());
                        //System.out.println("Nuevo e-mail de " + m.getFrom()[0].toString() + " | " + "Para: " +  m.getHeader("To")[0].toString() + " | " + "Asunto: " +  m.getSubject());

                        /*SI ES UNA RESPUESTA*/
                        if(m.getSubject().toString().indexOf("%") != -1){
                            int id_conversacion = Integer.valueOf(m.getSubject().toString().substring(0, m.getSubject().toString().indexOf("%")));
                            String asunto = m.getSubject().toString().substring(m.getSubject().toString().indexOf("%") + 1);
                            
                            Fachada.getInstancia().alta_correo_emisor(nom_cuenta_emisor, nom_dom_emisor, cedula_emisor, nom_cuenta_receptor, nom_dom_receptor, cedula_receptor, asunto, m.getContent().toString(), id_conversacion);
                        } else {
                            Fachada.getInstancia().alta_correo_emisor(nom_cuenta_emisor, nom_dom_emisor, cedula_emisor, nom_cuenta_receptor, nom_dom_receptor, cedula_receptor, m.getSubject(), m.getContent().toString(), (int)(rnd.nextDouble() * 10000));
                        }

                        cont++;
                    }catch(IOException | SQLException | MessagingException e){
                        //System.out.println("asd smtp");
                        e.printStackTrace();
                        //System.out.println("ddd smtp");
                    }
                }

            }

            try {
                mailServer.purgeEmailFromAllMailboxes();
                Thread.sleep(5000);
            }catch (InterruptedException | FolderException e){
                //System.out.println("EAEAEA smtp");
                e.printStackTrace();
                //System.out.println("zxzxzx smtp");
            }
            cont = 0;
        }
    }
	    
}
