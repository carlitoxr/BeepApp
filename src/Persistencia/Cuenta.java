
package Persistencia;

import Conexion.Conexion;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cuenta {
    private String nom_cuenta;
    private String dominio;
    private String usuario;
    private int habilitada;

    public Cuenta() {
    }

    public Cuenta(String nom_cuenta, String dominio, String usuario, int habilitada) {
        this.nom_cuenta = nom_cuenta;
        this.dominio = dominio;
        this.usuario = usuario;
        this.habilitada = habilitada;
    }

//    public String getNom_cuenta() {
//        return nom_cuenta;
//    }
//
//    public String getDominio() {
//        return dominio;
//    }
//
//    public String getUsuario() {
//        return usuario;
//    }
//
//    public int getHabilitada() {
//        return habilitada;
//    }
//
//    public void setHabilitada(int habilitada) {
//        this.habilitada = habilitada;
//    }
    
    
    //String nom_cuenta, String dominio, String usuario, int habilitada
    public void alta_cuenta(String nom_cuenta, String dominio, String usuario, int habilitada) throws IOException, FileNotFoundException, SQLException{
        String sql = "INSERT INTO cuenta(nom_cuenta, nom_dominio, cedula_usuario, habilitada) VALUES ('" +
                nom_cuenta + "', '" + dominio + "', '" + usuario + "', " + habilitada + ")";
        
        Conexion.getInstancia().conectar().prepareStatement(sql).executeUpdate(sql);
        
        Conexion.getInstancia().desconectar();
    }
    
    //Baja lógica NO FÍSICA
    public void baja_cuenta(String nom_cuenta, String dominio, String usuario) throws IOException, FileNotFoundException, SQLException{
        String sql = "UPDATE cuenta SET habilitada = " + 0 + 
                " WHERE nom_cuenta = '" + nom_cuenta + 
                "' AND nom_dominio = '" + dominio +
                "' AND cedula_usuario = '" + usuario + "'";
        
        Conexion.getInstancia().conectar().prepareStatement(sql).executeUpdate();
        
        Conexion.getInstancia().desconectar();
    }
    
    //Habilitación de la cuenta dada de baja lógica
    public void alta_logica_cuenta(String nom_cuenta, String dominio, String usuario) throws IOException, FileNotFoundException, SQLException{
        String sql = "UPDATE cuenta SET habilitada = " + 1 + 
                " WHERE nom_cuenta = '" + nom_cuenta + 
                "' AND nom_dominio = '" + dominio +
                "' AND cedula_usuario = '" + usuario + "'";
        
        Conexion.getInstancia().conectar().prepareStatement(sql).executeUpdate();
        
        Conexion.getInstancia().desconectar();
    }
    
    public boolean existe_cuenta(String nom_cuenta, String dominio, String usuario) throws IOException, FileNotFoundException, SQLException{
        String sql = "SELECT * FROM cuenta WHERE nom_cuenta = '" + nom_cuenta + 
                "' AND nom_dominio = '" + dominio +
                "' AND cedula_usuario = '" + usuario + "'";
        
        PreparedStatement sentencia = Conexion.getInstancia().conectar().prepareStatement(sql);
        
        if(sentencia.executeQuery(sql).getRow() != 0){
            Conexion.getInstancia().desconectar();
            return true;
        }
        Conexion.getInstancia().desconectar();
        return false;
    }
    
    public boolean existe_cuenta_habilitada(String nom_cuenta, String dominio, String usuario) throws IOException, FileNotFoundException, SQLException{
        String sql = "SELECT * FROM cuenta WHERE nom_cuenta = '" + nom_cuenta + 
                "' AND nom_dominio = '" + dominio +
                "' AND cedula_usuario = '" + usuario +
                "' AND habilitada = 1";
        
        PreparedStatement sentencia = Conexion.getInstancia().conectar().prepareStatement(sql);
        
        if(sentencia.executeQuery(sql).getRow() != 0){
            Conexion.getInstancia().desconectar();
            return true;
        }
        Conexion.getInstancia().desconectar();
        return false;
    }
    
    public boolean existe_cuenta_deshabilitada(String nom_cuenta, String dominio, String usuario) throws IOException, FileNotFoundException, SQLException{
        String sql = "SELECT * FROM cuenta WHERE nom_cuenta = '" + nom_cuenta + 
                "' AND nom_dominio = '" + dominio +
                "' AND cedula_usuario = '" + usuario +
                "' AND habilitada = 0";
        
        PreparedStatement sentencia = Conexion.getInstancia().conectar().prepareStatement(sql);
        
        if(sentencia.executeQuery(sql).getRow() != 0){
            Conexion.getInstancia().desconectar();
            return true;
        }
        Conexion.getInstancia().desconectar();
        return false;
    }
    
    public ResultSet get_cuentas_BD()throws IOException, FileNotFoundException, SQLException{
        String sql = "SELECT * FROM cuenta ";
        
        PreparedStatement sentencia = Conexion.getInstancia().conectar().prepareStatement(sql);
        
        ResultSet rs = sentencia.executeQuery();
        
        //Conexion.getInstancia().desconectar();
//        while(rs.next()){
//            System.out.println("" + rs.getString("habilitada"));
//        }
        
        return rs;
    }
    
    public ResultSet get_cuenta_habilitada_BD()throws IOException, FileNotFoundException, SQLException{
        String sql = "SELECT * FROM cuenta WHERE habilitada = 1 ";
        
        PreparedStatement sentencia = Conexion.getInstancia().conectar().prepareStatement(sql);
        
        ResultSet rs = sentencia.executeQuery();
        
        //Conexion.getInstancia().desconectar();
        
        return rs;
    }
    
    public ResultSet get_cuenta_deshabilitada_BD()throws IOException, FileNotFoundException, SQLException{
        String sql = "SELECT * FROM cuenta WHERE habilitada = 0 ";
        
        PreparedStatement sentencia = Conexion.getInstancia().conectar().prepareStatement(sql);
        
        ResultSet rs = sentencia.executeQuery();
        
        //Conexion.getInstancia().desconectar();
        
        return rs;
    }
    
    /*
    
    public void modif_dominio_BD(String nom_dom, String prioridad_new) throws IOException, FileNotFoundException, SQLException{
        String sql = "UPDATE dominio SET prioridad = " + prioridad_new + 
                " WHERE nom_dominio = '" + nom_dom + "'";
        
        System.out.println(sql);
        
        Conexion.getInstancia().conectar().prepareStatement(sql).executeUpdate(sql);
        
        Conexion.getInstancia().desconectar();
    }
    
    */
    
    public ResultSet obtengo_cuentas_de_un_usuario(String cedula)throws IOException, FileNotFoundException, SQLException{
        String sql = "SELECT * FROM cuenta WHERE cedula_usuario ='" + cedula + "'";
        
        PreparedStatement sentencia = Conexion.getInstancia().conectar().prepareStatement(sql);
        
        ResultSet rs = sentencia.executeQuery();
        
        return rs;
    }
    
    public String obtengo_pass_usuario(String cedula_usuario)throws IOException, FileNotFoundException, SQLException{
        String sql = "SELECT * FROM usuario WHERE cedula ='" + cedula_usuario + "'";
        System.out.println("Persistencia.cuenta.obtengo_pass_usuario "+sql);
        
        PreparedStatement sentencia = Conexion.getInstancia().conectar().prepareStatement(sql);
        
        ResultSet rs = sentencia.executeQuery();
        
        while(rs.next())
            return rs.getString("password");
        return rs.getString("password");
    }
    
    public String obtengo_cedula_usuario(String nom_usu, String nom_dom)throws IOException, FileNotFoundException, SQLException{
        String sql = "SELECT * FROM cuenta WHERE nom_cuenta = '" + nom_usu + 
                "' AND nom_dominio = '" + nom_dom + "'";
        //System.out.println("Persistencia.cuenta.obtengo_pass_usuario "+sql);
        
        PreparedStatement sentencia = Conexion.getInstancia().conectar().prepareStatement(sql);
        
        ResultSet rs = sentencia.executeQuery();
        
        while(rs.next())
            return rs.getString("cedula_usuario");
        return rs.getString("cedula_usuario");
    }
    
}
