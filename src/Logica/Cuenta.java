
package Logica;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

public class Cuenta {
    private String nom_cuenta;
    private Dominio dominio;
    private Usuario usuario;
    private int habilitada;
    
    private Carpetas carpetas;
    
    private Correos correos;

//    public Cuenta(String nom_cuenta, Dominio dominio, Usuario usuario, int habilitada) {
//        this.nom_cuenta = nom_cuenta;
//        this.dominio = dominio;
//        this.usuario = usuario;
//        this.habilitada = habilitada;
//    }
    public Cuenta(String nom_cuenta, Dominio dominio, Usuario usuario) {
        this.nom_cuenta = nom_cuenta;
        this.dominio = dominio;
        this.usuario = usuario;
        this.habilitada = 1;
        
        correos = new Correos();
    }
    
//    public Cuenta(String nom_cuenta, Dominio dominio, Usuario usuario, int habilitada) {
//        this.nom_cuenta = nom_cuenta;
//        this.dominio = dominio;
//        this.usuario = usuario;
//        this.habilitada = habilitada;
//    }
    
    public Cuenta(String nom_cuenta, Dominio dominio, Usuario usuario, int habilitada) throws IOException, FileNotFoundException, SQLException {
        this.nom_cuenta = nom_cuenta;
        this.dominio = dominio;
        this.usuario = usuario;
        this.habilitada = habilitada;
        
        this.correos = new Correos();
        this.correos.crear_coleccion();
        this.correos.obtener_correos_cuenta(nom_cuenta, dominio.getNom_dominio());
    }

    public Cuenta(String nom_cuenta, Dominio dominio, Usuario usuario, int habilitada, Carpetas carpetas) {
        this.nom_cuenta = nom_cuenta;
        this.dominio = dominio;
        this.usuario = usuario;
        this.habilitada = habilitada;
        this.carpetas = carpetas;
    }

    public String getNom_cuenta() {
        return nom_cuenta;
    }

    public Dominio getDominio() {
        return dominio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public int getHabilitada() {
        return habilitada;
    }

    public void setHabilitada(int habilitada) {
        this.habilitada = habilitada;
    }

    public Carpetas getCarpetas() {
        return carpetas;
    }

    public void setCarpetas(Carpetas carpetas) {
        this.carpetas = carpetas;
    }
    
    /*
        ---CORREO---
    */
    public void alta_correo(Correo correo) throws IOException, FileNotFoundException, SQLException{
         correos.Insertar(correo);
    }
     
    public boolean set_correos_EsVacio(){
         return correos.EsVacio();
    }
     
    public boolean correo_pertenece(Correo correo){
        return correos.Pertenece(correo);
    }
    
    public void correo_borrar(Correo correo) throws IOException, FileNotFoundException, SQLException{
        correos.Borrar(correo);
    }
    
    //Recargar correos desde BD:
    public void refresh_correos() throws IOException, FileNotFoundException, SQLException{
        correos.obtener_correos_cuenta(nom_cuenta, dominio.getNom_dominio());
    }
//    public Set<Correo> getSetCorreos() {
//        return correos.getSetCorreos();
//    }
    /*
        ---CORREO---FIN---
    */

    public Correos getCorreos() {
        return correos;
    }
}
