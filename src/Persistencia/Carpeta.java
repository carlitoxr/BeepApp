
package Persistencia;

import Conexion.Conexion;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Carpeta {
    public void carpetaBD(String nombreCarpeta,String nombreCuenta,String dominio,String cedula) throws IOException, FileNotFoundException, SQLException{
        Connection conectando = Conexion.getInstancia().conectar();
        PreparedStatement ps = conectando.prepareStatement("insert into carpeta values (?,?,?,?)");
        ps.setString(1, nombreCuenta);
        ps.setString(2,dominio);
        ps.setString(3, cedula);
        ps.setString(4, nombreCarpeta);
        
        ps.executeUpdate();
    }
    
    
}
