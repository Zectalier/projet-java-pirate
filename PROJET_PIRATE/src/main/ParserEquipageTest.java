package main;

import java.util.Scanner;

public class ParserEquipageTest {

	public static void main (String[]args) {
		
		Scanner sc = new Scanner (System.in);
		String nomFichier = sc.nextLine();
		Equipage equipage = ParserEquipage.parseFile(nomFichier);
		System.out.println(equipage.toString());
		sc.close();
	}
}
