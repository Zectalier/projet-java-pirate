package main;

import java.util.Scanner;

/**
 * Affichage d'un menu pour que l'utilisateur configure son equipage
 * @author Hu Tony 
 * @author Constantine Benjohnson
 * 
 */
public class Main {
	
	public static void main (String[]args) {
		
		System.out.println("Nom du fichier");
		@SuppressWarnings("resource")
		Scanner s = new Scanner (System.in);
		String NomFichier = s.nextLine();
		
		Equipage graphe = ParserEquipage.parseFile(NomFichier);
		Affectation affectation = new Affectation (graphe);
		Menu.menuCommand(affectation, graphe);
	}
}
