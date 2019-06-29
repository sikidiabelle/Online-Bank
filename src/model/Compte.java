package model;

/**
 * Classe repr�sentant un compte
 * @author TIC1.3
 *
 */
public class Compte {
	/**
	 * Num�ro du compte
	 */
	private String numCompte;
	/**
	 * Rib du Compte
	 */
	private String rib;
	/**
	 * Code Guichet
	 */
	private String cGuichet;
	/**
	 * code Banque
	 */
	private String cBanque;
	/**
	 * iban du compte
	 */
	private String iban;
	/**
	 * bic
	 */
	private String bic;
	/**
	 * Type du compte soit �pargne ou courant ou titre
	 */
	private int ClientID = 0;
	
	/**
	 * Constructeur d'un compte pour un client pr�cis 
	 * @param numCompte num�ro du compte
	 * @param rib rib du compte
	 * @param iban iban du compte
	 * @param ClientID identifiant du client
	 */
	public Compte(String numCompte, String rib, String iban,
			 int ClientID) {
		
		this.numCompte=numCompte; this.rib=rib;
		this.iban=iban; this.ClientID=ClientID;
	}
	
	/**
	 * Constructeur nouveau compte 
	 * @param rib rib du compte
	 * @param iban iban du compte
	 * @param ClientID identifiant du client
	 */
	public Compte(String rib, String iban,
			 int ClientID) {
		
		this.rib=rib;
		this.iban=iban; this.ClientID=ClientID;
	}
	
	/**
	 * Getter du num�ro de compte
	 * @return le num�ro du compte
	 */
	public String getNumCompte() {return numCompte;}
	/**
	 * Getter du rib du compte
	 * @return le rib du compte
	 */
	public String getRib() {return rib;}
	/**
	 * Getter iban
	 * @return l'iban du compte
	 */
	public String getIban() {return iban;}
	/**
	 * Getter bic
	 * @return le bic du compte
	 */
	public String getBic() {return bic;}
	/**
	 * Getter de l'id du client
	 * @return l'id du client d�tenteur du compte
	 */
	public int getClientID() {return ClientID;}
	
	/**
	 * Setter du rib
	 * @param rib le nouveau rib
	 */
	public void setRib(String rib) {this.rib = rib;}
	/**
	 * Setter de l'iban
	 * @param iban le nouvel iban
	 */
	public void setIban(String iban) {this.iban=iban;}
	/**
	 * Setter du bic
	 * @param bic le nouveau bic
	 */
	public void setBic(String bic) {this.bic=bic;}
	/**
	 * Setter de l'id du client
	 * @param idClient l'id du client
	 */
	public void setClientID(int idClient) {this.ClientID=idClient;}
}