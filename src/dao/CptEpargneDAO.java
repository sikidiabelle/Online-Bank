package dao;

import java.sql.Connection;
import java.util.*;

import model.CEpargne;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Classe liant notre appli à la table cepargne de la BDD
 * @author TIC1.3
 *
 */
public class CptEpargneDAO extends DAO {
	
	/**
	 * Permet de vérifier si le numéro de compte courant (bancaire) associé au compte epargne est bien valide
	 * @param numcpt numéro du compte
	 * @return 1 si le numero de compte courant existe dans la BDD et false sinon
	 */
	public int checkCpt(String numcpt) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			ps = con.prepareStatement("SELECT * "
					+ "FROM cbancaire WHERE numCpt=?");
			ps.setString(1, numcpt);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) retour = 1;

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
	 * Permet de trouver l'identifiant du client associé au compte bancaire
	 * @param num numéro du compte bancaire du client dont on requiert l'id
	 * @return id identifiant du client correspondant ou 0 si aucun compte trouvé
	 */
	public int idClient(String num) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			ps = con.prepareStatement("SELECT ClientID "
					+ "FROM compte WHERE numCompte=?");
			ps.setString(1, num);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		    	retour = rs.getInt("ClientID");
		    }

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
	 * Permet d'ajouter un compte epargne dans la BDD
	 * @param ce le compte épargne ajouté
	 * @return retourne le nombre de lignes ajoutées dans la BDD
	 */
	public int ajoutEp(CEpargne ce) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			ps = con.prepareStatement("INSERT INTO cepargne "
					+ "(numCpt, solde, TauxInteret)"
					+ " VALUES (?,?,?)");

			ps.setString(1, ce.getNumCompte());
			ps.setDouble(2, ce.getsolde());
			ps.setDouble(3, ce.gettaux());
			
			
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
		
		CptDAO c = new CptDAO();
		CptEpargneDAO ce = new CptEpargneDAO();
		CEpargne cep = new CEpargne(c.createnumcpt(), c.createRib(), "aa", 1, 100, 0.5);
		/*int add1 = c.ajout(cep);
		System.out.println(add1);
		int add2 = ce.ajoutEp(cep);
		System.out.println(add2);
		*/
		int a = ce.checkCpt("CCEC00452232"); 
		System.out.println(a);

	}

}
