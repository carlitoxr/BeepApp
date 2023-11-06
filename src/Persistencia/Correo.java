
package Persistencia;

import Conexion.Conexion;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Correo {
    private String nom_usuario_emisor;
    private String nom_dominio_emisor;
    private String cedula_usuario_emisor;
    private String nom_usuario_receptor;
    private String nom_dominio_receptor;
    private String cedula_usuario_receptor;
    private String fecha; //YYYY-MM-DD hh:mm:ss
    private String asunto;
    private String texto;
    private int id_conversacion;
    private int enviado;

    public Correo() {
    }

    public Correo(String nom_usuario_emisor, String nom_dominio_emisor, String cedula_usuario_emisor, String nom_usuario_receptor, String nom_dominio_receptor, String cedula_usuario_receptor, String fecha, String asunto, String texto, int id_conversacion) {
        this.nom_usuario_emisor = nom_usuario_emisor;
        this.nom_dominio_emisor = nom_dominio_emisor;
        this.cedula_usuario_emisor = cedula_usuario_emisor;
        this.nom_usuario_receptor = nom_usuario_receptor;
        this.nom_dominio_receptor = nom_dominio_receptor;
        this.cedula_usuario_receptor = cedula_usuario_receptor;
        this.fecha = fecha;
        this.asunto = asunto;
        this.texto = texto;
        this.id_conversacion = id_conversacion;
        this.enviado = 0; //Se setea a 0 debido a que el correo no salió del servidor
    }
    
    //dar de alta si está el srv prendido (pendiente)
    public void alta_correo() throws IOException, FileNotFoundException, SQLException{
        //String nom_usuario_emisor, String nom_dominio_emisor, String cedula_usuario_emisor, String nom_usuario_receptor, String nom_dominio_receptor, String cedula_usuario_receptor, String fecha, String asunto, String texto, int id_conversacion, int enviado
        //nom_usuario_emisor, nom_dominio_emisor, cedula_usuario_emisor, nom_usuario_receptor, nom_dominio_receptor, cedula_usuario_receptor, fecha, asunto, texto, id_conversacion, enviado
        String sql = "INSERT INTO correo(nom_usuario_emisor, nom_dominio_emisor, cedula_usuario_emisor, nom_usuario_receptor, nom_dominio_receptor, cedula_usuario_receptor, fecha, asunto, texto, id_conversacion, enviado) VALUES ('" +
                nom_usuario_emisor + "', '" + nom_dominio_emisor + "', '" + cedula_usuario_emisor + "', '" + nom_usuario_receptor + "', '" + nom_dominio_receptor + "', '" + cedula_usuario_receptor + "', '" + fecha + "', '" + asunto + "', '" + texto + "', " + id_conversacion + ", " + enviado + ")";
        System.out.println("correobd alta_correo " + sql);
        Conexion.getInstancia().conectar().prepareStatement(sql).executeUpdate(sql);
        
        Conexion.getInstancia().desconectar();
    }
    
    public ResultSet get_correos_BD() throws IOException, FileNotFoundException, SQLException{
        String sql = "SELECT * FROM correo";
        
        PreparedStatement sentencia = Conexion.getInstancia().conectar().prepareStatement(sql);
        
        ResultSet rs = sentencia.executeQuery();
        
        return rs;
    }
    
    public ResultSet get_correos_cuenta_emisor_BD(String nom_usuario_emisor, String nom_dominio_emisor) throws IOException, FileNotFoundException, SQLException{
        String sql = "SELECT * FROM correo WHERE nom_usuario_emisor = '" +
                nom_usuario_emisor + "' AND nom_dominio_emisor = '" + nom_dominio_emisor + "'";
        
        PreparedStatement sentencia = Conexion.getInstancia().conectar().prepareStatement(sql);
        
        ResultSet rs = sentencia.executeQuery();
        
        return rs;
    }
    
    //MODIFICANDO PARA OBTENER COMO EMISOR Y RECEPTOR
    public ResultSet get_correos_cuenta_receptor_BD(String nom_usuario_receptor, String nom_dominio_receptor) throws IOException, FileNotFoundException, SQLException{
        /*String sql = "SELECT * FROM correo WHERE nom_usuario_receptor = '" +
                nom_usuario_receptor + "' AND nom_dominio_receptor = '" + nom_dominio_receptor + "'";*/
        String sql = "SELECT * FROM correo WHERE nom_usuario_receptor = '" +
                nom_usuario_receptor + "' AND nom_dominio_receptor = '" + nom_dominio_receptor + "' OR " +
                "nom_usuario_emisor = '" + nom_usuario_receptor +
                "' AND nom_dominio_emisor = '" + nom_dominio_receptor + "'";
        
        PreparedStatement sentencia = Conexion.getInstancia().conectar().prepareStatement(sql);
        
        ResultSet rs = sentencia.executeQuery();
        
        return rs;
    }
    
    public ResultSet get_correo_BD(String nom_usuario_emisor, String nom_dominio_emisor, String nom_usuario_receptor, String nom_dominio_receptor, String fecha) throws IOException, FileNotFoundException, SQLException{
        String sql = "SELECT * FROM correo WHERE nom_usuario_emisor = '" +
                nom_usuario_emisor + "' AND nom_dominio_emisor = '" + nom_dominio_emisor + 
                "' AND nom_usuario_receptor = '" + nom_usuario_receptor + 
                "' AND nom_dominio_receptor = '" + nom_dominio_receptor +
                "' AND fecha = '" + fecha + "'";
        
        PreparedStatement sentencia = Conexion.getInstancia().conectar().prepareStatement(sql);
        
        ResultSet rs = sentencia.executeQuery();
        
        return rs;
    }
    
    public void baja_correo_BD(String nom_usuario_emisor, String nom_dominio_emisor, String nom_usuario_receptor, String nom_dominio_receptor, String fecha) throws IOException, FileNotFoundException, SQLException{
        String sql = "DELETE FROM correo WHERE nom_usuario_emisor = '" +
                nom_usuario_emisor + "' AND nom_dominio_emisor = '" + nom_dominio_emisor + 
                "' AND nom_usuario_receptor = '" + nom_usuario_receptor + 
                "' AND nom_dominio_receptor = '" + nom_dominio_receptor +
                "' AND fecha = '" + fecha + "'";
        
        Conexion.getInstancia().conectar().prepareStatement(sql).executeUpdate(sql);
        
        Conexion.getInstancia().desconectar();
    }
    
    public void modif_correo_BD(String nom_usuario_emisor, String nom_dominio_emisor, String nom_usuario_receptor, String nom_dominio_receptor, String fecha, int enviado) throws IOException, FileNotFoundException, SQLException{
        String sql = "UPDATE correo SET enviado = " + enviado + " WHERE nom_usuario_emisor = '" +
                nom_usuario_emisor + "' AND nom_dominio_emisor = '" + nom_dominio_emisor + 
                "' AND nom_usuario_receptor = '" + nom_usuario_receptor + 
                "' AND nom_dominio_receptor = '" + nom_dominio_receptor +
                "' AND fecha = '" + fecha + "'";
        
        Conexion.getInstancia().conectar().prepareStatement(sql).executeUpdate(sql);
        
        Conexion.getInstancia().desconectar();
    }
    
    /*
        Primero se prueba teniendo los datos de nom_cuenta, nom_dominio y fecha.
        
        Pero también podría ser solo fecha <-----
    */
    public void marcar_enviado_correo(String nom_cuenta_emisor, String nom_dominio_emisor, String fecha, boolean enviado) throws IOException, FileNotFoundException, FileNotFoundException, SQLException{
        String sql = "UPDATE correo SET enviado = " + enviado + " WHERE nom_usuario_emisor = '" +
                nom_usuario_emisor + "' AND nom_dominio_emisor = '" + nom_dominio_emisor +
                "' AND fecha = '" + fecha + "'";
        
        Conexion.getInstancia().conectar().prepareStatement(sql).executeUpdate(sql);
        
        Conexion.getInstancia().desconectar();
    }
    
    public void marcar_enviado_correo_fecha(String fecha, int enviado)throws IOException, FileNotFoundException, FileNotFoundException, SQLException{
        System.out.println("Persistencia.Correo " + enviado);
        String sql = "UPDATE correo SET enviado = " + enviado + " WHERE fecha = '" + fecha + "'";
        
        Conexion.getInstancia().conectar().prepareStatement(sql).executeUpdate(sql);
        
        Conexion.getInstancia().desconectar();
    }
}
