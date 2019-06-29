package model;

public class Cheque {
	/**
	 * Identifiant du chquier
 	 */
	public int idCheq;
	/**
	 * Num�ro du compte
	 */
	public String numCpt;
	/**
	 * Num�ro du ch�quier
	 */
	public String numCheq;
	
	/**
	 * Constructeur chequier
	 * @param numCpt num�ro du compte
	 * @param numCheq num�ro du chequier 
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
	 * Getter du num�ro de chequier
	 * @return le numero du chequier
	 */
	public String getnumCheq() {return numCheq;}
	/**
	 * Getter du numero de compte
	 * @return le numero de compte associ�
	 */
	public String getnumCpt() {return numCpt;}
	
	/**
	 * Setter du num�ro de chequier
	 * @param n le nouveau numero du chequier
	 */
	public void setnumCheq(String n) {this.numCheq=n;}
	/**
	 * Setter du num�ro de compte
	 * @param n le nouveau num�ro de compte
	 */
	public void setnumCpt(String n) {this.numCpt=n;}
}

