package main;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Classe qui permet de sauvegarder une liste d'affectation
 * @author Constantine Benjohnson
 * 
 */
public class Sauvegarde {
	
	/**
	 * M�thode qui permet de sauvegarder une liste d'affectation.<p>
	 * La m�thode sauvegarde le fichier � l'emplacement "solution/'nomfichier'.txt"
	 * @param affectation Affectation
	 * @param sc Scanner
	 */
	public static void sauvegarde(Affectation affectation, Scanner sc) {
		
		StringBuffer sb = new StringBuffer();
		boolean end = false;
		File file;
		
		for(Pirate p : affectation.getAffectation().keySet()) {
			sb.append(p.getEtiquette()+":"+affectation.getAffectation().get(p)+"\n");
        }
		
		do {
			System.out.println("Quel est le nom du fichier pour la sauvegarde ?");
			sc.nextLine();
			String nomFichier = sc.nextLine();
			if(!nomFichier.endsWith(".txt")) {
				nomFichier += ".txt";
			}
			file = new File("solution/"+nomFichier);
			try {
				PrintWriter fichier = new PrintWriter(file);
				fichier.print(sb.toString());
				fichier.close();
				end = true;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}while(!end);
		
		System.out.println("Votre fichier a �t� sauvegard� � l'emplacement : "+ file.getAbsolutePath());
	}
	
}
