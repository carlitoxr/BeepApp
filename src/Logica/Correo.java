
package Logica;

import Persistencia.FachadaBD;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

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
    
    private java.util.Date utilDate = new java.util.Date();
    private long lnMilisegundos = utilDate.getTime();

    public Correo() {
    }

    public Correo(String nom_usuario_emisor, String nom_dominio_emisor, String cedula_usuario_emisor, String nom_usuario_receptor, String nom_dominio_receptor, String cedula_usuario_receptor, String fecha, String asunto, String texto, int id_conversacion, int enviado) {
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
        this.enviado = enviado;
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
        this.enviado = 0;
        //alta_correo_BD(this);
    }
    
    
    
    public Correo(String nom_usuario_emisor, String nom_dominio_emisor, String cedula_usuario_emisor, String nom_usuario_receptor, String nom_dominio_receptor, String cedula_usuario_receptor, String asunto, String texto, int id_conversacion){
        this.nom_usuario_emisor = nom_usuario_emisor;
        this.nom_dominio_emisor = nom_dominio_emisor;
        this.cedula_usuario_emisor = cedula_usuario_emisor;
        this.nom_usuario_receptor = nom_usuario_receptor;
        this.nom_dominio_receptor = nom_dominio_receptor;
        this.cedula_usuario_receptor = cedula_usuario_receptor;
        this.asunto = asunto;
        this.texto = texto;
        this.id_conversacion = id_conversacion;
        this.enviado = 0;
        
        Calendar Calendario = Calendar.getInstance();
		
        int anio = Calendario.get(Calendar.YEAR);
        int mes = Calendario.get(Calendar.MONTH) + 1;
        int dia = Calendario.get(Calendar.DAY_OF_MONTH);
        java.sql.Time sqlTime = new java.sql.Time(lnMilisegundos);
        if (mes < 10){
            this.fecha = anio+"-"+"0"+mes+"-"+dia+" "+sqlTime;
        }
        else
            this.fecha = anio+"-"+mes+"-"+dia+" "+sqlTime;
    }

    public String getNom_usuario_emisor() {
        return nom_usuario_emisor;
    }

    public void setNom_usuario_emisor(String nom_usuario_emisor) {
        this.nom_usuario_emisor = nom_usuario_emisor;
    }

    public String getNom_dominio_emisor() {
        return nom_dominio_emisor;
    }

    public void setNom_dominio_emisor(String nom_dominio_emisor) {
        this.nom_dominio_emisor = nom_dominio_emisor;
    }

    public String getCedula_usuario_emisor() {
        return cedula_usuario_emisor;
    }

    public void setCedula_usuario_emisor(String cedula_usuario_emisor) {
        this.cedula_usuario_emisor = cedula_usuario_emisor;
    }

    public String getNom_usuario_receptor() {
        return nom_usuario_receptor;
    }

    public void setNom_usuario_receptor(String nom_usuario_receptor) {
        this.nom_usuario_receptor = nom_usuario_receptor;
    }

    public String getNom_dominio_receptor() {
        return nom_dominio_receptor;
    }

    public void setNom_dominio_receptor(String nom_dominio_receptor) {
        this.nom_dominio_receptor = nom_dominio_receptor;
    }

    public String getCedula_usuario_receptor() {
        return cedula_usuario_receptor;
    }

    public void setCedula_usuario_receptor(String cedula_usuario_receptor) {
        this.cedula_usuario_receptor = cedula_usuario_receptor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getId_conversacion() {
        return id_conversacion;
    }

    public void setId_conversacion(int id_conversacion) {
        this.id_conversacion = id_conversacion;
    }

    public int getEnviado() {
        return enviado;
    }

    public void setEnviado(int enviado) {
        this.enviado = enviado;
    }
    
    public void marcar_enviado_correo(String nom_cuenta_emisor, String nom_dominio_emisor, String fecha, boolean enviado) throws IOException, FileNotFoundException, FileNotFoundException, SQLException{
        FachadaBD.getInstancia().marcar_enviado_correo(nom_cuenta_emisor, nom_dominio_emisor, fecha, enviado);
    }

    @Override
    public String toString() {
        return "Correo{" + "nom_usuario_emisor=" + nom_usuario_emisor + ", nom_dominio_emisor=" + nom_dominio_emisor + ", cedula_usuario_emisor=" + cedula_usuario_emisor + ", nom_usuario_receptor=" + nom_usuario_receptor + ", nom_dominio_receptor=" + nom_dominio_receptor + ", cedula_usuario_receptor=" + cedula_usuario_receptor + ", fecha=" + fecha + ", asunto=" + asunto + ", texto=" + texto + ", id_conversacion=" + id_conversacion + ", enviado=" + enviado + '}';
    }
    
    
}
