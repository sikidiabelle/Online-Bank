package traitement;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import dao.CptDAO;
import dao.TransactionDAO;
import model.Transaction;

/**
 * Servlet de traitement de virement bancaire
 */
@WebServlet("/VirTreat")
public class VirTreat extends HttpServlet {
	private final static Logger rootLogger = Logger.getLogger(VirTreat.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VirTreat() {
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

		InetAddress addresse=InetAddress.getLocalHost();
		String depot = request.getParameter("depot");
		Double montant = Double.parseDouble(depot);
		String c = request.getParameter("numCB");
		String d = request.getParameter("dest");
		String d1=d.substring(14);
		d1=d1.substring(0,12);
		TransactionDAO td = new TransactionDAO();
		int a1=0;
		CptDAO cpt = new CptDAO();
		int a2 =cpt.alimenter(-montant, c); //debiter compte source
		Transaction t1 = new Transaction("Virement",c,d,montant,false);
		int a4 = td.add(t1);
		int a3=0;

		if (cpt.checkcpt(d)==1) {
			a1=cpt.alimenter(montant, d1); //crediter compte destinataire
			Transaction t2 = new Transaction("Dépôt",d1,c,montant,true);
			a3 = td.add(t2);

				if (a1 == 1 && a2 == 1) {
					// Redirection vers le solde
					session.setAttribute("msg_succes", "Le virement a bien été effectué");
					
					MDC.put("num",c);
					MDC.put("descp","Dépôt");
					MDC.put("montant",montant);
					MDC.put("ip", addresse.getHostAddress());
					rootLogger.trace("Transaction réussie");
					
					response.sendRedirect( "EffectuerVir.jsp" );
					
				}else {
					session.setAttribute("msg_error", "Il y a eu une erreur");
					
					MDC.put("num",c);
					MDC.put("montant",montant);
					MDC.put("descp","Dépôt");
					MDC.put("ip", addresse.getHostAddress());
					rootLogger.trace("Transaction échouée");
					
					response.sendRedirect( "EffectuerVir.jsp" );
				}
		}else {
			if (a2 == 1) {
				// Redirection vers le solde
				session.setAttribute("msg_succes", "Le virement a bien été effectué");
				
				MDC.put("num",c);
				MDC.put("montant",montant);
				MDC.put("descp","Dépôt");
				MDC.put("ip", addresse.getHostAddress());
				rootLogger.trace("Transaction échouée");
				
				response.sendRedirect( "EffectuerVir.jsp" );
			}else {
				session.setAttribute("msg_error", "Il y a eu une erreur");
				
				MDC.put("num",c);
				MDC.put("montant",montant);
				MDC.put("descp","Dépôt");
				MDC.put("ip", addresse.getHostAddress());
				rootLogger.trace("Transaction échouée");
				
				response.sendRedirect( "EffectuerVir.jsp" );
			}
		}
		
	}

}
