package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.lang.Object;


import com.sun.javafx.collections.MappingChange.Map;

import model.Client;

/**
 * Classe liant notre appli à la table client de la BDD
 * @author TIC1.3
 *
 */
public class ClientDAO extends DAO {
	
	/**
	 * Vérifie si l'email et le mot de passe correspondent à une entrée dans la BDD
	 * @param email email à tester
	 * @param pwd mot de passe renseigné
	 * @return true si l'email et le mot de passe correspondent
	 * et false sinon
	 */
	public boolean checkLogin(String email, String pwd){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean retour = false;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT mdp FROM client WHERE email=?");
			ps.setString(1, email);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				if(rs.getString(1).equals(pwd)) { retour = true; }
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
	 * Vérifie si l'inscription du client a été confirmé par mail
	 * @param email email du client à vérifier
	 * @return true si l'inscription du client a été confirmé et false sinon
	 */
	public boolean checkConfirmed(String email){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean retour = false;
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT confirmed FROM client WHERE email=?");
			ps.setString(1, email);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				if (rs.getBoolean(1)) { retour = true; }
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
	 * Permet de récupérer l'id du client depuis son email
	 * @param email email du client dont on souhaite avoir l'id 
	 * @return l'id correspondant ou 0 si aucun id trouvé
	 */
	public int getId(String email){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int retour = 0;
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT idClient FROM client WHERE email=?");
			ps.setString(1, email);
			
			rs = ps.executeQuery();
			
			if (rs.next()) retour = rs.getInt(1);
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du ResultSet, du PreparedStatement et de la Connexion
			try {
				if (rs != null) rs.close();
			} catch (Exception ignore) {}
			try {
				if (ps != null) ps.close();
			} catch (Exception ignore) {}
			try {
				if (con != null)con.close();
			} catch (Exception ignore) {}
		}
		return retour;
	}
	
	/**
	 * Renvoie les infos du client
	 * @param id l'id du client dont on requiert les infos
	 * @return un objet client contenant toutes ses infos
	 */
	public Client getInfos(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Client retour = null;
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM client WHERE idClient=?");
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				retour = new Client(
						rs.getInt("idClient"),
						rs.getString("civ").charAt(0),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("adr"),
						rs.getDate("bday"),
						rs.getString("email"),
						rs.getString("mdp"),
						rs.getString("pays"),
						rs.getString("city"),
						rs.getString("nat"),
						rs.getString("cP"),
						rs.getString("tel")
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
	 * Renvoie les infos du client détenteur du compte 
	 * @param numCpt le numéro du compte dont on requiert le client détenteur
	 * @return un objet client contenant toutes ses infos
	 */
	public Client getInfosClient(String numCpt) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Client retour = null;
		int id = 0;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT ClientID FROM compte WHERE numCompte=?");
			ps.setString(1, numCpt);
			
			rs = ps.executeQuery();
			
			if(rs.next()) id = rs.getInt("ClientID");
			
			retour = this.getInfos(id);
			
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
	 * Permet d'ajouter un client dans la table
	 * @param cl le client  ajouter
	 * @return retourne le nombre de lignes ajoutees dans la table
	 */
	public int add(Client cl) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			ps = con.prepareStatement("INSERT INTO client "
					+ "(civ, nom, prenom, adr, bday, email, mdp, pays, city, nat, cP, tel)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, String.valueOf(cl.getCiv()));
			ps.setString(2, cl.getNom());
			ps.setString(3, cl.getPrenom());
			ps.setString(4, cl.getAdr());
			ps.setDate(5, cl.getBday());
			ps.setString(6, cl.getEmail());
			ps.setString(7, cl.getMdp());
			ps.setString(8, cl.getPays());
			ps.setString(9, cl.getCity());
			ps.setString(10, cl.getNat());
			ps.setString(11, cl.getCP());
			ps.setString(12, cl.getTel());

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
	 * Permet d'ajouter le code de validation client à envoyer dans le lien de confirmation 
	 * @param idClient id du client dont on souhaite ajouter une clé de validation associée
	 * @param keyClient la clé à ajouter
	 * @return retourne le nombre de lignes ajoutees dans la table
	 */
	public int addKeyClient(int idClient, String keyClient) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			ps = con.prepareStatement("INSERT INTO key_client_confirm "
					+ "(idClient,keyClient)"
					+ " VALUES (?,?)");
			ps.setInt(1, idClient);
			ps.setString(2, keyClient);

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
	 * Permet de supprimer une clé de validation client après confirmation de son inscription 
	 * @param idClient id du client dont on souhaite supprimer la clé de validation
	 * @return retourne le nombre de lignes supprimées dans la table
	 */
	public int removeKeyClient(int idClient) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			ps = con.prepareStatement("DELETE FROM key_client_confirm WHERE idClient=?");
			ps.setInt(1, idClient);
			
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
	 * Vérifie si la clé fourni pour la validation du compte client correspond
	 * @param idClient id du client dont on souhaite vérifier la clé de validation 
	 * @param key clé à vérifier
	 * @return true si la clé correspond à l'id et false sinon
	 */
	public boolean checkKeyClient(int idClient, String key) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean retour = false;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT keyClient FROM key_client_confirm WHERE idClient=?");
			ps.setInt(1, idClient);
			
			rs = ps.executeQuery();
			
			//Recupere la valeur trouvée et la compare
			if(rs.next()) {
				if (rs.getString(1).equals(key)) retour = true;//Si la clé correspond => retour true
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
	 * Valide un compte client à partir de l'id fourni dans le lien de confirmation
	 * @param id l'id du client dont on souhaite valider le compte
	 * @return retourne le nombre de lignes modifiees dans la table
	 */
	public int confirmClient(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			ps = con.prepareStatement("UPDATE client SET confirmed=? WHERE idClient=?");
			ps.setBoolean(1, true);
			ps.setInt(2, id);
			
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
	 * Permet de modifier un client dans la table
	 * @param cl l'objet client contenant les nouvelles infos du client
	 * @return retourne le nombre de lignes modifiées dans la table
	 */
	public int modif(Client cl) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			ps = con.prepareStatement("UPDATE client SET "
					+ "civ=?, nom=?, prenom=?, adr=?, bday=?, email=?, mdp=?, pays=?, city=?, nat=?, cP=?, tel=?"
					+  "WHERE idClient=?");
			ps.setString(1, String.valueOf(cl.getCiv()));
			ps.setString(2, cl.getNom());
			ps.setString(3, cl.getPrenom());
			ps.setString(4, cl.getAdr());
			ps.setDate(5, cl.getBday());
			ps.setString(6, cl.getEmail());
			ps.setString(7, cl.getMdp());
			ps.setString(8, cl.getPays());
			ps.setString(9, cl.getCity());
			ps.setString(10, cl.getNat());
			ps.setString(11, cl.getCP());
			ps.setString(12, cl.getTel());
			ps.setInt(13, cl.getIdClient());
			

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
	 * Permet de lister tous les clients présents dans la BDD
	 * @return Dictionnaire associant chaque id à un prenom
	 */
	public Dictionary<Integer, String> getClients() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Dictionary<Integer, String> retour = new Hashtable<Integer,String>();		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT idClient,nom,prenom FROM client");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int t1 = rs.getInt("idClient");
				String t = rs.getString("nom");
				String t2 = rs.getString("prenom");
				String t3 = t +" "+ t2;

				retour.put(t1, t3);

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
	 * Permet d'obtenir la clé d'une valeur
	 * @param l dictionnaire à parcourir
	 * @param val la valeur dont on requiet la clé
	 * @return la clé de l'élément
	 */
	public int getkey(Dictionary<Integer, String> l, String val) {
		Enumeration<String> v = l.elements();
		Enumeration<Integer> k = l.keys();
		int b=0;
		while(k.hasMoreElements()) {
			if (l.get(k.nextElement())==val) {
				 b = (int) k.nextElement();
				 
			}
		}
		return b;
	}
	
	/**
	 * Renvoie la liste de tous les clients
	 * @return une liste de clients
	 */
	public ArrayList<Client> getCl() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Client> retour = new ArrayList<Client>();
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// Recupere les transaction dont les 5 premiers caractères de l'attribut desc
			// sont égal à "Dépot"
			ps = con.prepareStatement("SELECT idClient, nom, prenom, email FROM client");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Client t = new Client(
						rs.getInt("idClient"), 
						'a',  
						rs.getString("nom"),
						rs.getString("prenom"),
						"",
						null,
						rs.getString("email"), 
						"", 
						"",
						"",
						"",
						"",
						""
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
	 * Permet de hacher le mot de passe
	 * @param password le mot de passe non sécurisé à hasher
	 * @return liste des byte qui composent le mot de passe crypté
	 */
	public String hash(String password) {
		MessageDigest md=null;
		
		try { md = MessageDigest.getInstance("SHA-256"); }
		catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
		
		md.update(password.getBytes());
		
		byte byteData[] = md.digest();
		
		//convertir le tableau de bits en une format hexadécimal - méthode 1
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		ClientDAO d = new ClientDAO();
		System.out.println(d.checkLogin("housna.azeddine@gmail.com", d.hash("azeddine")));
		/*ArrayList<Client> l = new ArrayList<Client>();
		l = d.getCl();
		for (Client cl : l) {
			System.out.println(cl.getNom());/*
		}
	/*	Dictionary<Integer, String> retour = new Hashtable<Integer,String>();
		Enumeration v = retour.elements(); 

		retour = d.getClients();
		//int j = d.getkey(retour, "CAMARA Moussa");
		//System.out.println(j);
		d.getkey(retour, "CAMARA Moussa");*/
		/*Client cl = new Client(
					'H', "CAMARA", "Moussa", "Tour Minerve, Rue E. Cotton",
					java.sql.Date.valueOf("1997-01-24"), "moussa1@moussa.mo",
					"mo","France", "Saint-Etienne du Rouvray",
					"Senegalaise","76800", "0613389186"
				);
		int b = d.add(cl);
		System.out.println(b);
		
		int id = d.getId("moussa@moussa.mo");
		Client c = d.getInfos(id);
		System.out.println(
				"**"+c.getIdClient()+"**"
				+c.getCiv()+"**"
				+c.getNom()+"**"
				+c.getPrenom()+"**"
				+c.getAdr()+"**"
				+c.getBday()+"**"
				+c.getEmail()+"**"
				+c.getMdp()+"**"
				+c.getPays()+"**"
				+c.getCity()+"**"
				+c.getNat()+"**"
				);*/
	}
}
