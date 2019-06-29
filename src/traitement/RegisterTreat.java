package traitement;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ClientDAO;
import model.Client;

/**
 * Servlet de traitement de l'inscription d'un client
 */
@WebServlet("/RegisterTreat")
public class RegisterTreat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterTreat() {
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
		HttpSession session = request.getSession();
		ClientDAO c1 = new ClientDAO();
		char civ = request.getParameter("sex").charAt(0);
		String adr = request.getParameter("address");
		java.sql.Date bday = java.sql.Date.valueOf(request.getParameter("birth"));
		String city = request.getParameter("city");
		String email = request.getParameter("email");
		String pwd = request.getParameter("password");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String pays = request.getParameter("pays");
		String nat = request.getParameter("nationality");
		String tel = request.getParameter("tel");
		String cP = request.getParameter("codeP");
		
		// Hashage d'un mot de passe
		String hash = c1.hash(pwd);
		
		Client cl = new Client(civ, nom, prenom, adr, bday,
				email, hash, pays, city, nat, cP, tel);

		c1.add(cl);
		
		session.setAttribute("idClient", c1.getId(email));
		
		session.setAttribute("choix", request.getParameter("choix"));
		
		if(request.getParameter("choix").equals("1")) {
			response.sendRedirect("CompteTitre.jsp");
		} else {
			response.sendRedirect("CompteBancaire.jsp");
		}
	}

}