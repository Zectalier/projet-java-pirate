package main;

import java.util.Scanner;

/**
 * Classe de test pour la class ParserEquipage
 * @author Hu Tony
 *
 */
public class ParserEquipageTest {

	/**
	 * Méthode main de la classe
	 * @param args non nécessaire
	 */
	public static void main (String[]args) {
		
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		int choix = -1;
		String nomFichier;
		Equipage equipage;
		AffectationAutomatique aff;
		Scanner sc = new Scanner (System.in);
		while(choix != 0) {
			choix = sc.nextInt();
			switch(choix) {
				case 1:
					sc.nextLine();
					nomFichier = sc.nextLine();
					equipage = ParserEquipage.parseFile(nomFichier);
					System.out.println(equipage.toString());
					aff = new AffectationAutomatique(equipage,10);
					System.out.println(aff.toString());
					break;
				case 2:
					nomFichier = "equipagetest";
					equipage = ParserEquipage.parseFile(nomFichier);
					System.out.println(equipage.toString());
					aff = new AffectationAutomatique(equipage,10);
					System.out.println(aff.toString());
					break;
				case 3:
					nomFichier = "equipagetesterreur";
					equipage = ParserEquipage.parseFile(nomFichier);
					System.out.println(equipage.toString());
					break;
				case 4:
					nomFichier = "equipagetsyntaxeincorrecte";
					equipage = ParserEquipage.parseFile(nomFichier);
					break;
				case 5:
					nomFichier = "equipagespacevide";
					equipage = ParserEquipage.parseFile(nomFichier);
					break;
				case 6:
					nomFichier = "equipagepreferenceincorrect";
					equipage = ParserEquipage.parseFile(nomFichier);
					break;
				default:
					break;
			}
		}
		sc.close();
	}
	
}
