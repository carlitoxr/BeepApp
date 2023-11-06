
package Logica;

import Persistencia.FachadaBD;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/*
Make: ∅ → Diccionario
Crea un diccionario vacío.

Member : Diccionario x K → Boolean
Determina si en el diccionario existe un elemento con la clave especificada.

Insert : Diccionario x T → Diccionario
Inserta un elemento de tipo T en el diccionario.
Precondición: el elemento a insertar no es miembro del diccionario.

Find : Diccionario x K → T
Dada la clave de un elemento devuelve el elemento con dicha clave
Precondición: el elemento es miembro del diccionario.

Modify : Diccionario x T → Diccionario
Sustituye el viejo elemento de tipo T en el diccionario por el nuevo elemento.
Precondición: el elemento a sustituir es miembro del diccionario.

Delete: Diccionario x K → Diccionario
Dada la clave de un elemento lo borra del diccionario
Precondición: el elemento es miembro del diccionario.
*/

public class Dominios {
    private Hashtable<String,Dominio> dicc_dominios;
    

//    public Dominios() throws IOException, FileNotFoundException, SQLException{
//       this.dicc_dominios = getDominios();
//    }
    public Dominios() {
    }
    public void cargar_dominios_BD() throws IOException, FileNotFoundException, SQLException{
        dicc_dominios = getDominios();
    }

    public Hashtable<String, Dominio> getDicc_dominios() {
        return dicc_dominios;
    }
    
    //public Hashtable<String,Dominio> getDominios() throws IOException, FileNotFoundException, SQLException{
//    public void getDominios() throws IOException, FileNotFoundException, SQLException{
    public Hashtable<String,Dominio> getDominios() throws IOException, FileNotFoundException, SQLException{
        ResultSet rs = FachadaBD.getInstancia().get_dominios();
        
        Hashtable<String,Dominio> h_dom = new Hashtable<>();
        
        while(rs.next()){
            String nom_dom = rs.getString("nom_dominio");
            int prioridad = Integer.parseInt(rs.getString("prioridad"));
            
            Dominio d = new Dominio(nom_dom, prioridad);
            
            h_dom.put(nom_dom, d);
        }
        
        return h_dom;
    }
    
    public DefaultTableModel listar_tabla(){
        String col[] = {"Nombre", "Prioridad"};
        DefaultTableModel tb = new DefaultTableModel(col, 0);
        
        Enumeration<Dominio> enu = dicc_dominios.elements();
        
        while(enu.hasMoreElements()){
            Dominio dom = enu.nextElement();
            String row[] = {dom.getNom_dominio(), String.valueOf(dom.getPrioridad())};
            tb.addRow(row);
        }
        
        return tb;
    }
    
    public DefaultComboBoxModel listar_combobox(){
        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        
        Enumeration<Dominio> enu = dicc_dominios.elements();
        
        while(enu.hasMoreElements()){
            Dominio dom = enu.nextElement();
            String nom_dominio = dom.getNom_dominio();
            
            cb.addElement(nom_dominio);
        }
        return cb;
    }
    
    /*  Para javadoc:
        Determina si en el diccionario existe un elemento con la clave especificada.
    */
    public boolean member(String k){
        return dicc_dominios.containsKey(k);
    }
    
    /*
        Inserta un elemento de tipo T en el diccionario.
        Precondición: el elemento a insertar no es miembro del diccionario.
    
        Retorno: 
            true: si pudo ingresar el dominio
            false: si el elemento existe
    */
    public boolean insert(Dominio dom) throws IOException, FileNotFoundException, SQLException{
        if(!member(dom.getNom_dominio())){
            dicc_dominios.put(dom.getNom_dominio(), dom);
//            dom.alta();
            FachadaBD.getInstancia().alta_dominio(dom.getNom_dominio(), dom.getPrioridad());
            return true;
        } else {
            return false;
        }
    }
    
    /*
        Dada la clave de un elemento devuelve el elemento con dicha clave
        Precondición: el elemento es miembro del diccionario.
    
        Retorno:
            Si encuentra el dominio lo retorna
            Sino entonces retorna null
    */
    public Dominio find(String k){
        if(member(k))
            return dicc_dominios.get(k);
        else
            return null;
    }
    
    /*
        Sustituye el viejo elemento de tipo T en el diccionario por el nuevo elemento.
        Precondición: el elemento a sustituir es miembro del diccionario.
    */
    public boolean modify(Dominio dom) throws IOException, FileNotFoundException, SQLException{
        if(member(dom.getNom_dominio())){
            this.find(dom.getNom_dominio()).setPrioridad(dom.getPrioridad());
            FachadaBD.getInstancia().modif_dominio_BD(dom.getNom_dominio(), String.valueOf(dom.getPrioridad()));
            return false;
        } else {
            return false;
        }
    }
    
    /*
        Dada la clave de un elemento lo borra del diccionario
        Precondición: el elemento es miembro del diccionario.
    */
    public boolean delete(String k) throws IOException, FileNotFoundException, SQLException{
        if(member(k)){
            this.dicc_dominios.remove(k);
            FachadaBD.getInstancia().baja_dominio(k);
            return true;
        } else
            return false;
    }
    
}
