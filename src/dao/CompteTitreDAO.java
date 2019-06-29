package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import model.Ctitre;

/**
 * Classe liant notre appli à la table ctitre de la BDD
 * @author TIC1.3
 *
 */
public class CompteTitreDAO extends DAO {
	
	/**
	 * Permet d'ajouter un compte titre à la BDD
	 * @param c le compte titre à ajouter à la BDD
	 * @return le nombre de lignes ajoutées à la BDD
	 */
	public int ajouter(Ctitre c) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		System.out.println("erreur 1");
		// connexion à la base de données
		try {
			System.out.println("erreur 2");
			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// préparation de l'instruction SQL, chaque ? représente une valeur
			// à communiquer dans l'insertion
			// les getters permettent de récupérer les valeurs des attributs
			// souhaités
			ps = con.prepareStatement("INSERT INTO ctitre (numCpt, capital, NIF, residence) VALUES (?, ?, ?, ?)");
			ps.setString(1, c.getNumCompte());
			ps.setDouble(2, c.getCapital());
			ps.setString(3, c.getNif());
			ps.setString(4,c.getResidence());
			
			// Exécution de la requête
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
		System.out.println("erreur6");
		return retour;		
	}
	
	public static void main(String[] args) {
		CompteTitreDAO c = new CompteTitreDAO();
		
		c.ajouter(new Ctitre("b","b","b",1,"b",0,"b"));
	}
}
