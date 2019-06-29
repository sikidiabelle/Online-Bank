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
import model.Actionnaire;

/**
 * Servlet de traitement de la vente d'action
 */
@WebServlet("/VenteTreat")
public class VenteTreat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VenteTreat() {
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
		
		int idActionnaire = Integer.valueOf(request.getParameter("idActionnaire"));
		
		actDAO.vendre(idActionnaire);
		
		session.setAttribute("msg_succes", "Vente effectué avec succès!");
		response.sendRedirect("vendreAction.jsp");
	}

}
