package traitement;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MessageDAO;
import model.Message;

/**
 * Servlet de traitement de l'ajout d'une question à la BDD
 */
@WebServlet("/QuestionTreat")
public class QuestionTreat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionTreat() {
        super();
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
		
		HttpSession session = request.getSession();
		Message ms = new Message(
				request.getParameter("login"),
				request.getParameter("object"),
				request.getParameter("message")
				);
		MessageDAO d = new MessageDAO();
		int rt = d.add(ms);
		
		if (rt == 1) {
			session.setAttribute("msg_succes", "Message soumis avec succès!");
			response.sendRedirect("AskQuestion.jsp");
		} else {
			session.setAttribute("msg_error", "Erreur lors de l'envoi du message!");
			response.sendRedirect("AskQuestion.jsp");
		}
	}

}
