/**
 /**
 * Classe liant notre appli  la BDD
 * @author Moussa C.
 * @version 1.0
 *
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Message;

/**
 * Classe liant notre appli à la table message de la BDD
 * @author TIC1.3
 *
 */
public class MessageDAO extends DAO {
	
	/**
	 * Permet d'ajouter un nouveau message dans la table
	 * @param ms le message à ajouter
	 * @return retourne le nombre de lignes ajoutéees dans la table
	 */
	public int add(Message ms) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			ps = con.prepareStatement("INSERT INTO message (email, objet, text_message) VALUES (?,?,?)");
			ps.setString(1, ms.getEmail());
			ps.setString(2, ms.getObjet());
			ps.setString(3, ms.getTextMessage());

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
	
}
