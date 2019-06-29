package model;

/**
 * Classe permettant de définir des acquisitions d'action
 * @author TIC1.3
 *
 */
public class Actionnaire {
	/**
	 * id de l'acquisition
	 */
	private int id=0;
	/**
	 * id de l'action détenue
	 */
	private int idAction;
	/**
	 * numero du compte titre détenteur de l'action
	 */
	private String numCpt;
	/**
	 * quantité d'actions détenues
	 */
	private int quantite;
	
	/**
	 * Constructeur actionnaire sans id dans la BDD
	 * @param idAction id de l'action détenue
	 * @param numCpt numero du compte titre détenteur de l'action
	 * @param quantite quantité d'actions détenues
	 */
	public Actionnaire(int idAction, String numCpt, int quantite) {
		this.idAction=idAction; this.numCpt=numCpt; this.quantite=quantite;
	}
	
	/**
	 * Constructeur actionnaire avec id dans la BDD
	 * @param id id de l'acquisition 
	 * @param idAction id de l'action détenue
	 * @param numCpt numero du compte titre détenteur de l'action
	 * @param quantite quantité d'actions détenues
	 */
	public Actionnaire(int id, int idAction, String numCpt, int quantite) {
		this.id=id; this.idAction=idAction; this.numCpt=numCpt; this.quantite=quantite;
	}
	
	/**
	 * Getter de l'id
	 * @return l'id de l'acquisition
	 */
	public int getId() { return id; }
	/**
	 * Getter de l'id de l'Action
	 * @return l'id de l'action détenue
	 */
	public int getIdAction() { return idAction; }
	/**
	 * Getter numéro de compte
	 * @return le numéro de compte du détenteur de l'action
	 */
	public String getNumCpt() { return numCpt; }
	/**
	 * Getter de la quantite
	 * @return la quantite d'actions détenues
	 */
	public int getQuantite() { return quantite; }
}
