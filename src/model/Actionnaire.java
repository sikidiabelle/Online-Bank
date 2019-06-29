package model;

/**
 * Classe permettant de d�finir des acquisitions d'action
 * @author TIC1.3
 *
 */
public class Actionnaire {
	/**
	 * id de l'acquisition
	 */
	private int id=0;
	/**
	 * id de l'action d�tenue
	 */
	private int idAction;
	/**
	 * numero du compte titre d�tenteur de l'action
	 */
	private String numCpt;
	/**
	 * quantit� d'actions d�tenues
	 */
	private int quantite;
	
	/**
	 * Constructeur actionnaire sans id dans la BDD
	 * @param idAction id de l'action d�tenue
	 * @param numCpt numero du compte titre d�tenteur de l'action
	 * @param quantite quantit� d'actions d�tenues
	 */
	public Actionnaire(int idAction, String numCpt, int quantite) {
		this.idAction=idAction; this.numCpt=numCpt; this.quantite=quantite;
	}
	
	/**
	 * Constructeur actionnaire avec id dans la BDD
	 * @param id id de l'acquisition 
	 * @param idAction id de l'action d�tenue
	 * @param numCpt numero du compte titre d�tenteur de l'action
	 * @param quantite quantit� d'actions d�tenues
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
	 * @return l'id de l'action d�tenue
	 */
	public int getIdAction() { return idAction; }
	/**
	 * Getter num�ro de compte
	 * @return le num�ro de compte du d�tenteur de l'action
	 */
	public String getNumCpt() { return numCpt; }
	/**
	 * Getter de la quantite
	 * @return la quantite d'actions d�tenues
	 */
	public int getQuantite() { return quantite; }
}
