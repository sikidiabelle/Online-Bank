package traitement;

import java.io.*; 
import java.util.*; 
import com.opencsv.CSVWriter;

import model.Transaction; 

/**
 * Classe permettant de générer un fichier csv
 * @author Moussa C.
 */
public class GenerateCSV {
	
	/**
	 * Permet de créer et remplir le fiichier csv
	 * @param filePath le répertoire de stockage du fichier
	 * @param fileName le nom du fichier
	 * @param listTrans la liste des transactions à insérer dans le fichier
	 */
	public void writeDataAtOnce(String filePath, String fileName, ArrayList<Transaction> listTrans) {
	    // first create file object for file placed in filename given 
	    File file = new File(filePath+"/"+fileName+".csv");
	    File fileDir = new File(filePath);
	    
	    //Creation du repertoire si inexistant
	    fileDir.mkdir();
	  
	    try {
	        //Creer un objet FileWriter avec file en parametre 
	        FileWriter outputfile = new FileWriter(file); 
	  
	        //Creer un objet CSVWriter avec filewriter en parametre 
	        CSVWriter writer = new CSVWriter(outputfile, ';', 
				CSVWriter.NO_QUOTE_CHARACTER,
				CSVWriter.DEFAULT_ESCAPE_CHARACTER, 
				CSVWriter.DEFAULT_LINE_END);
	        
	        //Crer une liste stockant les String[] contenant les donnees
	        List<String[]> data = new ArrayList<String[]>();
	        
	        //Entête du fichier csv
	        String[] header = {"Description", "Numéro Compte (source/destinataire)", "Montant", "Type", "Date de l'opération"};
	        data.add(header);
	        
	        for(Transaction t : listTrans ) {
	        	//Conversion montant de l'operation en string
	        	String amount = String.valueOf(t.getMontant()) + " EUR";
	        	
	        	//Conversion du type d'operation en String : true => Crédit ; false => Débit
	        	String type;
	        	if (t.getType()) {type = "Crédit";}
	        	else {type = "Débit";}
	        	
	        	//Creation d'une liste des donnees de la transaction
	        	String[] d = {t.getDescp(), t.getIban_cible(), amount, type, t.getDate().toString()};
	        	
	        	//Ajout de la liste aux donnees
	        	data.add(d);
	        	
	        }
	        
	        //Ajout de toutes les infos au CSV
	        writer.writeAll(data);
	  
	        // closing writer connection 
	        writer.close(); 
	        
	        System.out.println(file.getAbsolutePath());
	    } catch (IOException e) { e.printStackTrace(); }
	}
	
	public static void main(String[] args) { 
		Transaction t0 = new Transaction(
				"Operation 0",
				"azer",
				"azer",
				100,
				true
				);
		Transaction t1 = new Transaction(
				"Operation 1",
				"azer",
				"azer",
				100,
				true
				);
		Transaction t2 = new Transaction(
				"Operation 2",
				"azer",
				"azer",
				100,
				true
				);
		
		ArrayList<Transaction> list = new ArrayList<Transaction>();
		list.add(t0);list.add(t1);list.add(t2);
		/*GenerateCSV g = new GenerateCSV(); 
		g.writeDataAtOnce("test", list);*/
	}
}