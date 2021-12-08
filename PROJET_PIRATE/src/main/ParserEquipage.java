package main;

import java.io.BufferedReader;
import java.io.FileReader;

public class ParserEquipage {

	public static Equipage parseFile(String file) {
		
		Equipage equipage = new Equipage();
		
		try {
			int ligneindex = 0;
			int warning = 0;
			FileReader fReader = new FileReader(file);
			BufferedReader bReader = new BufferedReader(fReader);
			String ligne = null;
			while((ligne = bReader.readLine()) != null) {
				if(ligne.contains(" ")) {
					System.out.println("Erreur votre ligne [" + ligneindex + "] contient un ou plusieurs espace! Vérifiez que la ligne ai un format approprié.");
					bReader.close();
					return null;
				}
				else {
					if(ligne.startsWith("pirate")) {
						if(!equipage.ajoutPirate(ligne.split("//(")[1].split("//)")[0])) {
							System.out.println("Attention! Le pirate à votre ligne [" + ligneindex + "] n'a pas pu être ajouté, il se peut que le pirate est déjà présent dans votre équipage");
							warning++;
						}
					}
					else if(ligne.startsWith("objet")) {
						if(!equipage.ajoutObjet(ligne.split("//(")[1].split("//)")[0])) {
							System.out.println("Attention! L'objet à votre ligne [" + ligneindex + "] n'a pas pu être ajouté, il se peut que l'objet est déjà présent dans votre équipage");
							warning++;
						}
					}
					else if(ligne.startsWith("deteste")) {
						if(!equipage.ajoutRelation(ligne.split("//(")[1].split("//)")[0].split(",")[0],ligne.split("//(")[1].split("//)")[0].split(",")[1])) {
							System.out.println("Attention! La relation à votre ligne [" + ligneindex + "] n'a pas pu être ajouté, il se peut que les pirates ne soit pas présent dans l'équipage");
							warning++;
						}
					}
					else if(ligne.startsWith("preferences")) {
						String[] lignesplitted = ligne.split("//(")[1].split("//)")[0].split(",");
						if(lignesplitted.length < (equipage.getObjetCount()+1)) {
							System.out.println("Erreur à votre ligne [" + ligneindex + "] la liste de preference n'a pas assez d'objets! Lors de la tentative d'ajout d'objet l'equipage a :" + equipage.getObjetCount() + "objets.");
							bReader.close();
							return null;
						}
						if(lignesplitted.length > (equipage.getObjetCount()+1)) {
							System.out.println("Erreur à votre ligne [" + ligneindex + "] la liste de preference a trop d'objets! Lors de la tentative d'ajout d'objet l'equipage a :" + equipage.getObjetCount() + "objets.");
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
							System.out.println("Attention! nom pirate invalide à la ligne [" + ligneindex + "]! Il se peut que le pirate n'existe pas dans l'equipage");
							warning++;
						}
						else if(retour == 2) {
							System.out.println("Attention! liste de preferences invalide à la ligne [" + ligneindex + "]! Il se peut que l'un ou plusieurs des objets que vous tentiez d'ajouter n'existe(nt) pas dans l'equipage");
							warning++;
						}
					}
					else {
						System.out.println("Erreur Syntaxe en début de ligne [" + ligneindex + "]! Vérifiez que le début de la ligne ai un format approprié.");
						bReader.close();
						return null;
					}
				}
			}
			for (Pirate v : equipage.getMap().keySet()) {
				if(v.getPreference().isEmpty()) {
					System.out.println("Attention! Un ou plusieurs pirates n'ont pas de préférences.");
					warning++;
				}
			}
			bReader.close();
			System.out.println("Création de l'equipage réussi, vous avez " + warning + " messages qui requiert votre attention.");
			return equipage;
		}catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}

}
