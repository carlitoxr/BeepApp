package Conectividad;

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

public class HiloUsuSocket extends Thread {

    private Socket socket;
    private DataOutputStream salida;
    private DataInputStream entrada;
    private int IdSession;

    public  HiloUsuSocket(Socket S, int Id){
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
        //System.out.println("HOLA???HILOUSU");
        String nom_cuenta, signal, msg = null, nom_dom;
        try {   //System.out.println("HOLA2 HILOUSU");
            POP3Serv POP3 = POP3Serv.getInstancia();
            nom_cuenta = entrada.readUTF(); //System.out.println("HiloUsu " + nom_cuenta);
            nom_dom = entrada.readUTF();    //System.out.println("HiloUsu " + nom_dom);
            POP3.getMailsBDUsu(nom_cuenta, nom_dom);
            msg = "Echo";
            salida.writeUTF(msg);   //System.out.println("HiloUsu " + msg);
            signal = entrada.readUTF();
            if(signal.equals("OK"))
                POP3.BorraMailDeBoxes();
            else
                System.out.println("Error en Conexion POP3");
        } catch (Exception e) {
        }
        Desconectar();
    }
}
