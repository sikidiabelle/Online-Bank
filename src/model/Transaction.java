package model;
/**
 * Classe représentant une transaction
 * @author Moussa C.
 *
 */
public class Transaction {
	/**
	 * id de la transaction dans la BDD
	 */
	private int idTrans;
	/**
	 * description de la transaction (dépot, retrait, virement, achat en ligne...)
	 */
	private String descp;
	/**
	 * numero du compte source
	 */
	private String numCpt_src;
	/**
	 * iban du compte cible
	 */
	private String iban_cible;
	/**
	 * montant de la transaction
	 */
	private double montant;
	/**
	 * type d'operation (false pour un debit et true pour un credit)
	 */
	private boolean type;
	/**
	 * date et heure de l'opération
	 */
	private java.sql.Timestamp date = null;
	/**
	 * Constructeur d'une transaction avec id
	 * @param idTrans id de la transaction dans la BDD
	 * @param descp description de la transaction
	 * @param numCpt_src numero du compte source
	 * @param iban_cible iban du compte cible
	 * @param montant montant de la transaction
	 * @param type type d'operation (false pour un debit et true pour un credit)
	 * @param date date et heure de l'opération
	 */
	public Transaction(int idTrans, String descp, String numCpt_src, String iban_cible,
			double montant, boolean type, java.sql.Timestamp date) {
		this.idTrans = idTrans; this.numCpt_src = numCpt_src; this.iban_cible = iban_cible;
		this.montant = montant; this.type = type; this.descp = descp; this.date = date; 
	}
	
	/**
	 * Constructeur d'une transaction sans id
	 * @param descp description de la transaction
	 * @param numCpt_src numero du compte source
	 * @param iban_cible iban du compte cible
	 * @param montant montant de la transaction
	 * @param type type d'operation (false pour un debit et true pour un credit)
	 */
	public Transaction(String descp, String numCpt_src, String iban_cible,
			double montant, boolean type) {
		this.numCpt_src = numCpt_src; this.iban_cible = iban_cible;
		this.montant = montant; this.type = type; this.descp = descp;
	}
	
	/**
	 * Getter de l'id
	 * @return l'id de la transaction dans la BDD
	 */
	public int getIdTrans() { return idTrans; }
	/**
	 * Getter de la description
	 * @return description de la transaction
	 */
	public String getDescp() { return descp; }
	/**
	 * Getter du numero du compte source
	 * @return le numero du compte source
	 */
	public String getNumCpt_src() { return numCpt_src; }
	/**
	 * Getter du iban_cible iban du compte cible
	 * @return le iban_cible iban du compte cible
	 */
	public String getIban_cible() { return iban_cible; }
	/**
	 * Getter du montant
	 * @return le montant de la transaction
	 */
	public double getMontant() { return montant; }
	/**
	 * Getter du type
	 * @return le type d'operation (false pour un debit et true pour un credit)
	 */
	public boolean getType() { return type; }
	/**
	 * Getter de la date
	 * @return la date et l'heure de l'opération
	 */
	public java.sql.Timestamp getDate() { return date; }

}