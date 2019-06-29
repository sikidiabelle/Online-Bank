package traitement;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ClientDAO;
import dao.CompteTitreDAO;
import dao.CptDAO;
import model.Client;
import model.Ctitre;

/**
 * Servlet de traitement de la cr�ation d'un compte titre
 */
@WebServlet("/TitreTreat")
public class TitreTreat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TitreTreat() {
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
		session.removeAttribute("idClient");
		String nif=request.getParameter("NIF");
		String residence=request.getParameter("pays");
		String iban;
		
		CptDAO c =new CptDAO();
		if (!c.UniTitre(id)) { //Si le client ne poss�de pas de compte titre
			CompteTitreDAO c2 = new CompteTitreDAO();
			String numeroCompte = c.createnumcpt();
			String rib = c.createRib();
			iban = "FR003003025892"+numeroCompte+rib;
			Ctitre ct = new Ctitre(numeroCompte, rib,iban, id , nif,0,residence);
			//Cr�ation du compte
			int add1 = c.ajout(ct);
			//Cr�ation du compte titre
			int add2 = c2.ajouter(ct);
			
			//Get infos client
			ClientDAO d = new ClientDAO();
			Client cl = d.getInfos(id);
			
			//Envoi du mail de confirmation d'inscription
			String text = "Cher Mr "+cl.getNom()+",\n"; //Contenu du mail
			
			//Definition de la cvilit� � partir de la valeur renseign�e
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
			text += "<h1 align=\"center\"><strong>BANKTIC - Your online bank</strong></h1>";
			text += "<p>Votre demande d'ouverture de compte est termin�e. "
					+ "Les infos suivantes ont �t� renseign�es pour cette adresse mail :"+"</p>";
			
			//Infos saisies dans le formulaire
			text += "<p>*** Prenom NOM : "+cl.getPrenom()+" "+cl.getNom()+"</p>";
			text += "<p>*** Date de naissance : "+cl.getBday()+"</p>";
			text += "<p>*** Civilit� : "+civ+"</p>";
			text += "<p>*** Nationalit� : "+cl.getNat()+"</p>";
			text += "<p>*** Adresse : "+cl.getAdr()+", "+cl.getCP()+" - "+cl.getCity()+", "
					+cl.getPays()+"</p>";
			text += "<p>*** T�l�phone : "+cl.getTel()+"</p>";
			
			text += "<p>Veuillez confirmer votre inscription en suivant ce lien : ";
			
			//Ajout URL confirmation
			url +="?alpha="+cl.getIdClient()+"&kword="+key;
			text += "<a href=\"http://"+url+"\">"
							+ "Confirmer</a></p>";
			
			text += "<p>Si vous n'�tes pas � l'oigine de cette inscription, veuillez ignorer ce mail.</p>"
					+ "<p>Cordialement,</p>"+"<p>BANKTIC</p>";
			
			MailSender m = new MailSender(cl.getEmail(), "Confirmation de votre inscription - BANKTIC", text);
			boolean b = m.send();
			
			// Si l'envoi du mail r�ussi => Redirection vers accueil
			if (b && (add1 ==1) && (add2 ==1)) {//Envoi mail ajout des comptes reussi
				//Ajout de la cl� de validation et l'id � la base de donn�es
				int addKey = d.addKeyClient(cl.getIdClient(), key);
				
				if(addKey==1) {
					//Message de succes a afficher
					session.setAttribute("msg_succes", "Creation du compte r�ussie. Un mail de confirmation "
							+ "d'inscription vous a �t� envoy� � l'adresse : "+cl.getEmail()
							+". Veuillez valider votre inscription avant de vous connecter!");
					response.sendRedirect( "index.jsp" );
				}
			} else {
				//Message d'echec a afficher
				session.setAttribute("msg_error", "Echec lors de la creation du compte.");
				response.sendRedirect( "CompteTitre.jsp" );
			}

		} else { //Si le client poss�de d�j� un compte titre
			//Message a afficher
			session.setAttribute("msg_error", "Vous n'avez pas le droit de cr�er plus d'un compte titre");
			response.sendRedirect( "index.jsp" );
		}
	}
	
}