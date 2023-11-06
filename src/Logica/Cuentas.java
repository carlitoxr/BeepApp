
package Logica;

import Persistencia.FachadaBD;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.swing.table.DefaultTableModel;

public class Cuentas {
    private Hashtable<String,Cuenta> dicc_cuentas;

    public Cuentas() {
    }
    
    public void cargar_cuentas_BD() throws IOException, FileNotFoundException, SQLException{
        dicc_cuentas = get_cuentas();
    }

    public Hashtable<String, Cuenta> getDicc_cuentas() {
        return dicc_cuentas;
    }
    
    /*
        Crea la clave de la siguiente forma:
        "[nom_cuenta] [nom_dominio] [cedula]" 
    */
    public String key(String nom_cuenta, String nom_dominio, String cedula){
        return nom_cuenta + " " + nom_dominio + " " + cedula;
    }
    
//    public Hashtable<String,Cuenta> get_cuentas_habilitadas() throws IOException, FileNotFoundException, SQLException{
    public Hashtable<String,Cuenta> get_cuentas() throws IOException, FileNotFoundException, SQLException{
        ResultSet rs = FachadaBD.getInstancia().get_cuentas_BD();
        
        Hashtable<String,Cuenta> h_cuenta = new Hashtable<>();
        
        while(rs.next()){
            String nom_cuenta = rs.getString("nom_cuenta");
            String nom_dominio = rs.getString("nom_dominio");
            String cedula_usuario = rs.getString("cedula_usuario");
            int habilitada = Integer.parseInt(rs.getString("habilitada"));
//            System.out.println("Cuentas get_cuentas " + habilitada);
            
            Dominio dominio = Fachada.getInstancia().obtener_dominio(nom_dominio);
            Usuario usuario = Fachada.getInstancia().obtener_usuario(cedula_usuario);
            
            Cuenta c = new Cuenta(nom_cuenta, dominio, usuario, habilitada);
//            Cuenta c = find(nom_cuenta, nom_dominio, cedula_usuario);
            System.out.println("Cuentas " + nom_cuenta + " " + dominio.getNom_dominio() + " " + usuario.getCedula());
            h_cuenta.put(key(nom_cuenta, nom_dominio, cedula_usuario), c);
        }
        
        return h_cuenta;
    }
    
    public DefaultTableModel listar_tabla(){
        String col[] = {"Cuenta", "Dominio", "Cedula" , "Habilitada"};
        DefaultTableModel tb = new DefaultTableModel(col, 0){
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        Enumeration<Cuenta> enu = dicc_cuentas.elements();
        
        while(enu.hasMoreElements()){
            Cuenta c = enu.nextElement();
//            String row[] = {c.getNom_cuenta(), c.getDominio().getNom_dominio(), c.getUsuario().getCedula(), String.valueOf(c.getHabilitada())};
            
            boolean habilitada = c.getHabilitada() == 1;
//            System.out.println("Cuentas listar_tabla " + c.getHabilitada() + " | " + (c.getHabilitada() == 1));
            
            Object row[] = {c.getNom_cuenta(), c.getDominio().getNom_dominio(), c.getUsuario().getCedula(), habilitada};
            tb.addRow(row);
        }
        
//        ResultSet rs = FachadaBD.getInstancia().get_cuentas_BD();
//        while(rs.next()){
//            System.out.println("" + rs.getString("habilitada"));
//        }
        
        return tb;
    }
    
    /*  Para javadoc:
        Determina si en el diccionario existe un elemento con la clave especificada.
    */
    public boolean member(String nom_cuenta, String nom_dominio, String cedula){
        String k = key(nom_cuenta, nom_dominio, cedula);
        return dicc_cuentas.containsKey(k);
    }
    
    public boolean member_sin_cedula(String nom_cuenta, String nom_dominio){
        //System.out.println("Cuentas " + nom_cuenta + " " + nom_dominio);
        Enumeration<Cuenta> elementos = dicc_cuentas.elements();
        String nom_cuenta_e, nom_dom_e;
        while(elementos.hasMoreElements()){ 
            Cuenta c = elementos.nextElement();
            nom_cuenta_e = c.getNom_cuenta();
            nom_dom_e = c.getDominio().getNom_dominio();
            
            //System.out.println("" + nom_cuenta_e + " " + nom_dom_e);
            if(nom_cuenta_e.equals(nom_cuenta) && nom_dom_e.equals(nom_dominio)){
                return true;
            }
        }
        return false;
    }
    
    /*
        Inserta un elemento de tipo T en el diccionario.
        Precondición: el elemento a insertar no es miembro del diccionario.
    
        Retorno: 
            true: si pudo ingresar el dominio
            false: si el elemento existe
    */
    
    public boolean insert(Cuenta c) throws IOException, FileNotFoundException, SQLException{
        if(!member(c.getNom_cuenta(), c.getDominio().getNom_dominio(), c.getUsuario().getCedula())){
            String k = key(c.getNom_cuenta(), c.getDominio().getNom_dominio(), c.getUsuario().getCedula());
            dicc_cuentas.put(k, c);
            
            FachadaBD.getInstancia().alta_cuenta_BD(c.getNom_cuenta(), c.getDominio().getNom_dominio(), c.getUsuario().getCedula(), 1);
            
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
    public Cuenta find(String nom_cuenta, String nom_dominio, String cedula){
        String k = key(nom_cuenta, nom_dominio, cedula);
        if(member(nom_cuenta, nom_dominio, cedula))
            return dicc_cuentas.get(k);
        else
            return null;
    }
    
    /*
        Sustituye el viejo elemento de tipo T en el diccionario por el nuevo elemento.
        Precondición: el elemento a sustituir es miembro del diccionario.
    */
//    public boolean modify(Cuenta c) throws IOException, FileNotFoundException, SQLException{
//        if(member(dom.getNom_dominio())){
//            this.find(dom.getNom_dominio()).setPrioridad(dom.getPrioridad());
//            FachadaBD.getInstancia().modif_dominio_BD(dom.getNom_dominio(), String.valueOf(dom.getPrioridad()));
//            return false;
//        } else {
//            return false;
//        }
//    }
    
    /*
        Dada la clave de un elemento lo borra del diccionario
        Precondición: el elemento es miembro del diccionario.
    */
    public boolean baja_logica(String nom_cuenta, String nom_dominio, String cedula) throws IOException, FileNotFoundException, SQLException{
        if(member(nom_cuenta, nom_dominio, cedula)){
            String k = key(nom_cuenta, nom_dominio, cedula);
            
//            dicc_cuentas.remove(k);
            find(nom_cuenta, nom_dominio, cedula).setHabilitada(0);
            
            FachadaBD.getInstancia().baja_cuenta_BD(nom_cuenta, nom_dominio, cedula);
            return true;
        } else
            return false;
    }
    
    public boolean alta_logica(String nom_cuenta, String nom_dominio, String cedula) throws IOException, FileNotFoundException, SQLException{
        if(member(nom_cuenta, nom_dominio, cedula)){
//            String k = key(nom_cuenta, nom_dominio, cedula);
            
            find(nom_cuenta, nom_dominio, cedula).setHabilitada(1);
            
            FachadaBD.getInstancia().alta_logica_cuenta_BD(nom_cuenta, nom_dominio, cedula);
            return true;
        } else
            return false;
    }
    
    public ArrayList<Cuenta> obtengo_cuentas_de_un_usuario(String cedula)throws IOException, FileNotFoundException, SQLException{
        ArrayList<Cuenta> aux_cuentas = new ArrayList<>();
        
        ResultSet rs = FachadaBD.getInstancia().obtengo_cuentas_de_un_usuario(cedula);
        
        while(rs.next()){
            String nom_cuenta = rs.getString("nom_cuenta");
            String nom_dominio = rs.getString("nom_dominio");
            String cedula_usuario = rs.getString("cedula_usuario");
            int habilitada = Integer.parseInt(rs.getString("habilitada"));
            
            Dominio dominio = Fachada.getInstancia().obtener_dominio(nom_dominio);
            Usuario usuario = Fachada.getInstancia().obtener_usuario(cedula_usuario);
            
            Cuenta c = new Cuenta(nom_cuenta, dominio, usuario, habilitada);
            
            aux_cuentas.add(c);
        }
        
        return aux_cuentas;
    }
    
}
