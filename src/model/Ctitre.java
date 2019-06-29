package model;

/**
 * Classe repr�sentant un compte titre
 * @author TIC1.3
 *
 */
public class Ctitre extends Compte {
	
	/**
	 * Capital du compte
	 */
	private float capital;
	/**
	 * Num�ro d'identification fiscale
	 */
	private String nif;
	/**
	 * R�sidence fiscale
	 */
	private String residence;
	
	/**
	 * Constructeur pour un client d�j� inscrit
	 * @param numCompte numero du compte
	 * @param ClientID id du client
	 * @param capital capital du client
	 * @param nif nif du client
	 * @param rib rib du client
	 * @param residence residence du client
	 * @param iban iban du compte
	 */
	public Ctitre(String numCompte, String rib, String iban,
			int ClientID, String nif, float capital, String residence) {
		
		super(numCompte, rib, iban, ClientID);
		this.capital=capital; this.nif=nif;	
		this.residence=residence;
	}
	
	/**
	 * Constructeur pour un client pas inscrit
	 * @param ClientID id du client
	 * @param capital capital du client
	 * @param nif nif du client
	 * @param rib rib du client
	 * @param iban iban du compte
	 * @param residence residence du client
	 */
	public Ctitre(String rib, String iban,
			int ClientID, String nif, float capital, String residence) {
		
		super(rib, iban, ClientID);
	 this.capital=capital; this.nif=nif; this.residence=residence;
	}
	
	/**
	 * Getter du capital
	 * @return le capital du compte
	 */
	public float getCapital() {return capital; }
	/**
	 * Getter du NIF
	 * @return le num�ro d'identification fiscale
	 */
	public String getNif() {return nif; }
	/**
	 * Getter du pays de r�sidence
	 * @return le pays de r�sidence du client
	 */
	public String getResidence() {return residence;}
}
