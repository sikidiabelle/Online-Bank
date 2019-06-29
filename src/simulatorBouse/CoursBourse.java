package simulatorBouse;

import java.sql.Timestamp;

public class CoursBourse {
	/**
	 * id du cours de la bourse
	 */
	private int idCours = 0;
	/**
	 * Date de la variation du cours observ�e
	 */
	private Timestamp dateCours=null;
	/**
	 * indice bourse � la date donn�e
	 */
	private double indice;
	
	/**
	 * Constructeur sans id ni date
	 * @param indice indice bourse � la date donn�e
	 */
	public CoursBourse(double indice) {
		this.indice = indice;
	}
	
	/**
	 * Constructeur avec id et date
	 * @param idCours id du cours
	 * @param dateCours Date de la variation du cours observ�e
	 * @param indice indice bourse � la date donn�e
	 */
	public CoursBourse(int idCours, java.sql.Timestamp dateCours, double indice) {
		this.idCours = idCours; this.dateCours = dateCours; this.indice = indice;
	}
	
	public Timestamp getDateCours() { return dateCours; }
	public double getIndice() { return indice; }
	public int getIdCours() { return idCours; }
	
	public String toString() {
		return "CoursBourse no."+idCours+" *** date : "+dateCours+" *** indice : "+indice;
	}

}
