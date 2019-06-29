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
 * Servlet de traitement du débit
 */
@WebServlet("/DebitTreat")
public class DebitTreat extends HttpServlet {
	private final static Logger rootLogger = Logger.getLogger(DebitTreat.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DebitTreat() {
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
		double montant = Double.valueOf(request.getParameter("debit"));
		String numCB = (String) request.getParameter("numCB");
		String mode = (String) request.getParameter("mode");
		InetAddress addresse=InetAddress.getLocalHost();
		
		String descp=""; //Description de la transaction
		
		//Si le dpot a t ffectu par chque
		if (mode.equals("pch")) {
			
			String numChq = request.getParameter("numCheque");
			descp= "Paiement par chèque";
		}
		
		//Si le dpot a t ffectu en espce
		if (mode.equals("pcb")) {
			descp = "Paiement par CB";
		}
		
		//Si le dépot a été effectué en carte
		if (mode.equals("retrait")) {
			descp = "Retrait";
		}
		CptDAO cptDao = new CptDAO();
		TransactionDAO transDao = new TransactionDAO();
		
		int a1 = cptDao.alimenter(-montant, numCB);
		Transaction t = new Transaction(
				descp,
				numCB,
				"",
				montant,
				false
				);
		int a2 = transDao.add(t);
		
		if (a1 == 1 && a2 == 1) {
			// Redirection vers le solde
			session.setAttribute("msg_succes", "Votre compte a été débité avec succès de : "+montant+" EUR");
			
			MDC.put("num",numCB);
			MDC.put("montant",montant);
			MDC.put("descp",descp);
			MDC.put("ip", request.getRemoteAddr());
			rootLogger.trace("Transaction réussie");
			
			response.sendRedirect( "consulterSolde.jsp" );
		}
		
		
	}

}
