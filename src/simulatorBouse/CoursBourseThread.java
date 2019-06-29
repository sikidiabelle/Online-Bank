package simulatorBouse;

import java.util.Date;
 
import javax.servlet.ServletContext;
 
public class CoursBourseThread implements Runnable {
 
    private ServletContext context;
    private CoursBourseDAO cbDao = new CoursBourseDAO();
 
    public CoursBourseThread(ServletContext context) {
        this.context = context;
    }
 
    @Override
    public void run() {
    	System.out.println("*****************************************************");
    	System.out.println("Generation d'un nouveau cours de la bourse ... " + new Date());
 
        try {
        	genIndice();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("G�n�ration termin�e!!!");
        System.out.println("*****************************************************");
    }
 
    private void genIndice() throws Exception {
    	//D�finition des valeurs min et max du taux de variation
    	double min = -0.01;
    	double max = 0.01;
    	
    	//G�n�ration d'un taux compris entre min et max
    	double taux = min + (Math.random()*(max-min));
    	
    	//R�cup�ration du dernier cours de la bourse
    	CoursBourse cb = this.cbDao.getLastCours();
    	double indice = cb.getIndice();
    	
    	//D�finition d'un nouvel indice
    	double newIndice = indice+(indice*taux);
    	double roundIndice = ((double) Math.round(newIndice*100))/100; //Arrondi � 2 chiffres
    	
    	//Cr�ation d'un nouceau cours � partir de l'indice du cours pr�c�dent
    	CoursBourse newCB = new CoursBourse(roundIndice);
    	
    	//Ajout du nouveau cours � la BDD
    	cbDao.add(newCB);
    	
    	//Affichage des donn�es manipul�es
    	System.out.println("Dernier indice dans la BDD: "+indice);
    	System.out.println("Taux de variation : "+taux);
    	System.out.println("Indice ajout� � la BDD: "+roundIndice);
    }
 
}