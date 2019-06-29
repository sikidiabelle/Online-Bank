package dao;

import java.sql.Connection;
import java.util.*;

import model.CBancaire;
import model.Compte;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Classe liant notre appli aux tables compte, cbancaire, ctitre de la BDD
 * @author TIC1.3
 *
 */
public class CptDAO extends DAO {
	
	/**
	 * Permet de g�n�rer un rib al�atoirement
	 * @return retourne le rib g�n�r�
	 */
	public String createRib() {
        Random r = new Random();
        int n = 10 + r.nextInt(99-10);
        
        String rib = Integer.toString(n);
        return rib;
	}
	
	/**
	 * Permet de g�n�rer un num�ro de compte al�atoire de 6 chiffres et 6 lettres
	 * @return le num�ro de compte g�n�r�
	 */
	public String createnumcpt() {
	    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; 
	    String pass = "";
	    
	    for(int x=0;x<6;x++)   {
	    	int i = (int)Math.floor(Math.random() * 5); 
	    	pass += chars.charAt(i);
	    }
	    String cha = "1234567890"; 
	    for(int x=0;x<6;x++)   {
	    	int i = (int)Math.floor(Math.random() * 5); 
	    	pass += cha.charAt(i);
		}
	    
		Connection con = null;
		PreparedStatement ps = null;
		
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			ps = con.prepareStatement("SELECT * "
					+ "FROM compte WHERE numCompte=?");
			ps.setString(1, pass);
		    ResultSet rs = ps.executeQuery();
		    
		    if (rs.next()) pass = createnumcpt();
		    
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
	 * Permet d'ajouter un compte dans la BDD
	 * @param c le compte � ajouter
	 * @return retourne le nombre de lignes ajout�es dans la BDD
	 */
	public int ajout(Compte c) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			ps = con.prepareStatement("INSERT INTO compte "
					+ "(numCompte, rib, iban,ClientID)"
					+ " VALUES (?,?,?,?)");

			ps.setString(1, c.getNumCompte());
			ps.setString(2, c.getRib());
			ps.setString(3, c.getIban());
			ps.setInt(4, c.getClientID());
			

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
	 * Permet d'ajouter un compte bancaire dans la BDD
	 * @param cb le compte bancaire � ajouter
	 * @return retourne le nombre de lignes ajout�es dans la BDD
	 */
	public int ajout2(CBancaire cb) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		
		try {
	
			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			ps = con.prepareStatement("INSERT INTO cbancaire "
					+ "(numCpt, solde, plVir, plRetrait, plDecouvert, cartes, chequier)"
					+ " VALUES (?,?,?,?,?,?,?)");
			ps.setString(1, cb.getNumCompte());
			ps.setDouble(2, cb.getsolde());
			ps.setInt(3, cb.getplVir());
			ps.setInt(4, cb.getplRetrait());
			ps.setInt(5, cb.getplDecouvert());
			ps.setBoolean(6, cb.getcarte());
			ps.setBoolean(7, cb.getchequier());
	
			// Excution de la requte
			retour = ps.executeUpdate();
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// fermeture du preparedStatement et de la connexion
			try {
				if (ps != null) ps.close();
			} catch (Exception ignore) {}
			try {
				if (con != null) con.close();
			} catch (Exception ignore) {}
		}
		return retour;
	}
	
	/**
	 * Renvoie les infos du compte � partir de son num�ro
	 * @param numCpt le num�ro du compte dont on requiert les infos
	 * @return un objet compte contenant toutes ses infos
	 */
	public Compte getInfosCompte(String numCpt) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Compte retour = null;
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM compte WHERE numCompte=?");
			ps.setString(1, numCpt);
			
			rs = ps.executeQuery();
			
			
			if(rs.next()) {
				retour = new Compte(
						rs.getString("numCompte"),
						rs.getString("rib"),
						rs.getString("iban"),
						rs.getInt("ClientID")
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
	 * Permet d'avoir le solde du compte (bancaire, titre ou epargne)
	 * @param numC le numero du compte dont on requiert le solde 
	 * @return le solde du compte
	 */
	public double getSolde (String numC) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		double retour = -5;
		CptDAO c = new CptDAO();
        int type = c.getTypeCompte(numC);
		
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			if(type ==1) 
				{ps = con.prepareStatement("SELECT solde FROM cbancaire WHERE numCpt=?");}
			if(type ==0) 
				{ps = con.prepareStatement("SELECT solde FROM cepargne WHERE numCpt=?");}
			if(type ==2) 
				{ps = con.prepareStatement("SELECT capital FROM ctitre WHERE numCpt=?");}
			
			ps.setString(1, numC);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				retour = rs.getDouble(1);
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
	 * Permet de r�cup�rer les num�ros de tous les comptes d'un Client
	 * @param id id du client dont on requiert les compte
	 * @return liste des num�ros de ses comptes
	 */
	public ArrayList<String> getCpts(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> retour = new ArrayList<String>();
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT numCompte FROM compte WHERE ClientID=?");
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String t = rs.getString("numCompte");
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
	 * Permet de cr�diter ou d�biter un compte (faire varier le solde)
	 * @param montant montant de la vairation du solde
	 * si elle est positive, le solde augmente donc le compte est cr�dit�
	 * si elle est n�gative, le solde augmente donc le compte est d�dit� 
	 * @param numCpt le numero du compte � alimenter
	 * @return le nombre de lignes modifi�es
	 */
	public int alimenter(double montant, String numCpt) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
        int type = getTypeCompte(numCpt);
		try {
			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			if(type ==1)
				ps = con.prepareStatement("UPDATE cbancaire SET solde = (solde + ?) WHERE numCpt = ?");
			if(type ==0)
				ps = con.prepareStatement("UPDATE cepargne SET solde = (solde + ?) WHERE numCpt = ?");
			if(type ==2)
				ps = con.prepareStatement("UPDATE ctitre SET capital = (capital + ?) WHERE numCpt = ?");
			ps.setDouble(1, montant);
			ps.setString(2, numCpt);
			
			// Excution de la requte
			retour = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// fermeture du preparedStatement et de la connexion
			try {
				if (ps != null) ps.close();
			} catch (Exception ignore) {}
			try {
				if (con != null) con.close();
			} catch (Exception ignore) {}
		}
		return retour;
	}
	
	/**
	 * Permet de r�cup�rer le type d'un compte depuis son iban	
	 * @param num le num�ro du compte dont on requiert le type dans la BDD
	 * @return 0 pour un compte epargne, 1 pour un compte bancaire, 2 pour un compte titre
	 * ou -1 si aucun compte trouv� pour ce num�ro
	 */
	public int getTypeCompte(String num)
	{
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs;
			String v= "0";
			int retour = -1;
			
			try {
	
				// tentative de connexion
				con = DriverManager.getConnection(URL, LOGIN, PASS);
				
				ps = con.prepareStatement("SELECT iban FROM compte WHERE numCompte=?");
				ps.setString(1,num);
				// Excution de la requte
				rs = ps.executeQuery();
				if(rs.next())
				{
					v = rs.getString(1); 
				}
				if(v.charAt(13) == '0')
				{
					retour = 0;
				}
				if(v.charAt(13) == '1')
				{
					retour = 1;
				}
				if(v.charAt(13) == '2')
				{
					retour = 2;
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
	 * Permet de v�rifier si un compte est enregistr� dans la banque
	 * @param iban iban du compte � v�rifier
	 * @return 1 si le compte est affili� � la banque et 0 sinon
	 */
	public int checkcpt(String iban) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int retour = 0;
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM compte WHERE iban=?");
			ps.setString(1, iban);
			
			rs = ps.executeQuery();
			
			
			if(rs.next()) {
				retour = 1;
			}
			else 
				retour = 0;
			
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
	 * R�cup�re le numero de compte titre pour l'id du client donn�
	 * @param id l'id du client dont on requiert le num de compte titre
	 * @return Le numero de compte titre correspondant � l'id
	 * ou une chaine vide si aucun compte titre trouv�
	 */
	public String getNumCptTitre(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String retour = "";

		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT numCompte, iban FROM compte WHERE ClientID=?");
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			//On r�cup�re les iban de tous les comptes du client
			while(rs.next()) {
				String t = rs.getString("iban");
				if(t.charAt(13) == '2') retour = rs.getString("numCompte");
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
	 * Permet de v�rifier si le client a d�j� un compte titre dans la banque
	 * @param id identifiant du client
	 * @return true si il poss�de un compte titre et false sinon
	 */
	public boolean UniTitre(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean re = false;
		ArrayList<String> l = new ArrayList<String>();

		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT iban FROM compte WHERE ClientID=?");
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			//On r�cup�re les iban de tous les comptes du client
			while(rs.next()) {
				String t = rs.getString("iban");
				l.add(t);
			}
			
			if (l.size()!=0) { //Si le client poss�de des compte
				for (String i : l ) { //Parcours de la liste des ibans du client
					if(i.charAt(13) == '2') re = true; //Titre => charAt_13 = '2'
				}
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
		return re;
	}
	
	/**
	 * Permet de v�rifier si le client a d�j� un compte epargne dans la banque
	 * @param id identifiant du client
	 * @return true si il poss�de un compte �pargne et false sinon
	 */
	public boolean UniEpargne(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean re = false;
		ArrayList<String> l = new ArrayList<String>();

		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT iban FROM compte WHERE ClientID=?");
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			//On r�cup�re les iban de tous les comptes du client
			while(rs.next()) {
				String t = rs.getString("iban");
				l.add(t);
			}
			
			if (l.size()!=0) { //Si le client poss�de des compte
				for (String i : l ) { //Parcours de la liste des ibans du client
					if(i.charAt(13) == '0') re = true; //Epargne => charAt_13 = '0'
				}
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
		return re;
	}
	
	/**
	 * Permet de v�rifier si le client a d�j� un compte bancaire dans la banque
	 * @param id identifiant du client
	 * @return true si il poss�de un compte bancaire et false sinon
	 */
	public boolean UniBancaire(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean re = false;
		ArrayList<String> l = new ArrayList<String>();

		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT iban FROM compte WHERE ClientID=?");
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			//On r�cup�re les iban de tous les comptes du client
			while(rs.next()) {
				String t = rs.getString("iban");
				l.add(t);
			}
			
			if (l.size()!=0) { //Si le client poss�de des compte
				for (String i : l ) { //Parcours de la liste des ibans du client
					if(i.charAt(13) == '1') re = true; //Bancaire => charAt_13 = '1'
				}
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
		return re;
	}
	
	public static void main(String[] args) {
		
		CptDAO c = new CptDAO();
		
		String a = c.getNumCptTitre(2);
		System.out.println(a);
		
		/*String nc = c.createnumcpt();
		String rib = c.createRib();
		String iban = "FR003003025891"+nc+rib;
		CBancaire cb = new CBancaire(nc, rib, iban, 2, 100, 100, 100, 100, false, false);
		int add1 = c.ajout(cb);
		System.out.println(add1);
		int add2 = c.ajout2(cb);
		System.out.println(add2);
		
		/*int r = c.checkcpt("ADCBADppp343");
		System.out.println(r);
		String d="FR003003025890BEAAAE54243552";
		d=d.substring(14);
		d=d.substring(0,12);
		System.out.println(d);*/
	}

}