package model;

/**
 * Classe représentant un compte bancaire
 * @author TIC1.3
 *
 */
public class CBancaire extends Compte {
	/**
	 * Solde dans le compte
	 */
	private double solde;
	/**
	 * plafond virement
	 */
	private int plVir;
	/**
	 * plafond retrait
	 */
	private int plRetrait;
	/**
	 * plafond découvert
	 */
	private int plDecouvert;
	/**
	 * si avec carte bancaire
	 */
	private boolean carte;
	/**
	 * si avec chéquier
	 */
	private boolean chequier;
	
	/**
	 * Constructeur compte bancaire 
	 * @param numCompte numéro du compte
	 * @param rib rib du compte
	 * @param iban iban du compte bancaire
	 * @param ClientID identifiant du client
	 * @param solde solde du compte
	 * @param plVir plafond virement
	 * @param plRetrait plafond retrait
	 * @param plDecouvert plafond decouvert
	 * @param carte existance carte bancaire
	 * @param chequier existance chequier
	 */
	public CBancaire(String numCompte, String rib, String iban,
			int ClientID, double solde, int plVir, int plRetrait, int plDecouvert, boolean carte, boolean chequier) {
		super(numCompte, rib, iban, ClientID);
		this.solde = solde;
		this.plVir=plVir;
		this.plRetrait=plRetrait;
		this.plDecouvert=plDecouvert;
		this.carte=carte;
		this.chequier=chequier;
	}
	
	/**
	 * Constructeur compte bancaire 
	 * @param rib rib du compte bancaire
	 * @param iban iban du compte bancaire
	 * @param ClientID identifiant du client
	 * @param solde solde du compte
	 * @param plVir plafond virement
	 * @param plRetrait plafond retrait
	 * @param plDecouvert plafond decouvert
	 * @param carte existance carte bancaire
	 * @param chequier existance chequier
	 */
	public CBancaire(String rib, String iban,
			 int ClientID, double solde, int plVir, int plRetrait, int plDecouvert, boolean carte, boolean chequier) {
		super(rib,iban, ClientID);
		this.solde = solde;
		this.plVir=plVir;
		this.plRetrait=plRetrait;
		this.plDecouvert=plDecouvert;
		this.carte=carte;
		this.chequier=chequier;
		
	}
	
	/**
	 * Getter du solde
	 * @return le solde du compte bancaire
	 */
	public double getsolde() {return solde;}
	/**
	 * Getter du plafond de virement
	 * @return le plafond de virement
	 */
	public int getplVir() {return plVir;}
	/**
	 * Getter du plafond de retrait
	 * @return le plafond de retrait
	 */
	public int getplRetrait() {return plRetrait;}
	/**
	 * Getter du plafond de decouvert
	 * @return le plafond de découvert
	 */
	public int getplDecouvert() {return plDecouvert;}
	/**
	 * Getter de carte
	 * @return true si carte associée et false sinon
	 */
	public boolean getcarte() {return carte;}
	/**
	 * Getter cheque
	 * @return true si chequier associé et false sinon
	 */
	public boolean getchequier() {return chequier;}

}
