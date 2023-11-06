
package Persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public class FachadaBD {
    
    private static FachadaBD instancia;
    private Administrador admin;
    private Dominio dom;
    private Usuario usu;
    private Cuenta cuenta;
    private Correo correo;
    
    private FachadaBD(){
        admin = new Administrador();
        dom = new Dominio();
        usu = new Usuario();
        cuenta = new Cuenta();
    }

    public static FachadaBD getInstancia() {
        if(instancia == null)
            instancia = new FachadaBD();
        return instancia;
    }
    
    public boolean login_admin(String usr, String pass) throws IOException, FileNotFoundException, SQLException{
        return admin.login_admin(usr, pass);
    }
    
    /*
        ---DOMINIO---
    */
    
    //public void alta_dominio(Dominio d)throws IOException, FileNotFoundException, SQLException{
    public void alta_dominio(String nom_dominio, int prioridad)throws IOException, FileNotFoundException, SQLException{
//        dom = new Dominio();
        dom.alta_dominio(nom_dominio, prioridad);
    }
    
    public void baja_dominio(String nom_dom)throws IOException, FileNotFoundException, SQLException{
        dom.baja_dominio(nom_dom);
    }
    
    public boolean existe_dominio(String nom_dom)throws IOException, FileNotFoundException, SQLException{
        return dom.existe_dominio(nom_dom);
    }
    
    public ResultSet get_dominios()throws IOException, FileNotFoundException, SQLException{
        return dom.getDominiosBD();
    }
    
    public void modif_dominio_BD(String nom_dom, String prioridad_new) throws IOException, FileNotFoundException, SQLException{
        dom.modif_dominio_BD(nom_dom, prioridad_new);
    }
    
    /*
        ---DOMINIO---FIN---
    */
    
    /*
        ---USUARIO---
    */
    
    public void alta_usuario(String cedula, String password, String nombre, String apellido, String celular) throws IOException, FileNotFoundException, SQLException{
        usu.alta_usuario(cedula, password, nombre, apellido, celular);
    }
    
    public void baja_usuario(String cedula)throws IOException, FileNotFoundException, SQLException{
        usu.baja_usuario(cedula);
    }
    
    public boolean existe_usuario(String cedula)throws IOException, FileNotFoundException, SQLException{
        return usu.existe_usuario(cedula);
    }
    
    public ResultSet getUsuarioBD()throws IOException, FileNotFoundException, SQLException{
        return usu.getUsuarioBD();
    }
    
    public void modif_usuario_BD(String cedula, String nombre, String apellido, String celular) throws IOException, FileNotFoundException, SQLException{
        usu.modif_usuario_BD(cedula, nombre, apellido, celular);
    }
    
    public void reset_pass_BD(String cedula, String password) throws IOException, FileNotFoundException, SQLException{
        usu.reset_pass_BD(cedula, password);
    }
    
    public void cambiar_pass(String cedula, String new_pass) throws IOException, FileNotFoundException, SQLException{
        usu.cambiar_pass(cedula, new_pass);
    }
    
    /*
        ---USUARIO---FIN---
    */
    
    /*
        ---CUENTA---
    */
    
    public void alta_cuenta_BD(String nom_cuenta, String dominio, String usuario, int habilitada) throws IOException, FileNotFoundException, SQLException{
        cuenta.alta_cuenta(nom_cuenta, dominio, usuario, habilitada);
    }
    
    public void baja_cuenta_BD(String nom_cuenta, String dominio, String usuario) throws IOException, FileNotFoundException, SQLException{
        cuenta.baja_cuenta(nom_cuenta, dominio, usuario);
    }
    
    public void alta_logica_cuenta_BD(String nom_cuenta, String dominio, String usuario) throws IOException, FileNotFoundException, SQLException{
        cuenta.alta_logica_cuenta(nom_cuenta, dominio, usuario);
    }
    
    public boolean existe_cuenta(String nom_cuenta, String dominio, String usuario) throws IOException, FileNotFoundException, SQLException{
        return cuenta.existe_cuenta(nom_cuenta, dominio, usuario);
    }
    
    public boolean existe_cuenta_habilitada(String nom_cuenta, String dominio, String usuario) throws IOException, FileNotFoundException, SQLException{
        return cuenta.existe_cuenta_habilitada(nom_cuenta, dominio, usuario);
    }
    
    public boolean existe_cuenta_deshabilitada(String nom_cuenta, String dominio, String usuario) throws IOException, FileNotFoundException, SQLException{
        return cuenta.existe_cuenta_deshabilitada(nom_cuenta, dominio, usuario);
    }
    
    public ResultSet get_cuentas_BD()throws IOException, FileNotFoundException, SQLException{
        return cuenta.get_cuentas_BD();
    }
    
    public ResultSet get_cuenta_habilitada_BD()throws IOException, FileNotFoundException, SQLException{
        return cuenta.get_cuenta_habilitada_BD();
    }
    
    public ResultSet get_cuenta_deshabilitada_BD()throws IOException, FileNotFoundException, SQLException{
        return cuenta.get_cuenta_deshabilitada_BD();
    }
    
    public ResultSet obtengo_cuentas_de_un_usuario(String cedula)throws IOException, FileNotFoundException, SQLException{
        return cuenta.obtengo_cuentas_de_un_usuario(cedula);
    }
    
    public String obtengo_pass_usuario(String cedula_usuario)throws IOException, FileNotFoundException, SQLException{
        return cuenta.obtengo_pass_usuario(cedula_usuario);
    }
    
    public String obtengo_cedula_usuario(String nom_usu, String nom_dom)throws IOException, FileNotFoundException, SQLException{
        return cuenta.obtengo_cedula_usuario(nom_usu, nom_dom);
    }
    
    /*
        ---CUENTA---FIN---
    */
    
    /*
        ---CORREO---
    */ 
    public void alta_correo_BD(String nom_usuario_emisor, String nom_dominio_emisor, String cedula_usuario_emisor, String nom_usuario_receptor, String nom_dominio_receptor, String cedula_usuario_receptor, String fecha, String asunto, String texto, int id_conversacion) throws IOException, FileNotFoundException, SQLException{
        correo = new Correo(nom_usuario_emisor, nom_dominio_emisor, cedula_usuario_emisor, nom_usuario_receptor, nom_dominio_receptor, cedula_usuario_receptor, fecha, asunto, texto, id_conversacion);
        correo.alta_correo();
    }
    
    public ResultSet get_correos_BD() throws IOException, FileNotFoundException, SQLException{
        correo = new Correo();
        return correo.get_correos_BD();
    }
    
    public ResultSet get_correos_cuenta_emisor_BD(String nom_usuario_emisor, String nom_dominio_emisor) throws IOException, FileNotFoundException, SQLException{
        correo = new Correo();
        return correo.get_correos_cuenta_emisor_BD(nom_usuario_emisor, nom_dominio_emisor);
    }
    
    public ResultSet get_correos_cuenta_receptor_BD(String nom_usuario_receptor, String nom_dominio_receptor) throws IOException, FileNotFoundException, SQLException{
        correo = new Correo();
        return correo.get_correos_cuenta_receptor_BD(nom_usuario_receptor, nom_dominio_receptor);
    }
    
    public ResultSet get_correo_BD(String nom_usuario_emisor, String nom_dominio_emisor, String nom_usuario_receptor, String nom_dominio_receptor, String fecha) throws IOException, FileNotFoundException, SQLException{
        correo = new Correo();
        return correo.get_correo_BD(nom_usuario_emisor, nom_dominio_emisor, nom_usuario_receptor, nom_dominio_receptor, fecha);
    }
    
    public void baja_correo_BD(String nom_usuario_emisor, String nom_dominio_emisor, String nom_usuario_receptor, String nom_dominio_receptor, String fecha) throws IOException, FileNotFoundException, SQLException{
        correo = new Correo();
        correo.baja_correo_BD(nom_usuario_emisor, nom_dominio_emisor, nom_usuario_receptor, nom_dominio_receptor, fecha);
    }
    
    public void modif_correo_BD(String nom_usuario_emisor, String nom_dominio_emisor, String nom_usuario_receptor, String nom_dominio_receptor, String fecha, int enviado) throws IOException, FileNotFoundException, SQLException{
        correo = new Correo();
        correo.modif_correo_BD(nom_usuario_emisor, nom_dominio_emisor, nom_usuario_receptor, nom_dominio_receptor, fecha, enviado);
    }
    
    public void marcar_enviado_correo(String nom_cuenta_emisor, String nom_dominio_emisor, String fecha, boolean enviado) throws IOException, FileNotFoundException, FileNotFoundException, SQLException{
        correo = new Correo();
        correo.marcar_enviado_correo(nom_cuenta_emisor, nom_dominio_emisor, fecha, enviado);
    }
    
    public void marcar_enviado_correo_fecha(String fecha, int enviado)throws IOException, FileNotFoundException, FileNotFoundException, SQLException{
        correo = new Correo();
        correo.marcar_enviado_correo_fecha(fecha, enviado);
    }
    /*
        ---CORREO---FIN---
    */
    
    /*
        ---BACKUP---
    */
    public static boolean backup_mensajes() throws URISyntaxException, FileNotFoundException, IOException, InterruptedException{
        return Backup.backup_mensajes();
    }
    /*
        ---BACKUP---FIN---
    */
    public JasperPrint listado(String camino) throws IOException, FileNotFoundException, JRException, SQLException{
        Reporte reporte = new Reporte();
        return reporte.listado(camino);
    }
    
    public void carpetaBD(String nombreCarpeta,String nombreCuenta,String dominio,String cedula) throws IOException, FileNotFoundException, SQLException{
        Carpeta carpeta=new Carpeta();
         carpeta.carpetaBD(nombreCarpeta, nombreCuenta, dominio, cedula);
    }
    
}
