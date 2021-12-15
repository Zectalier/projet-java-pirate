package main;

import java.util.Scanner;

/**
 * Main Class. Contient la méthode main.
 * @author Hu Tony 
 * @author Constantine Benjohnson
 * 
 */
public class Main {
	
	/**
	 * Méthode principale
	 * @param args non necessaire
	 */
	public static void main (String[]args) {
		
		Equipage equipage;
		Scanner sc = new Scanner(System.in);
		equipage = Menu.loadFile(sc);
		Menu.menuCommand(equipage,sc);
		
	}
}
