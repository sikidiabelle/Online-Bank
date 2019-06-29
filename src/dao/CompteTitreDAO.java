package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import model.Ctitre;

/**
 * Classe liant notre appli � la table ctitre de la BDD
 * @author TIC1.3
 *
 */
public class CompteTitreDAO extends DAO {
	
	/**
	 * Permet d'ajouter un compte titre � la BDD
	 * @param c le compte titre � ajouter � la BDD
	 * @return le nombre de lignes ajout�es � la BDD
	 */
	public int ajouter(Ctitre c) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		System.out.println("erreur 1");
		// connexion � la base de donn�es
		try {
			System.out.println("erreur 2");
			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// pr�paration de l'instruction SQL, chaque ? repr�sente une valeur
			// � communiquer dans l'insertion
			// les getters permettent de r�cup�rer les valeurs des attributs
			// souhait�s
			ps = con.prepareStatement("INSERT INTO ctitre (numCpt, capital, NIF, residence) VALUES (?, ?, ?, ?)");
			ps.setString(1, c.getNumCompte());
			ps.setDouble(2, c.getCapital());
			ps.setString(3, c.getNif());
			ps.setString(4,c.getResidence());
			
			// Ex�cution de la requ�te
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
