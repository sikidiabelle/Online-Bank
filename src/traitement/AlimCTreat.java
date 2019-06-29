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
 * Servlet de traitement de l'alimentation du compte 
 */
@WebServlet("/AlimCTreat")
public class AlimCTreat extends HttpServlet {
	private final static Logger rootLogger = Logger.getLogger(AlimCTreat.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlimCTreat() {
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
		double montant = Double.valueOf(request.getParameter("depot"));
		String numCB = (String) request.getParameter("numCB");
		String mode = (String) request.getParameter("modeAlim");
		
		String descp=""; //Description de la transaction
		String numCpt=""; //numero du compte  l'origine de l'alimentation si diffrente
		
		//Si le dpot a t ffectu par chque
		if (mode.equals("cheque")) {
			
			String numChq = request.getParameter("numCheque");
			numCpt = request.getParameter("numCompte");//Numero du compte dtenteur du chque
			descp= "Dépôt par chèque";
		}
		

		if (mode.equals("carte")) {
			descp = "Dépôt par carte";
		}
		
		//Instanciation des classes DAO
		CptDAO cptDao = new CptDAO();
		TransactionDAO transDao = new TransactionDAO();
		
		InetAddress addresse=InetAddress.getLocalHost();
		
		int a1 = cptDao.alimenter(montant, numCB);
		Transaction t = new Transaction(
				descp,
				numCB,
				numCpt,
				montant,
				true
				);
		int a2 = transDao.add(t);
		
		if (a1 == 1 && a2 == 1) {
			// Redirection vers le solde
			session.setAttribute("msg_succes", "Votre compte a été crédité avec succès de : "+montant+" EUR");
			
			MDC.put("num",numCB);
			MDC.put("montant",montant);
			MDC.put("descp",descp);
			MDC.put("ip", addresse.getHostAddress());
			rootLogger.trace("Transaction réussie");
			
			response.sendRedirect( "consulterSolde.jsp" );
		}else {
			session.setAttribute("msg_error", "Echec de la transaction");
			response.sendRedirect( "alimenterCpt.jsp" );
		}
		
		
	}
}

