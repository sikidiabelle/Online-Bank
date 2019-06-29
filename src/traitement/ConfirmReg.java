package traitement;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ClientDAO;

/**
 * Servlet de traitement de la confirmation de compte
 */
@WebServlet("/ConfirmReg")
public class ConfirmReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmReg() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("<h1>A developper!!!</h1>");
		try {
			int id = Integer.valueOf(request.getParameter("alpha"));
			String key = (String) request.getParameter("kword");
			
			response.getWriter().append("<h1>"+id+"</h1>");
			response.getWriter().append("<h1>"+key+"</h1>");
			
			HttpSession session = request.getSession();
			
			ClientDAO cd = new ClientDAO();
			
			if(cd.checkKeyClient(id, key)) { //Verifie si le la clé de validation correspond
				int conf = cd.confirmClient(id);
				
				if (conf==1) {//Confirmation du lien réussie
					//Message de succes de la connexion a afficher
					session.setAttribute("msg_succes", "Confirmation réussie! Vous pouvez désormais vous connecter!");
					cd.removeKeyClient(id);
					
					//Redirection vers page des infos perso
					response.sendRedirect("Login.jsp");
				}
			} else {//La clé fournie ne coorespond pas => Redirection vers l'accueil avec message erreur
				//Message d'echec a afficher
				session.setAttribute("msg_error", "L'URL de confirmation renseigné n'est pas (ou n'est plus) valide!");
				response.sendRedirect( "index.jsp" );
			}
			
		} catch(Exception e) {}
	}
}