package dao;
import java.sql.Connection;
import java.util.*;

import model.Carte;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Classe liant notre appli à la table carte de la BDD
 * @author TIC1.3
 *
 */
public class CarteDAO extends DAO {
	
	/**
	 * Permet de générer un numéro de carte aléatoire de 16 chiffres 
	 * @return le numero de carte généré
	 */
	public String createnum() {
	    String chars = "1234567890"; 
	    String pass = "";
	    
	    for(int x=0;x<16;x++)   {
	    	int i = (int)Math.floor(Math.random() * 5); 
	    	pass += chars.charAt(i);
	    }

	    
		Connection con = null;
		PreparedStatement ps = null;
		
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			ps = con.prepareStatement("SELECT * "
					+ "FROM carte WHERE numCrt=?");
			ps.setString(1, pass);
		    ResultSet rs = ps.executeQuery();
		    
		    if (rs.next()) pass = createnum();
		    
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
	 * Permet de générer aléatoirement un cvc 
	 * @return le cvc généré
	 */
	public String cvc() {
	    String chars = "1234567890"; 
	    String pass = "";
	    
	    for(int x=0;x<3;x++)   {
	    	int i = (int)Math.floor(Math.random() * 5); 
	    	pass += chars.charAt(i);
	    }

	    
		Connection con = null;
		PreparedStatement ps = null;
		
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			ps = con.prepareStatement("SELECT * "
					+ "FROM carte WHERE cvc=?");
			ps.setString(1, pass);
		    ResultSet rs = ps.executeQuery();
		    
		    if (rs.next()) pass = createnum();
		    
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
	 * Permet de donner la date d'expiration de 3 ans de la carte
	 * @return date d'expiration en caractères
	 */
	public String dateExpir() {
		DateFormat df = new SimpleDateFormat("MM/yy");
		Calendar date = Calendar.getInstance();		
		date.add(Calendar.YEAR, 3);
		return(df.format(date.getTime()));
		
	}
	
	/**
	 * Permet d'ajouter une carte dans la table
	 * @param c la carte à ajouter
	 * @return retourne le nombre de lignes ajoutees dans la table
	 */
	public int AjoutCarte(Carte c) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			ps = con.prepareStatement("INSERT INTO carte "
					+ "(numCrt, numCpt, dateExp, cvc)"
					+ " VALUES (?,?,?,?)");

			ps.setString(1, c.getnumCrt());
			ps.setString(2, c.getnumCpt());
			ps.setString(3, c.getdate());
			ps.setString(4, c.getcvc());
			

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
		CarteDAO c = new CarteDAO();
		String nc = c.createnum();
		String n = c.dateExpir();
		String t = c.cvc();
		Carte ca = new Carte(nc,"ACDDEA552142",n,t);
		int add = c.AjoutCarte(ca);
		System.out.println(add);

	}

}
