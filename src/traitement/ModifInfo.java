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
 * Servlet de traitement de la modification des infos client
 */
@WebServlet("/ModifInfo")
public class ModifInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifInfo() {
        super();
        // TODO Auto-generated constructor stub
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
		HttpSession s = request.getSession();
		String email = (String) s.getAttribute("email");
		ClientDAO d = new ClientDAO(); Client cl = d.getInfos(d.getId(email));
		

		int idClient = cl.getIdClient();
		char civ = cl.getCiv();
		
		String adr = request.getParameter("address");
		java.sql.Date bday = cl.getBday();
		String city = request.getParameter("city");
		String mdp = cl.getMdp();
		String nom = cl.getNom();
		String prenom = cl.getPrenom();
		String pays = cl.getPays();
		String nat = cl.getNat();
		String tel = request.getParameter("tel");
		String cP = request.getParameter("codeP");
		
		ClientDAO dao = new ClientDAO();
		Client client = new Client(idClient, civ, nom, prenom, adr, bday,
				email, mdp, pays, city, nat, cP, tel);
		int rt = d.modif(client);
		if(rt == 1) {
		s.setAttribute("msg_succes", "Vos informations ont été modifiées avec succès");
		response.sendRedirect( "InfosPerso.jsp" );
		}
		else {
			s.setAttribute("msg_error", "Il y a eu un problème !! ");
			response.sendRedirect( "modifierInfos.jsp" );
		}
	}

}
