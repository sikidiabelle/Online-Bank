package traitement;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CompteTitreDAO;
import dao.CptDAO;
import model.Ctitre;

/**
 * Servlet de traitement de l'ajout d'un compte titre
 */
@WebServlet("/AddTitreTreat")
public class AddTitreTreat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTitreTreat() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

		HttpSession session = request.getSession();

		int id = (int) session.getAttribute( "idClient" );
		String nif=request.getParameter("NIF");
		String residence=request.getParameter("pays");
		String iban;
		
		CptDAO c =new CptDAO();
		if (!c.UniTitre(id)) { //Si le client ne possède pas de compte titre
			CompteTitreDAO c2 = new CompteTitreDAO();
			String numeroCompte = c.createnumcpt();
			String rib = c.createRib();
			iban = "FR003003025892"+numeroCompte+rib;
			Ctitre ct = new Ctitre(numeroCompte, rib,iban, id , nif,0,residence);
			
			//Création du compte
			c.ajout(ct);
			//Création du compte titre
			c2.ajouter(ct);
			
			//Message de succes a afficher
			session.setAttribute("msg_succes", "Creation du compte réussie.");
			response.sendRedirect( "index.jsp" );

		} else { //Si le client possède déjà un compte titre
			//Message a afficher
			session.setAttribute("msg_error", "Vous n'avez pas le droit de créer plus d'un compte titre");
			response.sendRedirect( "index.jsp" );
		}
	}
	
}