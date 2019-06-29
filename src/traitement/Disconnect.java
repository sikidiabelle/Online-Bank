package traitement;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet de traitement de la d�connexion
 */
@WebServlet("/Disconnect")
public class Disconnect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Disconnect() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String mailOnline = (String) session.getAttribute("email");
		if (mailOnline != null) {
			
			//Suppression de la session
			session.removeAttribute("email");
			session.removeAttribute("idClient");
			
			// Redirection vers l'acceuil
			session.setAttribute("msg_succes", "D�connexion r�ussie!");
			response.sendRedirect( "index.jsp" );
		} else {
			// L'utilisateur est d�j� hors ligne
			session.setAttribute("msg_error", "Vous �tes d�j� hors ligne!");
			response.sendRedirect( "index.jsp" );
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
