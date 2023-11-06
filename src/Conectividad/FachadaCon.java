package Conectividad;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.icegreen.greenmail.user.UserException;

public class FachadaCon {

	private static FachadaCon instancia;
	
	//Esta clase utiliza el patron Singleton
	public static FachadaCon getInstancia(){
		if (instancia == null)
			instancia = new FachadaCon();
			
		return instancia;
	}
	
	// Incia el Servicio que escucha en un puerto TCP esperando el mail
	public void InciaServ() throws SQLException, AddressException, MessagingException, UserException, IOException{
		SmtpServ P = SmtpServ.getInstancia();
		P.setUp();
		if(!P.isAlive())
			P.start();
		
	}
	
	public void DetenerServ() throws SQLException{
		SmtpServ P = SmtpServ.getInstancia();
		P.tearDown();
	}
	
	public void InciaCli() throws SQLException, IOException, MessagingException, UserException, InterruptedException{
//		PruebaCli C = new PruebaCli();
//		SmtpServ P = SmtpServ.getInstancia();
//		C.Enviar();
//		P.setMailsBD();
	}
	
	public void ObtenerMail() throws IOException, MessagingException, UserException, InterruptedException, SQLException{
		SmtpServ P = SmtpServ.getInstancia();
		P.getMailsBD();
	}
	
	public void IniSocket() throws SQLException{
		InfoSocket I = InfoSocket.getInstancia();
		I.start();
	}
	
	public void IniPOPServ() throws SQLException, IOException{
		POP3Serv T = POP3Serv.getInstancia();
		T.setUp();
	}
	
	public void DetPOPServ() throws SQLException{
		POP3Serv T = POP3Serv.getInstancia();
		T.tearDown();
	}
}
