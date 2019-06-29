package dao;
import java.sql.Connection;
import java.util.*;
import java.util.Calendar;

import model.Cheque;

import static java.lang.System.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Classe liant notre appli à la table cheque de la BDD
 * @author TIC1.3
 *
 */
public class CheqDAO extends DAO {
	
	/**
	 * Permet de générer un numro aléatoire de 7 chiffres 
	 * @return le numero du cheque
	 */
	public String createCheq() {
	    String chars = "1234567890"; 
	    String pass = "";
	    
	    for(int x=0;x<7;x++)   {
	    	int i = (int)Math.floor(Math.random() * 5); 
	    	pass += chars.charAt(i);
	    }

	    
		Connection con = null;
		PreparedStatement ps = null;
		
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			ps = con.prepareStatement("SELECT * "
					+ "FROM chequier WHERE numCheq=?");
			ps.setString(1, pass);
		    ResultSet rs = ps.executeQuery();
		    
		    if (rs.next()) pass = createCheq();
		    
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// fermeture du preparedStatement et de la connexion
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return pass;
	}
	
	/**
	 * Permet d'ajouter un chéquier 
	 * @param c le chequier à ajouter
	 * @return le nombre de lignes ajoutées dans la BDD
	 */
	public int AjoutCheq(Cheque c) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			ps = con.prepareStatement("INSERT INTO chequier "
					+ "(numCpt, numCheq)"
					+ " VALUES (?,?)");

			ps.setString(1, c.getnumCpt());
			ps.setString(2, c.getnumCheq());

			// Excution de la requte
			retour = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// fermeture du preparedStatement et de la connexion
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return retour;
	}
	
	public static void main(String[] args) {
		CheqDAO c = new CheqDAO();
		String nc = c.createCheq();
		Cheque ca = new Cheque("ACDDEA552142",nc);
		int add = c.AjoutCheq(ca);
		System.out.println(add);
	}

}

