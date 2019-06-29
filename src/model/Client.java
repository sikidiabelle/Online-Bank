package model;

/**
 * Classe représentant un client
 * @author TIC1.3
 *
 */
public class Client {
	/**
	 * Id du client
	 */
	private int idClient = 0;
	/**
	 * Civilite du client
	 */
	private char civ = Character.MIN_VALUE;
	/**
	 * Nom du client
	 */
	private String nom = null;
	/**
	 * Prenom du client
	 */
	private String prenom = null;
	/**
	 * Adresse domicile du client
	 */
	private String adr = null;
	/**
	 * Date de naissance du client
	 */
	private java.sql.Date bday = null;
	/**
	 * Email du client
	 */
	private String email = null;
	/**
	 * Mot de passe du client
	 */
	private String mdp;
	/**
	 * Pays de résidence client
	 */
	private String pays = null;
	/**
	 * Ville de résidence du client
	 */
	private String city = null;
	/**
	 * Nationalité du client
	 */
	private String nat = null;
	/**
	 * Code postal du client client
	 */
	private String cP;
	/**
	 * Numéro de téléphone mobile du client
	 */
	private String tel;
	
	/**
	 * Constructeur pour un client déjà inscrit
	 * @param idClient id du client
	 * @param civ civilité du client
	 * @param nom nom du client
	 * @param prenom prenom du client
	 * @param adr adresse du client
	 * @param bday date de naissance du client
	 * @param email email du client
	 * @param mdp mot de passe du client
	 * @param pays pays de résidence
	 * @param city ville de résidence
	 * @param nat nationalité du client
	 * @param cP code postal du client
	 * @param tel numéro de téléphone mobile
	 */
	public Client(int idClient, char civ, String nom, String prenom, String adr, java.sql.Date bday,
			String email, String mdp, String pays, String city, String nat, String cP, String tel) {
		
		this.idClient=idClient; this.civ=civ; this.nom=nom; this.prenom=prenom;
		this.adr=adr; this.bday=bday; this.email=email; this.pays=pays;
		this.city=city; this.nat=nat; this.cP = cP; this.tel = tel; this.mdp=mdp;
		
	}
	
	/**
	 * Constructeur pour un client pas encore inscrit (pas d'id affecté)
	 * @param civ civilité du client
	 * @param nom nom du client
	 * @param prenom prenom du client
	 * @param adr adresse du client
	 * @param bday date de naissance du client
	 * @param email email du client
	 * @param mdp mot de passe du client
	 * @param pays pays de résidence
	 * @param city ville de résidence
	 * @param nat nationalité du client
	 * @param cP code postal du client
	 * @param tel numéro de téléphone mobile
	 */
	public Client(char civ, String nom, String prenom, String adr, java.sql.Date bday,
			String email, String mdp, String pays, String city, String nat, String cP, String tel) {
		
		this.civ=civ; this.nom=nom; this.prenom=prenom; this.mdp = mdp;
		this.adr=adr; this.bday=bday; this.email=email; this.pays=pays;
		this.city=city; this.nat=nat; this.cP = cP; this.tel = tel;
		
	}
	
	
	/**
	 * Constructeur pour un client 
	 * @param idClient id du client
	 * @param nom nom du client
	 * @param prenom prenom du client
	 */
	public Client(int idClient, String nom, String prenom) {
		
		this.idClient=idClient; this.nom=nom; this.prenom=prenom;

		
	}
	
	/**
	 * Getter de l'id du client
	 * @return l'id du client
	 */
	public int getIdClient() {return idClient; }
	/**
	 * Getter de la civilité
	 * @return la civilité du client
	 */
	public char getCiv() {return civ; }
	/**
	 * Getter du nom 
	 * @return le nom du Client
	 */
	public String getNom() {return nom; }
	/**
	 * Getter du prenom
	 * @return le prenom du Client
	 */
	public String getPrenom() {return prenom; }
	/**
	 * Getter de l'adresse
	 * @return l'adresse du client
	 */
	public String getAdr() {return adr; }
	/**
	 * Getter de la date de naissance 
	 * @return la date de naissance du client
	 */
	public java.sql.Date getBday() {return bday; }
	/**
	 * Getter de l'email
	 * @return l'email du client
	 */
	public String getEmail() {return email; }
	/**
	 * Getter du mot de passe 
	 * @return le mot de passe du client
	 */
	public String getMdp() { return mdp; }
	/**
	 * Getter du pays
	 * @return le pays du client
	 */
	public String getPays() {return pays; }
	/**
	 * Getter de la ville
	 * @return la ville du client
	 */
	public String getCity() {return city; }
	/**
	 * Getter de la nationalité
	 * @return la nationalité du client
	 */
	public String getNat() {return nat; }
	/**
	 * Getter du code postal
	 * @return le code postal du client
	 */
	public String getCP() {return cP; }
	/**
	 * Getter du numéro de téléphone du client
	 * @return le numéro de téléphone du client
	 */
	public String getTel() {return tel; }
	
	/**
	 * Setter du mot de passe
	 * @param mdp le nouveau mot de passe
	 */
	public void setMdp(String mdp) { this.mdp = mdp; }
	
}
