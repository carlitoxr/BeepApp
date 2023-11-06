
package Conexion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {
    
    private static Conexion instancia;
    private Connection con;
    private static String f_ini = "src/Conexion/config.ini";

    private Conexion() {
    }

    public static Conexion getInstancia() {
        if(instancia == null)
            instancia = new Conexion();
        return instancia;
    }

    public Connection conectar() throws FileNotFoundException, IOException, SQLException {
        Properties prop = new Properties();
        prop.load(new FileInputStream(f_ini));
        
        //Se cargan los String auxiliares con los datos correspondientes a los campos en el iniq
        //utilizando prop
        String db = prop.getProperty("db");
        String ipsrv = prop.getProperty("ipsrv");
        String port = prop.getProperty("port");
        String conf = prop.getProperty("conf");
        String usr = prop.getProperty("username");
        String pass = prop.getProperty("pass");
        
        //Genero el url de conexión
        String url = "jdbc:mysql://" + ipsrv + ":" + port + "/" + db + conf;
        
        System.out.println(url + " " + usr + " " + pass);
        
        //Se abre la conexión
        this.con = DriverManager.getConnection(url, usr, pass);
        
        return con;
    }
    
    public void desconectar() throws SQLException{
        if(this.con != null)
            this.con.close();
    }
    
}
