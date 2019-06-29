package traitement;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CarteDAO;
import dao.CheqDAO;
import dao.CptDAO;
import model.CBancaire;
import model.Carte;
import model.Cheque;

/**
 * Servlet de traitement de l'ajout d'un compte bancaire
 */
@WebServlet("/AddBancaireTreat")
public class AddBancaireTreat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBancaireTreat() { super(); }

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
		String solde = request.getParameter("solde");
		double s = Double.parseDouble(solde);
		String pr = request.getParameter("plafondRetrait");
		int plr = Integer.parseInt(pr);
		String pv = request.getParameter("plafondVirement");
		int plv = Integer.parseInt(pv);
		String pd = request.getParameter("plafondDecouvert");
		int pld = Integer.parseInt(pd);


		CptDAO c = new CptDAO();
		
		if (!c.UniBancaire(id)) { //Si client ne possède pas encore de compte bancaire
			String rib = c.createRib();
			String num = c.createnumcpt();
			String iban = "FR003003025891"+num+rib;
			
			//Verifie si les checkbox pour avoir un chequier et une carte ont t cochs
			boolean cheq = request.getParameter( "chBx_chequier" ) != null;
			boolean carte = request.getParameter( "chBx_carte" ) != null;
	
			CBancaire cb = new CBancaire(num, rib, iban, id, s, plv, plr, pld, carte, cheq);
			
			// Ajout des 2 comptes
			c.ajout(cb);
			c.ajout2(cb);
			
			//Ajout de la carte si choisie
			if (carte==true) {
				CarteDAO car = new CarteDAO();
				String nc = car.createnum();
				String n = car.dateExpir();
				String t = car.cvc();
				Carte ca = new Carte(nc,num,n,t);
				car.AjoutCarte(ca);
			
			}
			//Ajout du cheque si choisie
			if (cheq==true) {
				CheqDAO ch = new CheqDAO();
				String nc = ch.createCheq();
				Cheque ca = new Cheque(num,nc);
				ch.AjoutCheq(ca);
			}
			
			// Si l'envoi du mail réussi => Redirection vers accueil
			session.setAttribute("msg_succes", "Creation du compte réussie.");
			response.sendRedirect( "index.jsp" );
		} else { //Si client possède déjà un compte bancaire
			//Message a afficher
			session.setAttribute("msg_error", "Vous n'avez pas le droit de créer plus d'un compte bancaire");
			response.sendRedirect( "index.jsp" );
		}
	}
}
