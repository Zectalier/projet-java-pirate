package main;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Classe qui permet de lire le contenu d'un fichier afin d'y r?cuper un Equipage
 * @author Hu Tony
 */
public class ParserEquipage {

	/**
	 * M?thode qui permet de lire un fichier et de construire un ?quipage avec le contenu du fichier.<p>
	 * L'emplacement du fichier se trouve ? "save/'nomfichier'"
	 * @param file le nom du fichier
	 * @return Equipage
	 */
	public static Equipage parseFile(String file) {
		
		Equipage equipage = new Equipage();
		
		try {
			int ligneindex = 0;
			int warning = 0;
			FileReader fReader = new FileReader("save/"+file);
			BufferedReader bReader = new BufferedReader(fReader);
			String ligne = null;
			while((ligne = bReader.readLine()) != null) {
				if(ligne.contains(" ")) {
					System.out.println("Erreur votre ligne [" + ligneindex + "] contient un ou plusieurs espace! Vérifiez que la ligne ai un format approprié.");
					bReader.close();
					return null;
				}
				else {
					if(ligne.startsWith("pirate(")) {
						if(!equipage.ajoutPirate(ligne.split("\\(")[1].split("\\)")[0])) {
							System.out.println("Attention! Le pirate ? votre ligne [" + ligneindex + "] n'a pas pu ?tre ajout?, il se peut que le pirate soit d?j??pr?sent dans votre ?quipage");
							Thread.sleep(1000);
							warning++;
						}
					}
					else if(ligne.startsWith("objet(")) {
						if(!equipage.ajoutObjet(ligne.split("\\(")[1].split("\\)")[0])) {
							System.out.println("Attention! L'objet ? votre ligne [" + ligneindex + "] n'a pas pu ?tre ajout?, il se peut que l'objet soit d?j? pr?sent dans votre ?quipage");
							Thread.sleep(1000);
							warning++;
						}
					}
					else if(ligne.startsWith("deteste(")) {
						if(!equipage.ajoutRelation(ligne.split("\\(")[1].split("\\)")[0].split(",")[0],ligne.split("\\(")[1].split("\\)")[0].split(",")[1])) {
							System.out.println("Attention! La relation ? votre ligne [" + ligneindex + "] n'a pas pu ?tre ajout?, il se peut que les pirates ne soit pas pr?sent dans l'?quipage");
							Thread.sleep(1000);
							warning++;
						}
					}
					else if(ligne.startsWith("preferences(")) {
						String[] lignesplitted = ligne.split("\\(")[1].split("\\)")[0].split(",");
						if(lignesplitted.length < (equipage.getObjetCount()+1)) {
							System.out.println("Erreur ? votre ligne [" + ligneindex + "] la liste de preference n'a pas assez d'objets! Lors de la tentative d'ajout d'objet l'equipage a : " + equipage.getObjetCount() + " objets.");
							bReader.close();
							return null;
						}
						if(lignesplitted.length > (equipage.getObjetCount()+1)) {
							System.out.println("Erreur ? votre ligne [" + ligneindex + "] la liste de preference a trop d'objets! Lors de la tentative d'ajout d'objet l'equipage a : " + equipage.getObjetCount() + " objets.");
							bReader.close();
							return null;
						}
						StringBuilder sb = new StringBuilder();
						sb.append(lignesplitted[0]);
						for(int i = 1; i < lignesplitted.length; i++) {
							sb.append(" ");
							sb.append(lignesplitted[i]);
						}
						String str = sb.substring(0);
						int retour = equipage.ajoutPreference(str);
						if(retour == 1) {
							System.out.println("Attention! Nom pirate invalide ? la ligne [" + ligneindex + "]! Il se peut que le pirate n'existe pas dans l'equipage");
							Thread.sleep(1000);
							warning++;
						}
						else if(retour == 2) {
							System.out.println("Attention! Liste de preferences invalide ? la ligne [" + ligneindex + "]! Il se peut que l'un ou plusieurs des objets que vous tentiez d'ajouter n'existe(nt) pas dans l'equipage");
							Thread.sleep(1000);
							warning++;
						}
					}
					else {
						System.out.println("Erreur Syntaxe en d?but de ligne [" + ligneindex + "]! V?rifiez que le d?but de la ligne ai un format appropri?.");
						bReader.close();
						return null;
					}
				}
				ligneindex++;
			}
			for (Pirate v : equipage.getMap().keySet()) {
				if(v.getPreference().isEmpty()) {
					System.out.println("Attention! Un ou plusieurs pirates n'ont pas de pr?f?rences.");
					warning++;
				}
			}
			bReader.close();
			System.out.println("\nCr?ation de l'equipage r?ussi, vous avez " + warning + " messages qui requiert votre attention.\n");
			Thread.sleep(1000);
			return equipage;
		}catch (Exception e) {
			System.err.println(e.toString());
			return null;
		}
	}

}
