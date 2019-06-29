package traitement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TransactionDAO;
import model.Transaction;

/**
 * Servlet de traitement du téléchargement de fichier csv
 */
@WebServlet("/downloadCSV")
public class DownloadCSV extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadCSV() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recupere le numero du compte dont on souhaite telecharger le CSV
		HttpSession s = request.getSession();
		String numCpt = (String) request.getParameter("numCpt");
		
		//Recuperation des transaction du compte
		TransactionDAO td = new TransactionDAO();
		ArrayList<Transaction> tList = td.getTransactions(numCpt);
		
		//Creation du fichier csv dont le nom est le numero de compte
		GenerateCSV g = new GenerateCSV();
		String filePath=getServletConfig().getServletContext().getRealPath("/csv/");
		g.writeDataAtOnce(filePath, numCpt, tList);
		File f = new File(filePath, numCpt+".csv");
		
		// Téléchargement du fichier
		
		//Definition du fichier csv
		String type = "text/csv";
		
		//Initialisation de la réponse HTTP
		int DEFAULT_BUFFER_SIZE = 10240; //10ko
		
		response.reset();
		response.setBufferSize(DEFAULT_BUFFER_SIZE);
		response.setContentType(type);
		response.setHeader( "Content-Length", String.valueOf(f.length() ) );
		response.setHeader( "Content-Disposition", "attachment; filename=\"" +f.getName() + "\"" );
		
		//Preparation et ouverture des flux pour le lancement du telechargement
		OutputStream out = response.getOutputStream();
		FileInputStream in = new FileInputStream(f);
		byte[] buffer = new byte[4096];
		int length;
		while ((length = in.read(buffer)) > 0){
			out.write(buffer, 0, length);
		}
		in.close();
		out.flush();
		
		//Suppression du fichier
		f.delete();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
