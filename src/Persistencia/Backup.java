
package Persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Backup {
    private static String f_ini = "src/Conexion/config.ini";
    private static String table = "correo";
    
    public static boolean backup_mensajes() throws URISyntaxException, FileNotFoundException, IOException, InterruptedException{
        Properties prop = new Properties();
        prop.load(new FileInputStream(f_ini));

        CodeSource codeSource = FachadaBD.class.getProtectionDomain().getCodeSource();
        File f = new File(codeSource.getLocation().toURI().getPath());
        String dir = f.getParentFile().getPath();

        String path = dir + "\\backup";

        File f1 = new File(path);
        f1.mkdir();

        String save = "\"" + dir + "\\backup\\" + "backup.sql\"";

        String db = prop.getProperty("db");
        String usr = prop.getProperty("username");
        String pass = prop.getProperty("pass");
        
        String cmd = "C:\\XamppBD\\mysql\\bin\\mysqldump -u " + usr + "  -p" + pass + " --database " + db + " " + table + " -r " + save;
        System.out.println("" + cmd);

        Process p = Runtime.getRuntime().exec(cmd);
        int finalizado = p.waitFor();
        System.out.println("" + finalizado);
        if(finalizado == 0 || finalizado == 2)
            return true;
        else
            return false;
    }
}
