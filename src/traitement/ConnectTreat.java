package traitement;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.MDC;
import org.apache.log4j.RollingFileAppender;

import dao.ClientDAO;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;


/**
 * Servlet de traitement de la connexion
 */
@WebServlet("/ConnectTreat")
public class ConnectTreat extends HttpServlet {
	
	private final static Logger rootLogger = Logger.getLogger(ConnectTreat.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectTreat() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		
		ClientDAO d = new ClientDAO();
		String mail = request.getParameter("email");
		String pwd = request.getParameter("password");
		InetAddress addresse=InetAddress.getLocalHost();
		
		HttpSession session = request.getSession();
		// Hashage d'un mot de passe
		String pwd2 = d.hash(pwd);
		if (d.checkLogin(mail, pwd2)) { //Verifie si le login et le mdp
			if(d.checkConfirmed(mail)) { //Verifie si le compte associé au mail a été confirmé
				session.setAttribute("email", mail);
				session.setAttribute("idClient", d.getId(mail));
				
				//Message de succes de la connexion a afficher
				session.setAttribute("msg_succes", "Connexion réussie!");
				
				//fichier log login
			
				MDC.put("mail",mail);
				MDC.put("ip", addresse.getHostAddress());
				rootLogger.debug("Connexion réussie");
				
				//Redirection vers page des infos perso
				response.sendRedirect("InfosPerso.jsp");
			} else {//Email non confirmé
				//Message d'echec a afficher
				session.setAttribute("msg_error", "Veuillez d'abord confirmer votre inscription"
						+ " avec le lien fourni à l'adresse : "+mail+" avant de vous connecter!");
				response.sendRedirect( "Login.jsp" );
				
			}
		} else {
			//Message d'echec a afficher
			session.setAttribute("msg_error", "Echec de la connexion: email ou mot de passe invalide!");
			
			//fichier log
			MDC.put("mail",mail);
			MDC.put("ip", addresse.getHostAddress());
			rootLogger.debug("Tentative de connexion échoué");
			
			//redirection
			response.sendRedirect( "Login.jsp" );
		}
		
		
		
		
		
		
	}
}