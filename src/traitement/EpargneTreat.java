package traitement;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CptDAO;
import dao.CptEpargneDAO;
import model.CEpargne;

/**
 * Servlet de traitement de la création de compte epargne
 */
@WebServlet("/EpargneTreat")
public class EpargneTreat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EpargneTreat() {
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

		String cpt = request.getParameter("compte");
		String solde = request.getParameter("solde");
		double s = Double.parseDouble(solde);
		
		CptDAO c = new CptDAO();
		int a=0, a1=0;
		CptEpargneDAO cep = new CptEpargneDAO();
		int id = cep.idClient(cpt);
		if (!c.UniEpargne(id)) { //Si client ne possède pas encore de compte épargne
			int check = cep.checkCpt(cpt);
			if (check == 1) {
				String rib = c.createRib();
				String num = c.createnumcpt();
				String iban = "FR003003025890"+num+rib;
				double t = 0.5; // valeur par défaut lors de la création
	
				CEpargne ce = new CEpargne(num, rib, iban, id, s, t);
				 a1 = c.ajout(ce);
				 a = cep.ajoutEp(ce);
			} else 
				response.getWriter().append("<h1>"+"Le numéro de compte bancaire n'existe pas"+"</h1>");
		} else { //Si le client possède déjà un compte epargne
			//Message a afficher
			session.setAttribute("msg_error", "Vous n'avez pas le droit de créer plus d'un compte epargne");
			response.sendRedirect( "index.jsp" );
		}	
		if(a1 == 1 && a == 1) {
			session.setAttribute("msg_succes", "Votre compte epargne a bien été crée ");
			response.sendRedirect( "InfosPerso.jsp" );
		}
	}

}
