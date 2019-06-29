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
 * Servlet de traitement de la modification du mot de passe d'un client
 */
@WebServlet("/motdpTreat")
public class motdpTreat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public motdpTreat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		ClientDAO d = new ClientDAO(); int id = d.getId(email);
		Client cl = d.getInfos(id);
		String m1 = request.getParameter("password1");
		// Hashage d'un mot de passe
		String pwd1 = d.hash(m1);
		String m2 = request.getParameter("password2");
		// Hashage d'un mot de passe
		String pwd2 = d.hash(m2);
		String m3 = request.getParameter("password3");
		boolean b = d.checkLogin(email, pwd1);
		

		if(b == true) {
			cl.setMdp(pwd2);
			d.modif(cl);
			session.setAttribute("msg_succes", "Votre mot de passe a été modifié avec succès");
			response.sendRedirect( "InfosPerso.jsp" );
		}else
		if(b == false) {
			//L'ancien mot de passe renseigné est introuvable
			session.setAttribute("msg_error", "Le mot de passe renseigné est introuvable");
			response.sendRedirect( "modifierMDP.jsp" );
		
		} 
			
		
	}
	

}
