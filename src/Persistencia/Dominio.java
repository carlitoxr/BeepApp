package Persistencia;

import Conexion.Conexion;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dominio {
    /*
    create table dominio(
	nom_dominio varchar(20),
    prioridad int,
    constraint pk_dom primary key (nom_dominio)
);
    */
    private final String nom_dominio;
    private int prioridad;

    public Dominio() {
        this.nom_dominio = null;
    }

    public Dominio(String nom_dominio, int prioridad) {
        this.nom_dominio = nom_dominio;
        this.prioridad = prioridad;
    }

    /*public String getNom_dominio() {
        return nom_dominio;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return "nom_dominio: " + nom_dominio +
                "prioridad: " + prioridad;
    }*/
    
    public void alta_dominio(String nom_dominio, int prioridad) throws IOException, FileNotFoundException, SQLException{
        String sql = "INSERT INTO dominio(nom_dominio, prioridad) VALUES ('" +
                nom_dominio + "', " + prioridad + ")";
        System.out.println(sql);
        Conexion.getInstancia().conectar().prepareStatement(sql).executeUpdate(sql);
        
        Conexion.getInstancia().desconectar();
    }
    
    public void baja_dominio(String nom_dominio)throws IOException, FileNotFoundException, SQLException{
        String sql = "DELETE FROM dominio WHERE nom_dominio = '" + nom_dominio + "'";
        
        Conexion.getInstancia().conectar().prepareStatement(sql).executeUpdate();
        
        Conexion.getInstancia().desconectar();
    }
    
    public boolean existe_dominio(String nom_dominio)throws IOException, FileNotFoundException, SQLException{
        String sql = "SELECT * FROM dominio WHERE nom_dominio = '" + nom_dominio;
        
        PreparedStatement sentencia = Conexion.getInstancia().conectar().prepareStatement(sql);
        
        if(sentencia.executeQuery(sql).getRow() != 0){
            Conexion.getInstancia().desconectar();
            return true;
        }
        Conexion.getInstancia().desconectar();
        return false;
    }
    
    public ResultSet getDominiosBD()throws IOException, FileNotFoundException, SQLException{
        String sql = "SELECT * FROM dominio";
        
        PreparedStatement sentencia = Conexion.getInstancia().conectar().prepareStatement(sql);
        
        ResultSet rs = sentencia.executeQuery();
        
        //Conexion.getInstancia().desconectar();
        
        return rs;
    }
    
    public void modif_dominio_BD(String nom_dom, String prioridad_new) throws IOException, FileNotFoundException, SQLException{
        String sql = "UPDATE dominio SET prioridad = " + prioridad_new + 
                " WHERE nom_dominio = '" + nom_dom + "'";
        
        System.out.println(sql);
        
        Conexion.getInstancia().conectar().prepareStatement(sql).executeUpdate(sql);
        
        Conexion.getInstancia().desconectar();
    }
    
}
