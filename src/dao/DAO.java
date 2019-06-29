package dao;

/**
 * Classe DAO m�re permettant de d�finir le lien vers la BDD et les identifiants d'acc�s et charger le driver JDBC 
 * @author TIC1.3
 *
 */
public class DAO {

	/**
	 * Conctantes de connexion � la BDD
	 */
	protected final static String URL = "jdbc:mysql://localhost/Bank?useTimezone=true&serverTimezone=GMT";
	protected final static String LOGIN = "root";
	protected final static String PASS = "root"; 
	
	public DAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Success loading driver");
		} catch (ClassNotFoundException e) {
			System.err
					.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
		}
	}
}
