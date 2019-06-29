package model;

public class Cheque {
	/**
	 * Identifiant du chquier
 	 */
	public int idCheq;
	/**
	 * Numéro du compte
	 */
	public String numCpt;
	/**
	 * Numéro du chéquier
	 */
	public String numCheq;
	
	/**
	 * Constructeur chequier
	 * @param numCpt numéro du compte
	 * @param numCheq numéro du chequier 
	 */
	public Cheque(String numCpt, String numCheq) {
		this.numCpt=numCpt;
		this.numCheq=numCheq;
	}
	
	/**
	 * Getter de l'id du chequier
	 * @return l'id du chequier
	 */
	public int getidCheq() {return idCheq;}
	/**
	 * Getter du numéro de chequier
	 * @return le numero du chequier
	 */
	public String getnumCheq() {return numCheq;}
	/**
	 * Getter du numero de compte
	 * @return le numero de compte associé
	 */
	public String getnumCpt() {return numCpt;}
	
	/**
	 * Setter du numéro de chequier
	 * @param n le nouveau numero du chequier
	 */
	public void setnumCheq(String n) {this.numCheq=n;}
	/**
	 * Setter du numéro de compte
	 * @param n le nouveau numéro de compte
	 */
	public void setnumCpt(String n) {this.numCpt=n;}
}

