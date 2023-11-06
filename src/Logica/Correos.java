
package Logica;

import Persistencia.FachadaBD;
import java.sql.ResultSet;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

public class Correos {
    private ArrayList<Correo> set_correos;

    public Correos() {
    }
    
    public void crear_coleccion(){
        set_correos = new ArrayList<>();
    }
    
    /*
    public void Borrar(Correo correo){
    public ArrayList<Correo> getSetCorreos() {
    */
    public void Insertar(Correo correo) throws IOException, FileNotFoundException, SQLException{
        set_correos.add(correo);
        FachadaBD.getInstancia().alta_correo_BD(correo.getNom_usuario_emisor(), correo.getNom_dominio_emisor(), correo.getCedula_usuario_emisor(), correo.getNom_usuario_receptor(), correo.getNom_dominio_receptor(), correo.getCedula_usuario_receptor(), correo.getFecha(), correo.getAsunto(), correo.getTexto(), correo.getId_conversacion());
    }
    
    public boolean EsVacio(){
        return set_correos.isEmpty();
    }
    
    public boolean Pertenece(Correo correo){
        return set_correos.contains(correo); //¿FUNCIONA?
    }
    
    public void Borrar(Correo correo) throws IOException, FileNotFoundException, SQLException{
        if(Pertenece(correo)){
            set_correos.remove(correo);
            FachadaBD.getInstancia().baja_correo_BD(correo.getNom_usuario_emisor(), correo.getNom_dominio_emisor(), correo.getNom_usuario_receptor(), correo.getNom_dominio_receptor(), correo.getFecha());
        }
    }
    
    public ArrayList<Correo> getSetCorreos() {
        return set_correos;
    }
    
    //Lo que tengo que hacer es cargar la colección de correos dentro de cada cuenta
    public void obtener_correos_cuenta(String nom_usu, String nom_dom) throws IOException, FileNotFoundException, SQLException{
//        if(!set_correos.isEmpty())
//            set_correos.clear();
        
        set_correos = obtener_correos_emisor_cuenta(nom_usu, nom_dom, set_correos);
        set_correos = obtener_correos_receptor_cuenta(nom_usu, nom_dom, set_correos);
        
//        return correos;
    }
    
    public ArrayList<Correo> obtener_correos_emisor_cuenta(String nom_usu_emisor, String nom_dom_emisor, ArrayList<Correo> correos) throws IOException, FileNotFoundException, SQLException{
        ResultSet rs = FachadaBD.getInstancia().get_correos_cuenta_emisor_BD(nom_usu_emisor, nom_dom_emisor);
        
        while(rs.next()){
            //Emisor
            String nom_usuario_emisor = rs.getString("nom_usuario_emisor");
            String nom_dominio_emisor = rs.getString("nom_dominio_emisor");
            String cedula_usuario_emisor = rs.getString("cedula_usuario_emisor");
            //Receptor
            String nom_usuario_receptor = rs.getString("nom_usuario_receptor");
            String nom_dominio_receptor = rs.getString("nom_dominio_receptor");
            String cedula_usuario_receptor = rs.getString("cedula_usuario_receptor");
            //Otros
            String fecha = rs.getString("fecha"); //YYYY-MM-DD hh:mm:ss
            String asunto = rs.getString("asunto");
            String texto = rs.getString("texto");
            int id_conversacion = rs.getInt("id_conversacion");
            int enviado = rs.getInt("enviado");
            
            Correo c = new Correo(nom_usuario_emisor, nom_dominio_emisor, cedula_usuario_emisor, 
                    nom_usuario_receptor, nom_dominio_receptor, cedula_usuario_receptor, 
                    fecha, asunto, texto, id_conversacion, enviado);
            
            correos.add(c);
        }
        
        return correos;
    }
    
    public ArrayList<Correo> obtener_correos_receptor_cuenta(String nom_usu_receptor, String nom_dom_receptor, ArrayList<Correo> correos) throws IOException, FileNotFoundException, SQLException{
        ResultSet rs = FachadaBD.getInstancia().get_correos_cuenta_receptor_BD(nom_usu_receptor, nom_dom_receptor);
        
        while(rs.next()){
            //Emisor
            String nom_usuario_emisor = rs.getString("nom_usuario_emisor");
            String nom_dominio_emisor = rs.getString("nom_dominio_emisor");
            String cedula_usuario_emisor = rs.getString("cedula_usuario_emisor");
            //Receptor
            String nom_usuario_receptor = rs.getString("nom_usuario_receptor");
            String nom_dominio_receptor = rs.getString("nom_dominio_receptor");
            String cedula_usuario_receptor = rs.getString("cedula_usuario_receptor");
            //Otros
            String fecha = rs.getString("fecha"); //YYYY-MM-DD hh:mm:ss
            String asunto = rs.getString("asunto");
            String texto = rs.getString("texto");
            int id_conversacion = rs.getInt("id_conversacion");
            int enviado = rs.getInt("enviado");
            
            Correo c = new Correo(nom_usuario_emisor, nom_dominio_emisor, cedula_usuario_emisor, 
                    nom_usuario_receptor, nom_dominio_receptor, cedula_usuario_receptor, 
                    fecha, asunto, texto, id_conversacion, enviado);
            correos.add(c);
        }
        
        return correos;
    }
    
    //Se marca enviados a los correos que llegan al servidor
    public void marcar_enviado_correos(String nom_cuenta, String nom_dominio, String cedula){
        for(Correo correo : set_correos){
            
        }
    }
    
}
