package Conectividad;

import Logica.Correos;
import Logica.Cuenta;
import Logica.Cuentas;
import Logica.Dominio;
import Logica.Fachada;
import Logica.Usuario;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
import Persistencia.FachadaBD;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HiloSocket extends Thread{
	
    private Socket socket;
    private DataOutputStream salida;
    private DataInputStream entrada; 
    private int IdSession;
    private FachadaBD BD = FachadaBD.getInstancia();

    public  HiloSocket(Socket S, int Id){
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
        //Usuario:
        String cedula = null, password/*, nombre, apellido, celular*/;
        Usuario usuario;

        //Auxiliares para dominio
        //String dominio_nombre_dominio;
        int dominio_prioridad;

        //Auxiliares para cuenta
        String cuenta_nom_cuenta, cuenta_nom_dominio/*, cuenta_cedula_usuario*/;
        //String cuenta_email;
        int habilitada;

        //Auxiliares para usuario
        //String usuario_nombre, usuario_apellido, usuario_nom_comp;

        try {
            cedula = entrada.readUTF(); //En cte es salida
            password = entrada.readUTF(); //En cte es salida

            usuario = Fachada.getInstancia().obtener_usuario(cedula); //Obtengo el usuario de la colección

            salida.writeUTF(usuario.getNombre());
            salida.writeUTF(usuario.getApellido());
            salida.writeUTF(usuario.getCelular());

            salida.writeUTF("finUsuario"); //"finDom"

        } catch (IOException | SQLException e){	}


        try {
            //Cuentas
            for(Cuenta cuenta : Fachada.getInstancia().obtengo_cuentas_de_un_usuario(cedula)){
                cuenta_nom_cuenta = cuenta.getNom_cuenta();
                cuenta_nom_dominio = cuenta.getDominio().getNom_dominio();
                dominio_prioridad = cuenta.getDominio().getPrioridad();
                habilitada = cuenta.getHabilitada();

                salida.writeUTF(cuenta_nom_cuenta);
                salida.writeUTF(cuenta_nom_dominio);
                salida.writeUTF(String.valueOf(dominio_prioridad));
                salida.writeUTF(String.valueOf(habilitada));
            }

            salida.writeUTF("fin");
        } catch (IOException | SQLException e){	}

        Desconectar();
    }
}
