package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Action;
import model.Actionnaire;
import model.Transaction;

/**
 * Classe liant notre appli aux tables action et actionnaire de la BDD
 * @author TIC1.3
 *
 */
public class ActionDAO extends DAO {
	
	/**
	 * Permet de rajouter une nouvelle acquisition d'action dans la BDD
	 * @param ac acquisistion à ajouter à la BDD
	 * @return le nombre de lignes ajoutées
	 */
	public int acheter(Actionnaire ac) {	
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		
		CptDAO cd = new CptDAO();
		TransactionDAO td = new TransactionDAO();
		
		//Récupère l'action à acheter et son prix
		Action actionBuy = getAction(ac.getIdAction());
		double prix = actionBuy.getPrix();
		prix = prix * ((double) ac.getQuantite());
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("INSERT INTO actionnaire "
					+ "(idAction, numCpt, quantite)"
					+ " VALUES (?,?,?)");

			ps.setInt(1, ac.getIdAction());
			ps.setString(2, ac.getNumCpt());
			ps.setInt(3, ac.getQuantite());
			
			// Excution de la requte
			retour = ps.executeUpdate();
			
			//Si acquisition ajoutée à la BDD
			if (retour!=0) {
				//On débite le capital du compte titre
				int rt = cd.alimenter(-prix, ac.getNumCpt());
				
				//Si compte débité
				if (rt!=0)
					td.add(new Transaction(
							"Achat d'actions - Entreprise : "+actionBuy.getNom()
							+" - Quantité : "+ac.getQuantite()
							, ac.getNumCpt(), "",
							prix, false
							)); //Ajout de la transaction dans la BDD
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
	 * Permet de supprimer une nouvelle acquisition d'action dans la BDD
	 * @param id id l'id de l'acquisition à vendre
	 * @return le nombre de lignes supprimées
	 */
	public int vendre(int id) {	
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;

		CptDAO cd = new CptDAO();
		TransactionDAO td = new TransactionDAO();
		
		//Récupération de l'acquisition à supprimer
		Actionnaire ac = getActionnaire(id);
		
		//Récupère l'action à vendre et son prix
		Action actionBuy = getAction(ac.getIdAction());
		double prix = actionBuy.getPrix();
		prix = prix * ((double) ac.getQuantite());
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("DELETE FROM actionnaire WHERE idActionnaire=?");
			ps.setInt(1, id);
			
			// Excution de la requte
			retour = ps.executeUpdate();
			
			//Si acquisition ajoutée à la BDD
			if (retour!=0) {
				//On débite le capital du compte titre
				int rt = cd.alimenter(prix, ac.getNumCpt());
				
				//Si compte débité
				if (rt!=0)
					td.add(new Transaction(
							"Vente d'actions - Entreprise : "+actionBuy.getNom()
							+" - Quantité : "+ac.getQuantite()
							, ac.getNumCpt(), "",
							prix, true
							)); //Ajout de la transaction dans la BDD
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
	 * Récupère la liste des actions détenues par un compte titre donné
	 * @param numCpt numero du compte titre 
	 * @return une liste des acquisition d'actions détenues pour ce compte
	 */
	public ArrayList<Actionnaire> getListActionnaire(String numCpt) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<Actionnaire> retour = new ArrayList<Actionnaire>();

		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM actionnaire WHERE numCpt = ?");
			ps.setString(1, numCpt);

			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("idActionnaire");
				int idAction = rs.getInt("idAction");
				int quantite = rs.getInt("quantite");

				Actionnaire act = new Actionnaire(id,idAction,numCpt,quantite);
				retour.add(act);
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
	 * Récupère l'action dont l'id est donné
	 * @param id l'id de l'action que l'on requiert
	 * @return un objet contenant les infos de l'Action
	 */
	public Action getAction(int id) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Action retour = null;

		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM action WHERE idAction = ?");
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if (rs.next())
				retour = new Action(
						id,
						rs.getString("nom"),
						rs.getDouble("prix")
						);
			
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
	 * Récupère l'acquisistion d'action dont l'id est donné
	 * @param id l'id de l'acquisition que l'on requiert
	 * @return un objet contenant les infos de l'acquisition
	 */
	public Actionnaire getActionnaire(int id) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Actionnaire retour = null;
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM actionnaire WHERE idActionnaire = ?");
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if (rs.next())
				retour = new Actionnaire(
						id,
						rs.getInt("idAction"),
						rs.getString("numCpt"),
						rs.getInt("quantite")
						);
			
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
	 * Récupère la liste des actions du cac40 présentes dans la BDD
	 * @return la liste des actions du cac40
	 */
	public ArrayList<Action> getListCAC40() {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<Action> retour = new ArrayList<Action>();

		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM action");

			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("idAction");
				String nom = rs.getString("nom");
				double prix = rs.getDouble("prix");

				Action act = new Action(id,nom,prix);
				retour.add(act);
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
}