package Logica;

import Persistencia.FachadaBD;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    public Dominio(String nom_dominio, int prioridad) throws IOException, FileNotFoundException, SQLException {
        this.nom_dominio = nom_dominio;
        this.prioridad = prioridad;
    }

    public String getNom_dominio() {
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
    }
    
//    public void alta() throws IOException, FileNotFoundException, SQLException{
//        FachadaBD.getInstancia().alta_dominio(nom_dominio, prioridad);
//    }
}
