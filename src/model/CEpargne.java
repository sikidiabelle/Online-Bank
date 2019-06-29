package model;

/**
 * Classe représentant un compte epargne
 * @author TIC1.3
 *
 */
public class CEpargne extends Compte {
	/**
	 * Solde dans le compte
	 */
	private double solde;
	/**
	 * Taux d'intérêt annuel
	 */
	private double taux;
	
	/**
	 * Constructeur compte épargne nouveau 
	 * @param numCompte numéro du compte
	 * @param rib rib du compte epargne
	 * @param iban iban du compte epargne
	 * @param ClientID identifiant du client
	 * @param solde solde du compte
	 * @param taux Taux d'intérêts par année
	 */
	public CEpargne(String numCompte, String rib, String iban,int ClientID, double solde, double taux) {
		super(numCompte, rib, iban, ClientID);
		this.solde = solde;
		this.taux = taux;
	}
	
	/**
	 * Constructeur compte épargne
	 * @param numCompte numéro du compte
	 * @param rib rib du compte epargne
	 * @param iban iban du compte epargne
	 * @param ClientID identifiant du client
	 * @param solde solde du compte
	 */
	public CEpargne(String numCompte, String rib, String iban,int ClientID, double solde) {
		super(numCompte, rib, iban, ClientID);
		this.solde = solde;
	}
	
	/**
	 * Getter solde
	 * @return le solde du compte
	 */
	public double getsolde() {return solde;}
	/**
	 * Getter du taux
	 * @return le taux d'intérêt du compte
	 */
	public double gettaux() {return taux;}
	
	/**
	 * Setter du solde  
	 * @param s le nouveau solde du compte
	 */
	public void setsolde(int s) {this.solde=s;}
	/**
	 * Setter du taux
	 * @param t le nouveau taux d'intérêt
	 */
	public void settaux(int t) {this.taux=t;}
	
}
