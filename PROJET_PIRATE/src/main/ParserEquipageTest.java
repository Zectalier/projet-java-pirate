package main;

import java.util.Scanner;

public class ParserEquipageTest {

	public static void main (String[]args) {
		
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		int choix = -1;
		String nomFichier;
		Equipage equipage;
		Scanner sc = new Scanner (System.in);
		while(choix != 0) {
			choix = sc.nextInt();
			switch(choix) {
				case 1:
					nomFichier = sc.nextLine();
					equipage = ParserEquipage.parseFile(nomFichier);
					System.out.println(equipage.toString());
					break;
				case 2:
					nomFichier = "equipagetest.txt";
					equipage = ParserEquipage.parseFile(nomFichier);
					System.out.println(equipage.toString());
					break;
				case 3:
					nomFichier = "equipagetesterreur.txt";
					equipage = ParserEquipage.parseFile(nomFichier);
					System.out.println(equipage.toString());
					break;
				case 4:
					nomFichier = "equipagetsyntaxeincorrecte.txt";
					equipage = ParserEquipage.parseFile(nomFichier);
					break;
				case 5:
					nomFichier = "equipagespacevide.txt";
					equipage = ParserEquipage.parseFile(nomFichier);
					break;
				case 6:
					nomFichier = "equipagepreferenceincorrect.txt";
					equipage = ParserEquipage.parseFile(nomFichier);
					break;
				default:
					break;
			}
		}
		sc.close();
	}
	
}
