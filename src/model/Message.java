package model;

/**
 * Classe message contenant les infos des message de visiteurs
 * @author Moussa C.
 */
public class Message {
	/**
	 * id du message dans la BDD
	 */
	private int id = 0;
	/**
	 * email de l'expediteur
	 */
	private String email;
	/**
	 * objet du message
	 */
	private String objet;
	/**
	 * contenu du message
	 */
	private String textMessage;
	
	/**
	 * Constructeur pour un nouveau message (pas encore d'id) 
	 * @param email email de l'expediteur
	 * @param objet objet du message
	 * @param textMessage contenu du message
	 */
	public Message(String email, String objet, String textMessage) {
		this.email=email; this.objet=objet; this.textMessage=textMessage;
	}
	
	/**
	 * Getter de l'id
	 * @return l'id du message
	 */
	public int getId() { return id; }
	/**
	 * Getter de l'email
	 * @return l'email de l'expediteur
	 */
	public String getEmail() { return email; }
	/**
	 * Getter de l'objet
	 * @return l'objet du message
	 */
	public String getObjet() { return objet; }
	/**
	 * Getter du texte
	 * @return le texte du message
	 */
	public String getTextMessage() { return textMessage; }
}
