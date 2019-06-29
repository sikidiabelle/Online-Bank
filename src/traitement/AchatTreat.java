package traitement;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ActionDAO;
import dao.CptDAO;
import model.Action;
import model.Actionnaire;

/**
 * Servlet de traitement de l'achat d'action  
 */
@WebServlet("/AchatTreat")
public class AchatTreat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AchatTreat() {
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
		
		CptDAO cptDAO = new CptDAO();
		ActionDAO actDAO = new ActionDAO();
		
		HttpSession session = request.getSession();
		int idClient = (int) session.getAttribute("idClient");
		
		//Recuperation du numm�ro de compte
		String numCpt = cptDAO.getNumCptTitre(idClient);
		
		//R�cup�ration de l'idAction et quantite
		int idAction = Integer.valueOf(request.getParameter("idAction"));
		int quantite = Integer.valueOf(request.getParameter("qteAction"));
		
		//R�cup�ration du prix de l'action
		Action act = actDAO.getAction(idAction);
		double prix = act.getPrix();
		
		//Calcul du montant pr�vu
		double montant = prix * ((double) quantite);
		
		//R�cup�ration du solde du compte
		double solde = cptDAO.getSolde(numCpt);
		
		if(solde<montant) { // si le capital du compte est insuffisant (inf�rieur au montant de l'achat)
			session.setAttribute("msg_error", "Le solde de votre compte ne vous permet pas d'effectuer cet achat!");
			response.sendRedirect("acheterAction.jsp");
		} else { // si le capital du compte est suffisant (sup�rieur ou �gal au montant de l'achat)
			Actionnaire a = new Actionnaire(idAction, numCpt, quantite);
			actDAO.acheter(a);
			session.setAttribute("msg_succes", "Achat effectu� avec succ�s!");
			response.sendRedirect("acheterAction.jsp");
		}
	}

}
