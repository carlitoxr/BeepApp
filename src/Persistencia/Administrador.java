package Persistencia;

import Conexion.Conexion;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Administrador {
    private final String nom_admin;
    private String pass_admin;

    public Administrador() {
        this.nom_admin = null;
    }

    public Administrador(String nom_admin, String pass_admin) {
        this.nom_admin = nom_admin;
        this.pass_admin = pass_admin;
    }
    /*
    public String getNom_admin() {
        return nom_admin;
    }

    public String getPass_admin() {
        return pass_admin;
    }

    public void setPass_admin(String pass_admin) {
        this.pass_admin = pass_admin;
    }*/
    
    public boolean login_admin(String usr, String pass) throws IOException, FileNotFoundException, SQLException{
        String sql = "SELECT * FROM administrador WHERE nom_admin = '" +
                usr + "' AND pass_admin = '" + pass + "'";
        
        System.out.println(sql);
        
        PreparedStatement sentencia = Conexion.getInstancia().conectar().prepareStatement(sql);
        
        if(sentencia.executeQuery(sql).next()){
            Conexion.getInstancia().desconectar();
            return true;
        }
        Conexion.getInstancia().desconectar();
        return false;
    }

    @Override
    public String toString() {
        return "nom_admin: " + this.nom_admin +
                "pass_admin: " + this.pass_admin;
    }
}
