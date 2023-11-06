package Logica;

import Persistencia.Backup;
import Persistencia.FachadaBD;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public class Fachada {

    private static Fachada instancia;
    private Dominios dicc_dom;
    private Usuarios dicc_usu;
    private Cuentas dicc_cuenta;

//    private Fachada() throws IOException, FileNotFoundException, SQLException{
    private Fachada() throws IOException {
        dicc_dom = new Dominios();
        dicc_usu = new Usuarios();
        dicc_cuenta = new Cuentas();
    }

    public void cargar_colecciones_BD() throws IOException, FileNotFoundException, SQLException {
        dicc_dom.cargar_dominios_BD();
        dicc_usu.cargar_usuarios_BD();
        dicc_cuenta.cargar_cuentas_BD();
    }

    public static Fachada getInstancia() throws IOException, FileNotFoundException, SQLException {
        if (instancia == null) {
            instancia = new Fachada();
        }
        return instancia;
    }

    public boolean login_admin(String usr, String pass) throws IOException, FileNotFoundException, SQLException {
        return new Administrador().login_admin(usr, pass);
    }

    public void alta_dominio1(String nom_dominio, int prioridad) throws IOException, FileNotFoundException, SQLException {
        FachadaBD.getInstancia().alta_dominio(nom_dominio, prioridad);
    }

    public boolean alta_dominio(String nom_dominio, int prioridad) throws IOException, FileNotFoundException, SQLException {
        Dominio dom = new Dominio(nom_dominio, prioridad);
        return this.dicc_dom.insert(dom);
    }

//    public TableModel listar_dominios_tabla() throws IOException, FileNotFoundException, SQLException{
//        String col[] = {"Nombre", "Prioridad"};
//        DefaultTableModel tb = new DefaultTableModel(col, 0);
//        
//        ResultSet rs = FachadaBD.getInstancia().get_dominios();
//        while(rs.next()){
//            String row[] = {rs.getString("nom_dominio"), rs.getString("prioridad")};
//            tb.addRow(row);
//        }
//        return tb;
//    }
    public DefaultTableModel listar_dominios_tabla() {
        return dicc_dom.listar_tabla();
    }

    public DefaultComboBoxModel listar_dominios_cb() {
        return dicc_dom.listar_combobox();
    }

    public boolean baja_dominio(String nom_dominio) throws IOException, FileNotFoundException, SQLException {
        return dicc_dom.delete(nom_dominio);
    }

    public void baja_dominio_BD(String nom_dominio) throws IOException, FileNotFoundException, SQLException {
        FachadaBD.getInstancia().baja_dominio(nom_dominio);
    }

    public boolean modif_dominio(String nom_dominio, String prioridad) throws IOException, FileNotFoundException, SQLException {
        Dominio dom = new Dominio(nom_dominio, Integer.parseInt(prioridad));
        return dicc_dom.modify(dom);
    }

    public void modif_dominio_BD(String nom_dominio, String prioridad) throws IOException, FileNotFoundException, SQLException {
        FachadaBD.getInstancia().modif_dominio_BD(nom_dominio, prioridad);
    }

    public Dominio obtener_dominio(String nom_dominio) {
        return dicc_dom.find(nom_dominio);
    }

    public boolean existe_dominio(String nom_dominio) {
        return dicc_dom.member(nom_dominio);
    }

    /*
    -------------------------Modificando desde acá:
     */
    public boolean alta_usuario(String cedula, String nombre, String apellido, String password, String celular) throws IOException, FileNotFoundException, SQLException {
        Usuario usu = new Usuario(cedula, password, nombre, apellido, celular);
        return dicc_usu.insert(usu);
    }

    public DefaultTableModel listar_usuarios_tabla() {
        return dicc_usu.listar_tabla();
    }

    public boolean baja_usuario(String cedula) throws IOException, FileNotFoundException, SQLException {
        return dicc_usu.delete(cedula);
    }

    public boolean modif_usuario(String cedula, String nombre, String apellido, String password, String celular) throws IOException, FileNotFoundException, SQLException {
        Usuario usu = new Usuario(cedula, password, nombre, apellido, celular);
        return this.dicc_usu.modify(usu);
    }

    /*
    public boolean login_admin(String usr, String pass) throws IOException, FileNotFoundException, SQLException{
        return admin.login_admin(usr, pass);
    }
    
    public void alta_dominio(Dominio d)throws IOException, FileNotFoundException, SQLException{
        dom.alta_dominio(d);
    }
    
    public void baja_dominio(String nom_dom)throws IOException, FileNotFoundException, SQLException{
        dom.baja_dominio(nom_dom);
    }
    
    public boolean existe_dominio(String nom_dom)throws IOException, FileNotFoundException, SQLException{
        return dom.existe_dominio(nom_dom);
    }
    
    public ResultSet get_dominios()throws IOException, FileNotFoundException, SQLException{
        return dom.getDominiosBD();
    }
     */
    //Llamo a la función de forma auxiliar para probar el srv:
//    public ResultSet get_cuentas_habilitadas_BD()throws IOException, FileNotFoundException, SQLException{
//        return FachadaBD.getInstancia().get_cuentas_habilitadas_BD();
//    }
    public Usuario obtener_usuario(String cedula) {
        return dicc_usu.find(cedula);
    }

    public boolean existe_usuario(String cedula) {
        return dicc_usu.member(cedula);
    }

    public boolean cambiar_pass(String cedula, String new_pass) throws IOException, FileNotFoundException, SQLException {
        return dicc_usu.cambiar_pass(cedula, new_pass);
    }

    /*
        ---CUENTA---
     */
    public boolean alta_cuenta(String nom_cuenta, String nom_dominio, String cedula) throws IOException, FileNotFoundException, SQLException {
        Dominio dominio = dicc_dom.find(nom_dominio);
        Usuario usuario = dicc_usu.find(cedula);
        Cuenta c = new Cuenta(nom_cuenta, dominio, usuario);
        return dicc_cuenta.insert(c);
    }

    public boolean baja_logica_cuenta(String nom_cuenta, String nom_dominio, String cedula) throws IOException, FileNotFoundException, SQLException {
        return dicc_cuenta.baja_logica(nom_cuenta, nom_dominio, cedula);
    }

    public boolean alta_logica_cuenta(String nom_cuenta, String nom_dominio, String cedula) throws IOException, FileNotFoundException, SQLException {
        return dicc_cuenta.alta_logica(nom_cuenta, nom_dominio, cedula);
    }

    public DefaultTableModel listar_cuentas_tabla() {
        return dicc_cuenta.listar_tabla();
    }

    public ArrayList<Cuenta> obtengo_cuentas_de_un_usuario(String cedula) throws IOException, FileNotFoundException, SQLException {
        return dicc_cuenta.obtengo_cuentas_de_un_usuario(cedula);
    }

    public boolean existe_cuenta_sin_cedula(String nom_cuenta, String nom_dominio) {
        return dicc_cuenta.member_sin_cedula(nom_cuenta, nom_dominio);
    }

    /*
        ---CUENTA---FIN---
     */
 /*
        ---CORREO---
     */
    //Prueba de correo emisor
    public void alta_correo_emisor(String nom_usuario_emisor, String nom_dominio_emisor, String cedula_usuario_emisor, String nom_usuario_receptor, String nom_dominio_receptor, String cedula_usuario_receptor, String asunto, String texto, int id_conversacion) throws IOException, FileNotFoundException, SQLException {
        //Correo correo = new Correo(nom_usuario_emisor, nom_dominio_emisor, cedula_usuario_emisor, nom_usuario_receptor, nom_dominio_receptor, cedula_usuario_receptor, fecha, asunto, texto, id_conversacion, id_conversacion);
        Correo correo = new Correo(nom_usuario_emisor, nom_dominio_emisor, cedula_usuario_emisor, nom_usuario_receptor, nom_dominio_receptor, cedula_usuario_receptor, asunto, texto, id_conversacion);
        //String nom_usuario_emisor, String nom_dominio_emisor, String cedula_usuario_emisor, String nom_usuario_receptor, String nom_dominio_receptor, String cedula_usuario_receptor, String asunto, String texto, int id_conversacion)
        Cuenta cuenta = dicc_cuenta.find(nom_usuario_emisor, nom_dominio_emisor, cedula_usuario_emisor);

        cuenta.alta_correo(correo);
    }

    public void marcar_enviado_correo(String nom_cuenta_emisor, String nom_dominio_emisor, String fecha, boolean enviado) throws IOException, FileNotFoundException, FileNotFoundException, SQLException {
        Correo c = new Correo();
        c.marcar_enviado_correo(nom_cuenta_emisor, nom_dominio_emisor, fecha, enviado);
    }

    /*ESTO ES SOLO DE PRUEBA:*/
    public void imprimir_datos_correo_emisor(String nom_cuenta_emisor, String nom_dominio_emisor, String cedula_emisor) {
        Cuenta cuenta = dicc_cuenta.find(nom_cuenta_emisor, nom_dominio_emisor, cedula_emisor);

        System.out.println(cuenta.getNom_cuenta() + " " + cuenta.getDominio().getNom_dominio());

        for (Correo correo : cuenta.getCorreos().getSetCorreos()) {
            System.out.println(correo.toString());
        }

    }

    //Método que se utiliza para obtener los correos para luego mandarlos al cliente
    public ArrayList<Correo> obtener_correos_cuenta(String nom_cuenta, String nom_dominio, String cedula) {
        return dicc_cuenta.find(nom_cuenta, nom_dominio, cedula).getCorreos().getSetCorreos();
    }

    //Marca correos de receptor como enviados ya que luego se envían al cliente
    //Este método va de la mano de obtener_correos_cuenta
    public void marcar_correos_receptor(String nom_cuenta, String nom_dominio, String cedula) {

    }

    /*
        ---CORREO---FIN---
     */
 /*
        ---CLIENTE---
     */
    public boolean login_cliente(String cedula, String password) {
        return dicc_usu.login_cliente(cedula, password);
    }

    //necesito: cedula, password, nom_cuenta, nom_dominio
    public String obtener_pass(String cedula) {
        return dicc_usu.find(cedula).getPassword();
    }

    /*
        ---CLIENTE---FIN---
     */
 /*
        ---BACKUP---
     */
    public boolean backup_mensajes() throws URISyntaxException, FileNotFoundException, IOException, InterruptedException {
        return Backup.backup_mensajes();
    }

    /*
        ---BACKUP---FIN---
     */

    public JasperPrint listado(String camino) throws IOException, FileNotFoundException, JRException, SQLException {

        return FachadaBD.getInstancia().listado(camino);
    }

    public void carpetaBD(String nombreCarpeta, String nombreCuenta, String dominio, String cedula) throws IOException, FileNotFoundException, SQLException {
       FachadaBD.getInstancia().carpetaBD(nombreCarpeta, nombreCuenta, dominio, cedula);
        
    }

}
