package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Transaction;

/**
 * Classe liant notre appli à la table transaction de la BDD
 * @author TIC1.3
 *
 */
public class TransactionDAO extends DAO {
	
	/**
	 * Renvoie tout l'historique des transactions de tous les comptes
	 * @return une liste d'objets transaction contenant toutes les infos des transactions 
	 */
	public ArrayList<Transaction> getHisto() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Transaction> retour = new ArrayList<Transaction>();
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM transaction");
			
			rs = ps.executeQuery();
			
			String iban_cible;
			
			while(rs.next()) {
				//Vérifie ci le champ destinataire est vide (cas d'un depot)
				if (rs.getString("iban_cible").isEmpty()) iban_cible = "Non défini";
				else iban_cible = rs.getString("iban_cible");
				
				Transaction t = new Transaction(
						rs.getInt("idTrans"),
						rs.getString("descp"),
						rs.getString("numCpt_src"),
						iban_cible,
						rs.getDouble("montant"),
						rs.getBoolean("type"),
						rs.getTimestamp("date")
						);
				retour.add(t);
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
	 * Renvoie la liste de tous les depots des dépots de tous les comptes
	 * @return une liste d'objets transaction contenant toutes les infos des dépots 
	 */
	public ArrayList<Transaction> getDepots() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Transaction> retour = new ArrayList<Transaction>();
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// Recupere les transaction dont les 5 premiers caractères de l'attribut desc
			// sont égal à "Dépot"
			ps = con.prepareStatement("SELECT * FROM transaction WHERE (SUBSTRING(descp, 1, 5)=?)");
			ps.setString(1, "Dépôt");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Transaction t = new Transaction(
						rs.getInt("idTrans"),
						rs.getString("descp"),
						rs.getString("numCpt_src"),
						"Non défini",
						rs.getDouble("montant"),
						rs.getBoolean("type"),
						rs.getTimestamp("date")
						);
				retour.add(t);
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
	 * Renvoie toutes les transactions pour un compte donnée
	 * @param numCpt numero du compte source dont on requiert les transactions
	 * @return une liste d'objets transaction contenant toutes les infos des transaction du compte 
	 */
	public ArrayList<Transaction> getTransactions(String numCpt) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Transaction> retour = new ArrayList<Transaction>();
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM transaction WHERE numCpt_src=?");
			ps.setString(1, numCpt);
			
			rs = ps.executeQuery();
			
			String iban_cible;
			
			while(rs.next()) {
				//Vérifie ci le champ destinataire est vide (cas d'un depot)
				if (rs.getString("iban_cible").isEmpty()) iban_cible = "Non défini";
				else iban_cible = rs.getString("iban_cible");
				
				Transaction t = new Transaction(
						rs.getInt("idTrans"),
						rs.getString("descp"),
						rs.getString("numCpt_src"),
						iban_cible,
						rs.getDouble("montant"),
						rs.getBoolean("type"),
						rs.getTimestamp("date")
						);
				retour.add(t);
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
	 * Renvoie 3 dernières transactions pour un compte donnée
	 * @param numCpt numero du compte source dont on requiert les 3 dernières transactions
	 * @return une liste d'objets transaction contenant toutes les infos des 3 dernières transaction du compte 
	 */
	public ArrayList<Transaction> get3LastTrans(String numCpt) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Transaction> retour = new ArrayList<Transaction>();
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM transaction WHERE numCpt_src=? ORDER BY date DESC");
			ps.setString(1, numCpt);
			
			rs = ps.executeQuery();
			
			String iban_cible;
			
			int c=1;
			while(rs.next() && c<=3) {
				//Vérifie ci le champ destinataire est vide (cas d'un depot)
				if (rs.getString("iban_cible").isEmpty()) iban_cible = "Non défini";
				else iban_cible = rs.getString("iban_cible");
				
				Transaction t = new Transaction(
						rs.getInt("idTrans"),
						rs.getString("descp"),
						rs.getString("numCpt_src"),
						iban_cible,
						rs.getDouble("montant"),
						rs.getBoolean("type"),
						rs.getTimestamp("date")
						);
				retour.add(t); c++;
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
	 * Permet d'ajouter une nouvelle transaction dans la table
	 * @param t la transaction à ajouter
	 * @return le nombre de ligne ajoutées dans la table
	 */
	public int add(Transaction t) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("INSERT INTO transaction (descp, numCpt_src, iban_cible, montant, type, date)"
					+ " VALUES (?,?,?,?,?,CURRENT_TIME())");
			//CURRENT_TIME permet de récupérer le temps courent au moment de l'écution de la requête SQL
			ps.setString(1, t.getDescp());
			ps.setString(2, t.getNumCpt_src());
			ps.setString(3, t.getIban_cible());
			ps.setDouble(4, t.getMontant());
			ps.setBoolean(5, t.getType());
			
			retour = ps.executeUpdate();
			
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du PreparedStatement et de la Connexion
			try {
				if (ps != null) ps.close();
			} catch (Exception ignore) {}
			try {
				if (con != null) con.close();
			} catch (Exception ignore) {}
		}
		return retour;
	}
	
	public static void main(String[] args) {
		TransactionDAO td = new TransactionDAO();
		
		Transaction t = new Transaction(
				"Dépot en agence en espèce",
				"ADDAAE113551",
				"",
				100,
				true
				);
		
		System.out.println(td.add(t));
		
		/*ArrayList<Transaction> a = td.getDepots();
		
		for (Transaction t0 : a) {
			System.out.println("****************");
			System.out.println(t0.getDescp());
			System.out.println(t0.getNumCpt_src());
			System.out.println(t0.getNumCpt_dest());
			System.out.println(t0.getMontant());
			System.out.println(t0.getType());
			System.out.println(t0.getDate());
		}*/
	}
}
