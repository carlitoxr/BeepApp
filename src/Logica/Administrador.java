package Logica;

import Persistencia.FachadaBD;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    public String getNom_admin() {
        return nom_admin;
    }

    public String getPass_admin() {
        return pass_admin;
    }

    public void setPass_admin(String pass_admin) {
        this.pass_admin = pass_admin;
    }

    @Override
    public String toString() {
        return "nom_admin: " + this.nom_admin +
                "pass_admin: " + this.pass_admin;
    }
    
    public boolean login_admin(String usr, String pass) throws IOException, FileNotFoundException, FileNotFoundException, SQLException{
        return FachadaBD.getInstancia().login_admin(usr, pass);
    }
}
