
package Logica;

public class Usuario {
    private String cedula;
    private String password;
    private String nombre;
    private String apellido;
    private String celular;

    public Usuario(String cedula, String password, String nombre, String apellido, String celular) {
        this.cedula = cedula;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
    }

    public String getCedula() {
        return cedula;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
