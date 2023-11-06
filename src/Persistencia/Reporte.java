package Persistencia;

import Conexion.Conexion;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class Reporte {

    JasperPrint listado(String camino) throws IOException, FileNotFoundException, JRException, SQLException {

        JasperReport reporteD = (JasperReport) JRLoader.loadObjectFromFile(camino);
        Connection conectando = Conexion.getInstancia().conectar();
        return JasperFillManager.fillReport(reporteD, null, conectando); //Imprime Reporte
    }
}
