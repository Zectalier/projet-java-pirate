package main;

import java.util.Scanner;

/**
 * Affichage d'un menu pour que l'utilisateur configure son equipage
 * @author Hu Tony, Constantine Benjohnson
 * 
 */
public class Main {
	public static void main (String[]args) {

		Scanner sc = new Scanner (System.in);
		int choix, option;
		int n = 0;
		String e1, e2, pref;
		String listobj = "";
		Graphe graphe = new Graphe();

		System.out.println("Combien de pirate possede votre equipage ?");
		n = sc.nextInt();
		
		while (n>0 && n<27) {
				char c = 'A';
				for (int i=0; i<n; i++) {
					String s = String.valueOf(c);
					graphe.ajoutNoeud(s);
					listobj=listobj+(i+1)+" ";
					c++;
				}

			do {
				System.out.println("***** MENU *****");
				System.out.println("1- Ajouter une relation");
				System.out.println("2- Ajouter des préférences");
				System.out.println("3- Voir les preferences");
				System.out.println("4- Fin");
				choix = sc.nextInt();

				switch(choix) {
					case 1 : System.out.println("Choisir les pirates qui ne s'aiment pas :");
					System.out.println("Nom du premier pirate ? ");
					e1 = sc.next();
					System.out.println("Nom du deuxieme pirate ? ");
					e2 = sc.next();
					graphe.ajoutRelation(e1,e2);
					break;

					case 2 : System.out.println("Nom du pirate suivi de ses préférence :");
					sc.nextLine();
					pref = sc.nextLine();
					graphe.ajoutPreference(pref);
					break;

					case 3 : System.out.println("Voici la liste d'adjacence des pirates ainsi que de leur préférences:");
					System.out.println(graphe.toString());
					break;
				}

				if (choix>4) {
					System.out.println("Erreur, votre choix ne figure pas dans le menu");
				}
			}while(choix !=4);

			Affectation affectation = new Affectation(graphe, listobj);
			System.out.println(affectation.toString());
			String o1, o2;

			do {
				System.out.println("***** MENU numero 2 *****");

				System.out.println("1- Echanger objet");
				System.out.println("2- Afficher coût");
				System.out.println("3- Fin");
				option= sc.nextInt();

				switch(option) {
					case 1 : System.out.println("Echanger les objets : ");
					System.out.println("A quel pirate appartient l'objet que vous voulez changer ? ");
					o1 = sc.next();
					System.out.println("Choisir le deuxieme pirate ");
					o2 = sc.next();
					affectation.echanger(o1,o2);
					System.out.println(affectation.toString());
					break;

					case 2 : System.out.println("Le cout est équivalent à : " +affectation.calculCout(graphe));
					break;
				}
				
				if (option>3) {
					System.out.println("Erreur, votre choix ne figure pas dans le menu");
				}
			}while(option !=3);
			
			System.out.println(affectation.toString());
		}
		sc.close();
	}
}
