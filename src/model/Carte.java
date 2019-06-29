package model;

/**
 * Classe représentant une carte bancaire
 * @author TIC1.3
 *
 */
public class Carte {
	/**
	 * Identifiant de la carte dans la BDD
	 */
	public int idCarte;
	/**
	 * Numéro de carte
	 */
	public String numCrt;
	/**
	 * Numéro du compte associé à la carte
	 */
	public String numCpt;
	/**
	 * Date d'expiration de la carte
	 */
	public String dateExp;
	/**
	 * Numéro de vérification de la carte
	 */
	public String cvc;
	
	/**
	 * Constructeur carte bancaire
	 * @param numCrt numro de la carte bancaire
	 * @param numCpt numro de la banque
	 * @param dateExp date d'expiration de la carte
	 * @param cvc Numéro de vérification de la carte
	 */
	public Carte(String numCrt, String numCpt, String dateExp, String cvc) {
		this.numCrt=numCrt;
		this.numCpt=numCpt;
		this.dateExp=dateExp;
		this.cvc=cvc;
		
	}
	
	/**
	 * Getter de l'id de la carte
	 * @return l'id de la carte
	 */
	public int getidCarte() {return idCarte;}
	/**
	 * Getter du numero de la carte
	 * @return le numero de la carte
	 */
	public String getnumCrt() {return numCrt;}
	/**
	 * Getter du numero de compte
	 * @return le numero du compte associé à cette carte
	 */
	public String getnumCpt() {return numCpt;}
	/**
	 * Getter de la date d'expiration
	 * @return la date d'expiration de la carte
	 */
	public String getdate() {return dateExp;}
	/**
	 * Getter du cvc
	 * @return le numero de validation de la carte 
	 */
	public String getcvc() {return cvc;}
	
	/**
	 * Setter du numero de carte
	 * @param n le nouveau numero de carte
	 */
	public void setnumCrt(String n) {this.numCrt=n;}
	/**
	 * Setter du numero de compte
	 * @param n le nouveau numero de compte
	 */
	public void setnumCpt(String n) {this.numCpt=n;}
	/**
	 * Setter de la date d'expiration
	 * @param n la nouvelle date d'expiration
	 */
	public void setdate(String n) {this.dateExp=n;}
	/**
	 * Setter du cvc
	 * @param n le nouveau numéro de valisation de carte
	 */
	public void setcvc(String n) {this.cvc=n;}
}

