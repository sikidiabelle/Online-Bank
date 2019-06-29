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
import dao.ClientDAO;
import dao.CptDAO;
import model.CBancaire;
import model.Carte;
import model.Cheque;
import model.Client;

/**
 * Servlet de traitement de la création de compte bancaire
 */
@WebServlet("/BancaireTreat")
public class BancaireTreat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BancaireTreat() { super(); }

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
		session.removeAttribute("idClient");
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
			
			int add1 =c.ajout(cb);
			int add2 = c.ajout2(cb);
			
			if (carte==true) {
				CarteDAO car = new CarteDAO();
				String nc = car.createnum();
				String n = car.dateExpir();
				String t = car.cvc();
				Carte ca = new Carte(nc,num,n,t);
				int add = car.AjoutCarte(ca);
			
			} 
			if (cheq==true) {
				CheqDAO ch = new CheqDAO();
				String nc = ch.createCheq();
				Cheque ca = new Cheque(num,nc);
				int add = ch.AjoutCheq(ca);
			}
			//Get infos client
			ClientDAO d = new ClientDAO();
			Client cl = d.getInfos(id);
			
			//Envoi du mail de confirmation d'inscription
			String text = "Cher/Chère "+cl.getNom()+",\n"; //Contenu du mail
			
			//Definition de la cvilit  partir de la valeur renseigne
			String civ = "";
			switch (cl.getCiv()) {
				case 'H': civ="Homme"; break;
				case 'F': civ="Femme"; break;
			}
			
			//URL du site
			String url = "localhost:8081/Bank/ConfirmReg";
			
			//Cle de securisation de l'URL
			String key = c.createnumcpt();
			
			//Preparation du contenu du mail
			text += "<p>Votre demande d'ouverture de compte est termine. "
					+ "Les infos suivantes ont t renseignes pour cette adresse mail :"+"</p>";
			
			//Infos saisies dans le formulaire
			text += "<p>*** Prenom NOM : "+cl.getPrenom()+" "+cl.getNom()+"</p>";
			text += "<p>*** Date de naissance : "+cl.getBday()+"</p>";
			text += "<p>*** Civilit : "+civ+"</p>";
			text += "<p>*** Nationalit : "+cl.getNat()+"</p>";
			text += "<p>*** Adresse : "+cl.getAdr()+", "+cl.getCP()+" - "+cl.getCity()+", "
					+cl.getPays()+"</p>";
			text += "<p>*** Téléphone : "+cl.getTel()+"</p>";
			
			text += "<p>Veuillez confirmer votre inscription en suivant ce lien : ";
			
			//Ajout URL confirmation
			url +="?alpha="+cl.getIdClient()+"&kword="+key;
			text += "<a href=\"http://"+url+"\">"
							+ "Confirmer</a></p>";
			
			text += "<p>Si vous n'tes pas  l'origine de cette inscription, veuillez ignorer ce mail.</p>"
					+ "<p>Cordialement,</p>"+"<p>BANKTIC</p>";
			
			MailSender m = new MailSender(cl.getEmail(), "Confirmation de votre inscription - BANKTIC", text);
			boolean b = m.send();
			
			// Si l'envoi du mail réussi => Redirection vers accueil
			if (b && (add1 ==1) && (add2 ==1)) {//Envoi mail ajout des comptes reussi
				//Ajout de la cl de validation et l'id  la base de donnes
				int addKey = d.addKeyClient(cl.getIdClient(), key);
				
				if(addKey==1) {
					//Message de succes a afficher
					session.setAttribute("msg_succes", "Creation du compte russie. Un mail de confirmation "
							+ "d'inscription vous a été envoyé à l'adresse : "+cl.getEmail()
							+". Veuillez valider votre inscription avant de vous connecter!");
					response.sendRedirect( "index.jsp" );
				}
			} else {
				//Message d'echec a afficher
				session.setAttribute("msg_error", "Echec lors de la creation du compte.");
				response.sendRedirect( "CompteBancaire.jsp" );
			}
		} else { //Si client possède déjà un compte bancaire
			//Message a afficher
			session.setAttribute("msg_error", "Vous n'avez pas le droit de créer plus d'un compte bancaire");
			response.sendRedirect( "index.jsp" );
		}
	}
}
