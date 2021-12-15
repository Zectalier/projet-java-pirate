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

		Scanner sc = new Scanner (System.in);
		int choix, option;
		int n = 0;
		String e1, e2, pref;
		String listobj = "";
		Equipage graphe = new Equipage();
		Affectation affectation;

		while (!(n>0 && n<27)) {
			System.out.println("Combien de pirate possede votre equipage ?");
			n = sc.nextInt();
		}

		char c = 'A';
		for (int i=0; i<n; i++) {
			String s = String.valueOf(c);
			graphe.ajoutPirate(s);
			listobj=listobj+(i+1)+" ";
			graphe.ajoutObjet(String.valueOf(i+1));
			c++;
		}

		do {
			System.out.println("***** MENU *****");
			System.out.println("1- Ajouter une relation");
			System.out.println("2- Ajouter des préférences");
			System.out.println("3- Voir les preferences des pirates");
			System.out.println("4- Fin");
			choix = sc.nextInt();

			switch(choix) {
			case 1 : 
				System.out.println("Choisir les pirates qui ne s'aiment pas :");
				System.out.println("Nom du premier pirate ? ");
				e1 = sc.next();
				System.out.println("Nom du deuxieme pirate ? ");
				e2 = sc.next();
				if(!graphe.ajoutRelation(e1,e2)) {
					System.out.println("Erreur dans l'ajout de relation");
				}
				break;

			case 2 : 
				System.out.println("Voici la liste des objets disponibles : " + listobj);
				System.out.println("Nom du pirate suivi de ses préférence :");	
				sc.nextLine();
				pref = sc.nextLine();
				if(graphe.ajoutPreference(pref) == 1) {
					System.out.println("Erreur, nom du pirate ne fait pas partie de ceux dans l'Equipage");
				}
				else if(graphe.ajoutPreference(pref) == 2) {
					System.out.println("Erreur, objet qui ne fait pas partie de la liste d'objet disponible");
				}
				else if(graphe.ajoutPreference(pref) == -1) {
					System.out.println("Erreur Critique!");
				}
				
				break;

			case 3 : 
				System.out.println("Voici la liste d'adjacence des pirates ainsi que de leur préférences:");
				System.out.println(graphe.toString());
				break;

			case 4 :
				for (Pirate v : graphe.getMap().keySet()) {
					if(v.getPreference().isEmpty()) {
						choix = 0;
					}
				}
				if(choix == 0) {
					System.out.println("Erreur, un ou plusieurs pirates n'ont pas de préférences!");
				}
				break;

			case 42 :
				System.out.println("Bravo, vous avez trouver le numéro secret pour les tests des méthodes des différentes classes!");
				GrapheTest.grapheTest();

			default :
				System.out.println("Erreur, votre choix ne figure pas dans le menu");
			}

		}while(choix !=4);

		affectation = new Affectation(graphe, listobj);
		System.out.println("Voici la liste d'affectation des objets pour les pirates");
		System.out.println(affectation.toString());
		String o1, o2;

		do {
			System.out.println("***** MENU numero 2 *****");

			System.out.println("1- Echanger objet");
			System.out.println("2- Afficher coût");
			System.out.println("3- Fin");
			option= sc.nextInt();

			switch(option) {
			case 1 : 
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
				System.out.println("Le cout est �quivalent � : " + affectation.calculCout(graphe));
				break;

			case 3 :
				break;

			default :
				System.out.println("Erreur, votre choix ne figure pas dans le menu");
			}

		}while(option !=3);

		System.out.println("Votre liste d'affectation a un cout de " + affectation.calculCout(graphe));
		System.out.println(affectation.toString());

		sc.close();
	}
}
