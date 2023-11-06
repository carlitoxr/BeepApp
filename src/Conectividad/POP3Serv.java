package Conectividad;
import static Conectividad.SmtpServ.*;

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
import com.icegreen.greenmail.util.ServerSetupTest;
import com.sun.mail.smtp.SMTPTransport;
import com.dumbster.smtp.SmtpMessage;
import com.icegreen.greenmail.Managers;
import com.icegreen.greenmail.store.FolderException;
import com.icegreen.greenmail.store.MailFolder;
import com.icegreen.greenmail.user.GreenMailUser;
import com.sun.mail.imap.IMAPMessage;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

public class POP3Serv {

    private static POP3Serv instancia;
    private GreenMail POPServer;
    private int stop = 0;

    //Esta clase utiliza el patron Singleton
    public static POP3Serv getInstancia() throws SQLException{
            if (instancia == null)
                    instancia = new POP3Serv();
            return instancia;
    }

    /*public POP3Serv() throws SQLException{
            //setUp();
    }*/
	
    public void setUp() throws SQLException, IOException {
    	POPServer = new GreenMail(ServerSetupTest.POP3);
        if(this.stop == 0){
            POPServer.start();
            cargar_cuentas();
        }else{
            POPServer.reset();
            cargar_cuentas();
            this.stop = 0;
        }
    }
 
    public void tearDown() {
    	POPServer.stop();
    	this.stop = 1;
    }
    
    public void cargar_cuentas() throws IOException, FileNotFoundException, SQLException{
        String nom_cuenta, nom_dom, email_cuenta, pass, cedula;
        java.sql.ResultSet rs = FachadaBD.getInstancia().get_cuentas_BD();
        
        while(rs.next()){
            nom_cuenta = rs.getString("nom_cuenta");
            nom_dom = rs.getString("nom_dominio");
            cedula = rs.getString("cedula_usuario");
            
            pass = FachadaBD.getInstancia().obtengo_pass_usuario(cedula);
            
            email_cuenta = nom_cuenta + "@" + nom_dom;
            POPServer.setUser(email_cuenta, nom_cuenta, pass);
            
            System.out.println("cargar_cuentas " + email_cuenta + " " + nom_cuenta + " " + pass);
        }
    }
    
    public void getMailsBDUsu(String usu, String dom) throws FolderException, Exception {
    	String mail_from, mail_to, mail_subject, mail_text;
        String nom_cuenta_emisor, nom_dominio_emisor, nom_cuenta_receptor, nom_dominio_receptor, fecha;
        String cedula_receptor, password_receptor;
        String cedula_emisor;
        
        int enviado = 1; //1 true
        int se_envio = 0; //0 false
        int id_conversacion;
        
        java.sql.ResultSet rs = FachadaBD.getInstancia().get_correos_cuenta_receptor_BD(usu, dom); //Esto se debería hacer desde Logica.Fachada
        GreenMailUser usuario;
        Managers managers = new Managers();
        
        //System.out.println("getMailsBDUsu " + usu + " " + dom);
        
        while(rs.next()){
            //System.out.println("getMailsBDUsu " + usu + " " + dom);
            nom_cuenta_emisor = rs.getString("nom_usuario_emisor");
            nom_dominio_emisor = rs.getString("nom_dominio_emisor");
            nom_cuenta_receptor = rs.getString("nom_usuario_receptor");
            nom_dominio_receptor = rs.getString("nom_dominio_receptor");
            id_conversacion = rs.getInt("id_conversacion");
            
            mail_from = nom_cuenta_emisor + "@" + nom_dominio_emisor;
            mail_to = nom_cuenta_receptor + "@" + nom_dominio_receptor;
            mail_subject = id_conversacion + "%" + rs.getString("asunto");
            mail_text = rs.getString("texto");
            fecha = rs.getString("fecha");
            se_envio = rs.getInt("enviado");
            
            //System.out.println("getMailsBDUsu " + mail_to + " " + usu); 
            //System.out.println("getMailsBDUsu " + mail_from + " " + mail_to + " " + mail_subject + " " + mail_text);
            
            //Creo mensaje con JavaMail
            MimeMessage message = new MimeMessage((Session) null); System.out.println("getMailsBDUsu " + mail_from + " " + mail_to + " " + mail_subject + " " + mail_text);

            message.setFrom(new InternetAddress(mail_from)); //System.out.println("getMailsBDUsu " + mail_from + " " + mail_to + " " + mail_subject + " " + mail_text);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail_to)); //System.out.println("getMailsBDUsu " + mail_from + " " + mail_to + " " + mail_subject + " " + mail_text);
            message.setSubject(mail_subject); //System.out.println("getMailsBDUsu " + mail_from + " " + mail_to + " " + mail_subject + " " + mail_text);
            message.setText(mail_text); //System.out.println("getMailsBDUsu " + mail_from + " " + mail_to + " " + mail_subject + " " + mail_text);
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //System.out.println("con sdf " + sdf.parse(fecha));
            message.setSentDate(sdf.parse(fecha));
            
            cedula_emisor = rs.getString("cedula_usuario_emisor");
            cedula_receptor = rs.getString("cedula_usuario_receptor");
            password_receptor = FachadaBD.getInstancia().obtengo_pass_usuario(cedula_receptor);
            
            //System.out.println("getMailsBDUsu " + mail_to + " " + usu + " " + password_receptor);
            
            if(nom_cuenta_receptor.equals(usu)){
                mail_to = nom_cuenta_receptor + "@" + nom_dominio_receptor;
                password_receptor = FachadaBD.getInstancia().obtengo_pass_usuario(cedula_receptor);
            }
            else if(nom_cuenta_emisor.equals(usu)){
                mail_to = nom_cuenta_emisor + "@" + nom_dominio_emisor;
                password_receptor = FachadaBD.getInstancia().obtengo_pass_usuario(cedula_emisor);
            }
            
            usuario = POPServer.setUser(mail_to, usu, password_receptor);
            
            if(se_envio == 0){
                usuario.deliver(message);
                FachadaBD.getInstancia().marcar_enviado_correo_fecha(fecha, 0);
            }
            //System.out.println("HOLAPOP3serv");
        }
    }
    
    public void BorraMailDeBoxes() throws FolderException{
    	POPServer.purgeEmailFromAllMailboxes();
    }
}
