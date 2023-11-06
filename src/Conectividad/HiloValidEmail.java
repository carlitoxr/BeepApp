
package Conectividad;

import Logica.Fachada;
import Persistencia.FachadaBD;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloValidEmail extends Thread {
    private Socket socket;
    private DataOutputStream salida;
    private DataInputStream entrada;
    private int IdSession;

    private FachadaBD BD = FachadaBD.getInstancia();

    public HiloValidEmail(Socket S, int Id){
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
        
        String nom_usu, nom_dom;

        try {
            nom_dom = entrada.readUTF(); //System.out.println("HiloValidEmail dom" + nom_dom);
            if(Fachada.getInstancia().existe_dominio(nom_dom))
                msg = "dominio_valido";
            else
                msg = "dominio_invalido";
            salida.writeUTF(msg);
            
            if(msg.equals("dominio_valido")){
                nom_usu = entrada.readUTF(); //System.out.println("HiloValidEmail " + nom_usu + " " + nom_dom);
                if(Fachada.getInstancia().existe_cuenta_sin_cedula(nom_usu, nom_dom)){
                    msg = "email_valido";
                    //System.out.println("HiloValidEmail " + msg);
                }
                else{
                    msg = "email_invalido";
                    //System.out.println("HiloValidEmail " + msg);
                }
                salida.writeUTF(msg);
            }
        } catch (Exception e){}

        Desconectar();
    }
}
