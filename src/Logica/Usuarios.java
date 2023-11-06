
package Logica;

import Persistencia.FachadaBD;
import java.sql.ResultSet;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.swing.table.DefaultTableModel;

public class Usuarios {
    private Hashtable<String,Usuario> dicc_usu;

//    public Usuarios() throws IOException, FileNotFoundException, SQLException {
//        dicc_usu = getUsuarios();
//    }
    public Usuarios() {
    }
    public void cargar_usuarios_BD() throws IOException, FileNotFoundException, SQLException {
        dicc_usu = getUsuarios();
    }
    
    
    public Hashtable<String, Usuario> getDicc_usuarios(){
        return dicc_usu;
    }
    
    public Hashtable<String,Usuario> getUsuarios() throws IOException, FileNotFoundException, SQLException{
        ResultSet rs = FachadaBD.getInstancia().getUsuarioBD();
        Hashtable<String,Usuario> h_usu = new Hashtable<>();
        
        while(rs.next()){
            String cedula = rs.getString("cedula");
            String password = rs.getString("password");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String celular = rs.getString("celular");
            
            Usuario usu = new Usuario(cedula, password, nombre, apellido, celular);
            
            h_usu.put(cedula, usu);
        }
        
        return h_usu;
    }
    
    public DefaultTableModel listar_tabla(){
        String col[] = {"Cédula", "Nombre", "Apellido", "Celular"};
        DefaultTableModel tb = new DefaultTableModel(col, 0);
        
        Enumeration<Usuario> enu = dicc_usu.elements();
        while(enu.hasMoreElements()){
            Usuario usu = enu.nextElement();
            
            String cedula = usu.getCedula();
            //String password = usu.getPassword();
            String nombre = usu.getNombre();
            String apellido = usu.getApellido();
            String celular = usu.getCelular();
            //String row[] = {cedula, password, nombre, apellido, celular};
            String row[] = {cedula, nombre, apellido, celular};
            
            tb.addRow(row);
        }
        
        return tb;
    }
    
    /*  Para javadoc:
        Determina si en el diccionario existe un elemento con la clave especificada.
    */
    public boolean member(String k){
        return dicc_usu.containsKey(k);
    }
    
    /*
        Inserta un elemento de tipo T en el diccionario.
        Precondición: el elemento a insertar no es miembro del diccionario.
    
        Retorno: 
            true: si pudo ingresar el usuario
            false: si el elemento existe
    */
    public boolean insert(Usuario usu) throws IOException, FileNotFoundException, SQLException{
        if(!member(usu.getCedula())){
            dicc_usu.put(usu.getCedula(), usu);
            FachadaBD.getInstancia().alta_usuario(usu.getCedula(), usu.getPassword(), usu.getNombre(), usu.getApellido(), usu.getCelular());
            return true;
        } else {
            return false;
        }
    }
    
    /*
        Dada la clave de un elemento devuelve el elemento con dicha clave
        Precondición: el elemento es miembro del diccionario.
    
        Retorno:
            Si encuentra el usuario lo retorna
            Sino entonces retorna null
    */
    public Usuario find(String k){
        if(member(k))
            return dicc_usu.get(k);
        else
            return null;
    }
    
    /*
        Sustituye el viejo elemento de tipo T en el diccionario por el nuevo elemento.
        Precondición: el elemento a sustituir es miembro del diccionario.
    */
    public boolean modify(Usuario usu) throws IOException, FileNotFoundException, SQLException{
        if(member(usu.getCedula())){
            this.find(usu.getCedula()).setNombre(usu.getNombre());
            this.find(usu.getCedula()).setApellido(usu.getApellido());
            this.find(usu.getCedula()).setCelular(usu.getCelular());
            
            FachadaBD.getInstancia().modif_usuario_BD(usu.getCedula(), usu.getNombre(), usu.getApellido(), usu.getCelular());
            return false;
        } else {
            return false;
        }
    }
    
    /*
        Resetea la contraseña a la cédula del usuario
    */
    public boolean resetear_pass(Usuario usu) throws IOException, FileNotFoundException, SQLException{
        if(member(usu.getCedula())){
            this.find(usu.getCedula()).setPassword(usu.getCedula());
            
            FachadaBD.getInstancia().reset_pass_BD(usu.getCedula(), usu.getCedula());
            return true;
        } else {
            return false;
        }
    }
    
    public boolean cambiar_pass(String cedula, String new_pass) throws IOException, FileNotFoundException, SQLException{
        if(member(cedula)){
            this.find(cedula).setPassword(new_pass);
            
            FachadaBD.getInstancia().cambiar_pass(cedula, new_pass);
            return true;
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
            this.dicc_usu.remove(k);
            FachadaBD.getInstancia().baja_usuario(k);
            return true;
        } else 
            return false;
    }
    
    /*
        ---CLIENTE---
    */
    
    public boolean login_cliente(String cedula, String password){
        return find(cedula).getPassword().equals(password);
    }
    
    /*
        ---CLIENTE---FIN---
    */
}
