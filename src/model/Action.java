package model;

/**
 * Classe représentant les actions du CAC40 
 * @author TIC1.3
 *
 */
public class Action {
	/**
	 * id de l'action dans la BDD
	 */
	private int id=0;
	/**
	 * Nom de l'entreprise associée à l'action
	 */
	private String nom;
	/**
	 * Prix unitaire de l'action
	 */
	private double prix;
	
	/**
	 * Constructeur d'un objet action sans id dans la BDD
	 * @param nom nom de l'entreprise associée à l'action
	 * @param prix prix unitaire de l'action
	 */
	public Action(String nom,double prix) {
		this.nom = nom;
		this.prix= prix;
	}
	
	/**
	 * Constructeur d'un objet action avec un id dans la BDD
	 * @param id id de l'action dans la BDD
	 * @param nom nom de l'entreprise détentrice de l'action
	 * @param prix prix unitaire de l'action
	 */
	public Action(int id,String nom,double prix) {
		this.id=id;
		this.nom = nom;
		this.prix= prix;

	}
	
	/**
	 * Getter pour l'id
	 * @return l'id de l'Action
	 */
	public int getId() { return id; }
	/**
	 * Getter pour le nom
	 * @return le nom de l'action
	 */
	public String getNom() { return nom; }
	/**
	 * Getter pour le prix
	 * @return le prix de l'action
	 */
	public double getPrix() { return prix; }
}
