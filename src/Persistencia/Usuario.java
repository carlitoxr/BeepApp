
package Persistencia;

import Conexion.Conexion;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuario {
    private String cedula;
    private String password;
    private String nombre;
    private String apellido;
    private String celular;

//    public Usuario(String cedula, String password, String nombre, String apellido, String celular) {
//        this.cedula = cedula;
//        this.password = password;
//        this.nombre = nombre;
//        this.apellido = apellido;
//        this.celular = celular;
//    }
//
//    public String getCedula() {
//        return cedula;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public String getApellido() {
//        return apellido;
//    }
//
//    public void setApellido(String apellido) {
//        this.apellido = apellido;
//    }
//
//    public String getCelular() {
//        return celular;
//    }
//
//    public void setCelular(String celular) {
//        this.celular = celular;
//    }
    
    public void alta_usuario(String cedula, String password, String nombre, String apellido, String celular) throws IOException, FileNotFoundException, SQLException{
        String sql = "INSERT INTO usuario(cedula, nombre, apellido, password, celular) VALUES ('" +
                cedula + "', '" + nombre + "', '" + apellido + "', '" + password  + "', '" + celular + "')";
        
        Conexion.getInstancia().conectar().prepareStatement(sql).executeUpdate(sql);
        
        Conexion.getInstancia().desconectar();
    }
    
    public void baja_usuario(String cedula)throws IOException, FileNotFoundException, SQLException{
        String sql = "DELETE FROM usuario WHERE cedula = '" + cedula + "'";
        
        Conexion.getInstancia().conectar().prepareStatement(sql).executeUpdate();
        
        Conexion.getInstancia().desconectar();
    }

    public boolean existe_usuario(String cedula)throws IOException, FileNotFoundException, SQLException{
        String sql = "SELECT * FROM usuario WHERE cedula = '" + cedula + "'";
        
        PreparedStatement sentencia = Conexion.getInstancia().conectar().prepareStatement(sql);
        
        if(sentencia.executeQuery(sql).getRow() != 0){
            Conexion.getInstancia().desconectar();
            return true;
        }
        Conexion.getInstancia().desconectar();
        return false;
    }

    public ResultSet getUsuarioBD()throws IOException, FileNotFoundException, SQLException{
        String sql = "SELECT * FROM usuario";
        
        PreparedStatement sentencia = Conexion.getInstancia().conectar().prepareStatement(sql);
        
        ResultSet rs = sentencia.executeQuery();
        
        return rs;
    }

    public void modif_usuario_BD(String cedula, String nombre, String apellido, String celular) throws IOException, FileNotFoundException, SQLException{
        String sql = "UPDATE usuario SET nombre = '" + nombre + 
                "', apellido = '" + apellido +
                "', celular = '" + celular +
                "' WHERE cedula = '" + cedula + "'";
               
        Conexion.getInstancia().conectar().prepareStatement(sql).executeUpdate(sql);
        
        Conexion.getInstancia().desconectar();
    }
    
    public void reset_pass_BD(String cedula, String password) throws IOException, FileNotFoundException, SQLException{
        String sql = "UPDATE usuario SET password = '" + password + "'";
               
        Conexion.getInstancia().conectar().prepareStatement(sql).executeUpdate(sql);
        
        Conexion.getInstancia().desconectar();
    }
    
    public void cambiar_pass(String cedula, String new_pass) throws IOException, FileNotFoundException, SQLException{
        String sql = "UPDATE usuario SET password = '" + new_pass + "'";
               
        Conexion.getInstancia().conectar().prepareStatement(sql).executeUpdate(sql);
        
        Conexion.getInstancia().desconectar();
    }
    
}
