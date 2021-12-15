package main;

import java.util.Scanner;

public class Menu {

	public static void menuCommand(Affectation affectation, Equipage equipage) {
		
		Scanner sc = new Scanner (System.in);	
		int choix;
		int k=0;
		
		do {
			System.out.println("***** MENU *****");

			System.out.println("1- Résolution automatique");
			System.out.println("2- Résolution manuelle");
			System.out.println("3- Sauvegarder");
			System.out.println("4- Fin");
			choix= sc.nextInt();

			switch(choix) {
				case 1 :
					System.out.println("Veuillez choisir k :");
					k=sc.nextInt();
					affectation = new AffectationAutomatique(equipage,k);
					
					System.out.println("Voici la solution proposé par la machine : ");
					System.out.println(affectation.toString());
					System.out.println("Le cout est �quivalent a : " + affectation.calculCout());
					break;

				case 2 : 
					menu2(affectation, equipage);
					break;
					
				case 3:
					Sauvegarde.sauvegarde(affectation);
					break;
				case 4 :
					break;

				default :
					System.out.println("Erreur, votre choix ne figure pas dans le menu");
			}
		}while(choix !=4);
		sc.close();
		
	}
	
	public static void menu2(Affectation affectation,Equipage equipage) {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner (System.in);
		int option;
		String o1, o2;

		do {
			System.out.println("***** MENU numero 2 *****");

			System.out.println("1- Echanger objet");
			System.out.println("2- Afficher coût");
			System.out.println("3- Fin");
			option= sc.nextInt();

			switch(option) {
				case 1 : 
					System.out.println(affectation.toString());	
					System.out.println("Echanger les objets : ");
					System.out.println("A quel pirate appartient l'objet que vous voulez changer ? ");
					o1 = sc.next();
					System.out.println("Choisir le deuxieme pirate ");
					o2 = sc.next();
					affectation.echanger(o1,o2);
					System.out.println("Voici la nouvelle liste d'affectation");
					System.out.println(affectation.toString());
					break;

				case 2 : 
					System.out.println("Le cout est �quivalent � : " + affectation.calculCout());
					break;

				case 3 :
					break;

				default :
					System.out.println("Erreur, votre choix ne figure pas dans le menu");
			}

		}while(option !=3);

		System.out.println("Votre liste d'affectation a un cout de " + affectation.calculCout());
		System.out.println(affectation.toString());	

	}
}
