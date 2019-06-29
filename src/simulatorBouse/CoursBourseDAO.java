package simulatorBouse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dao.DAO;

public class CoursBourseDAO extends DAO {
	
	public CoursBourseDAO() { super(); }
	
	/**
	 * Récupère la dernière date entrée dans la table
	 * @return la dernière date rentrée dans la table
	 */
	public Timestamp getLastDateCours() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Timestamp retour = null;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT MAX(dateCours) FROM cours_bourse");
			
			rs = ps.executeQuery();
			
			if(rs.next())
				retour = rs.getTimestamp(1);
			
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du ResultSet, du PreparedStatement et de la Connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
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
	
	/**
	 * Recupère le dernier cours de la bourse saisi
	 * @return un objet CoursBourse contenant les infos relative au dernier cours de la bourse
	 */
	public CoursBourse getLastCours() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CoursBourse retour = null;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			//Récupèration de la date de la dernière entrée
			Timestamp lastTS = getLastDateCours();
			
			if (lastTS!=null) { //Si une entrée existe dans la table
				
				ps = con.prepareStatement("SELECT * FROM cours_bourse WHERE dateCours=?");
				ps.setTimestamp(1, lastTS);
				
				rs = ps.executeQuery();
				
				if(rs.next())
					retour = new CoursBourse(
							rs.getInt("idCours"),
							rs.getTimestamp("dateCours"),
							rs.getDouble("indice")
							);
			}
			
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du ResultSet, du PreparedStatement et de la Connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
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
	
	/**
	 * Permet d'ajouter un cours de la bourse dans la table
	 * @param cb le cours de la bourse à ajouter
	 * @return retourne le nombre de lignes ajoutees dans la table
	 */
	public int add(CoursBourse cb) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			ps = con.prepareStatement("INSERT INTO cours_bourse "
					+ "(dateCours,indice)"
					+ " VALUES (CURRENT_TIMESTAMP,?)");
			ps.setDouble(1, cb.getIndice());

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
	
	/**
	 * Renvoie la liste de tous les cours de la bourse
	 * @return une liste de cours de la bourse
	 */
	public ArrayList<CoursBourse> getCoursBourse() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<CoursBourse> retour = new ArrayList<CoursBourse>();
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM cours_bourse");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CoursBourse cb = new CoursBourse(
						rs.getInt("idCours"),
						rs.getTimestamp("dateCours"),
						rs.getDouble("indice")
						);
				retour.add(cb);
			}
			
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du ResultSet, du PreparedStatement et de la Connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
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
	public ArrayList<ArrayList<String>> getCours() {
		CoursBourseDAO cr = new CoursBourseDAO();
		ArrayList<ArrayList<String>> retour = new ArrayList<ArrayList<String>>();
		//ArrayList<String> b = new ArrayList<String>();
	//	b.add("date");
		//b.add("cours");
		//retour.add(b);

		ArrayList<CoursBourse> l = cr.getCoursBourse();
		for (CoursBourse c : l) {
			ArrayList<String> li = new ArrayList<String>();
			Timestamp d = c.getDateCours();
			SimpleDateFormat formater = new SimpleDateFormat("yyyy,MM,dd,HH,mm,ss");
		    String date =formater.format(d);
			Double j = c.getIndice();
			String indice = j.toString();
			String date1 = d.toString();
			li.add(date);
			li.add(indice);
			retour.add(li);

		}
		return retour;
		
	}
	public static void main(String[] args) {
		CoursBourseDAO dao = new CoursBourseDAO();
		ArrayList<ArrayList<String>> l = dao.getCours();
		
	/*	CoursBourse cb = new CoursBourse(5558.96);
		dao.add(cb);
		
		ArrayList<CoursBourse> l = dao.getCoursBourse();
		
		System.out.println("***** Liste des cours de la bourse");
		for (CoursBourse c : l)*/
			System.out.println(l);
	}
}
