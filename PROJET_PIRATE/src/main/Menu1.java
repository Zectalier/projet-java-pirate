import java.util.Scanner;

public class Menu1 {

	public static void main (String[]args) {

		Scanner sc = new Scanner (System.in);
		int choix, option;
		int n;
		String e1, e2, pref;
		String listobj = "";
		Graphe graphe = new Graphe();

		System.out.println("Combien de pirate possede votre equipage ?");
		n = sc.nextInt();
		// check if n is not a string
		while (n<27) {
			if (!(n>26)) {
				char c = 'A';
				for (int i=0; i<n; i++) {
					String s = String.valueOf(c);
					graphe.ajoutNoeud(s);
					listobj=listobj+(i+1)+" ";
					c++;
				}
			}

			//System.out.println(listobj); --> 
			//ajouter une excepetion
			do {
				// check if the user do not write any string 
				System.out.println("***** MENU *****");
				System.out.println("1- Ajouter une relation");
				System.out.println("2- Ajouter des preferences");
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

					case 2 : System.out.println("Nom du pirate suivi de ses preference :");
					sc.nextLine();
					pref = sc.nextLine();
					graphe.ajoutPreference(pref);
					break;

					case 3 : System.out.println("Voici les pr�ferences:");
					System.out.println(graphe.toString());
					break;
				}

				if (choix>4) {
					System.out.println("erreur, votre choix ne figure pas dans le menu");
				}
			}while(choix !=4);
			
			// second part of the menu

			Affectation affectation = new Affectation(graphe, listobj);
			System.out.println(affectation.toString());
			String o1, o2;

			do {
				System.out.println("***** MENU numero 2 *****");

				System.out.println("1- echanger objet");
				System.out.println("2- afficher co�t");
				System.out.println("3- fin");
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

					case 2 : System.out.println("Le cout est equivalent � : " +affectation.calculCout(graphe));
					break;
				}
				
				if (option>3) {
					System.out.println("erreur, votre choix ne figure pas dans le menu");
				}
			}while(option !=3);
			
			System.out.println(affectation.toString());
		}
		sc.close();
	}
}
