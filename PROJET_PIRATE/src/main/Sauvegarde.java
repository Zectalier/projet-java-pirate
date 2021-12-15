package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Sauvegarde {
	public static void sauvegarde(Affectation affectation) {
		
		StringBuffer solution = new StringBuffer();
		for(Pirate p : affectation.getAffectation().keySet()) {
			solution.append(p.getEtiquette()+":"+affectation.getAffectation().get(p)+"\n");
        }
		boolean end = false;
		do {
			
			System.out.println("Quel est le nom du fichier pour la sauvegarde ?");
			@SuppressWarnings("resource")
			Scanner s = new Scanner (System.in);
			String NomFichier = s.nextLine();
	
			try {
				PrintWriter fichier = new PrintWriter (NomFichier+".txt","UTF-8");
				fichier.print(solution.toString());
				fichier.close();
				end = true;
			}catch(FileNotFoundException e) {
				System.out.println("Le fichier n'a pas été trouvé");
			} catch (IOException e) {
				e.printStackTrace();
			}
			//s.close();
		}while(!end);
	}
}
