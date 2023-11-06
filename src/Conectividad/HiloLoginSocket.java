package Conectividad;

//import static grafica.FrmMuestraCuentas.id;

import Logica.Cuenta;
import java.io.*;
import java.net.*;
import java.util.Properties;
import java.sql.SQLException;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.icegreen.greenmail.Managers;
import com.icegreen.greenmail.store.FolderException;
import com.icegreen.greenmail.user.GreenMailUser;
import com.icegreen.greenmail.user.UserException;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetupTest;

import Logica.Fachada;


import Persistencia.FachadaBD;

public class HiloLoginSocket extends Thread {
	
    private Socket socket;
    private DataOutputStream salida;
    private DataInputStream entrada;
    private int IdSession;

    private FachadaBD BD = FachadaBD.getInstancia();
	
	
    public HiloLoginSocket(Socket S, int Id){
        this.socket = S;
        this.IdSession = Id;
        try {
            entrada = new DataInputStream(socket.getInputStream());
            salida = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e){}
    }
	
	public void Desconectar(){
            try{
                socket.close();
            } catch (IOException e){}
	}
	
	@Override
	public void run(){
            String NomUsu, NomDom, Pass, P ,msg;
            String cedula, pass;

            try {
                cedula = entrada.readUTF();
                pass = entrada.readUTF();
                
                if( Fachada.getInstancia().existe_usuario(cedula) ){
                    if( Fachada.getInstancia().login_cliente(cedula, pass) )
                        msg = "Valid";
                    else
                        msg = "NoPass";
                }else{
                    msg = "NoCuenta";
                }

                salida.writeUTF(msg);

                if(msg.equals("Valid")){
                    cuentas_usuario(cedula);
                    msg = "Valido";
                    salida.writeUTF(msg);
                }
            } catch (Exception e){}

            Desconectar();
	}
    
    //Este método se utiliza para enviar al cliente las cuentas que tiene el usuario
    private void cuentas_usuario(String cedula) throws IOException, FileNotFoundException, SQLException{
        for(Cuenta cuenta : Fachada.getInstancia().obtengo_cuentas_de_un_usuario(cedula)){
            String cuenta_email = cuenta.getNom_cuenta() + "@" + cuenta.getDominio().getNom_dominio();
            salida.writeUTF(cuenta_email);
            System.out.println("" + cuenta_email);
        }
    }

}
