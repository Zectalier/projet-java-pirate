package main;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class permettant de gérer les différents menu intervenant dans la méthode main
 * @author Constantine Benjohnson
 * @author Hu Tony
 *
 */
public class Menu {

	/**
	 * Méthode demandant à l'utilisateur de saisir le nom d'un fichier afin de récuperer 
	 * un équipage à l'aide de la méthode présente dans ParserEquipage.java<p>
	 * La méthode cherche le fichier à l'emplacement "save/'nomfichier'"
	 * @param sc Scanner
	 * @return Equipage
	 */
	public static Equipage loadFile(Scanner sc) {
		System.out.println("Veuillez saisir le nom du fichier à charger");
		String nomFichier = "";
		File file;
		Equipage equipage = new Equipage();
		while(nomFichier.equals("")) {
			nomFichier = sc.nextLine();
			file = new File("save/"+nomFichier);
			if(!file.exists()) {
				nomFichier = "";
				System.err.println("Nom de fichier invalide! Veuillez réessayer");
			}
			else {
				equipage = ParserEquipage.parseFile(nomFichier);
				if(equipage == null) {
					nomFichier = "";
				}
			}
		}
		return equipage;
	}
	
	/**
	 * Méthode permettant d'afficher le premier menu et de traiter les entrées utilisateurs.
	 * @param equipage un Equipage
	 * @param sc Scanner
	 */
	public static void menuCommand(Equipage equipage, Scanner sc) {
		
		Affectation affectation = new Affectation(equipage);
		int choix = 0;
		int k=0;
		
		try {
			System.out.println("Bienvenue Capitaine!");
			Thread.sleep(1000);
			System.out.println("\nVoici votre équipage, chacun des pirates vous ont fourni leur liste de préférence mais il semblerait qu'il y ai des tensions entre vos hommes... ");
			Thread.sleep(2000);
			System.out.println(equipage.toString());
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		do {
			System.out.println("***** MENU *****");

			System.out.println("1- Résolution automatique");
			System.out.println("2- Résolution manuelle");
			System.out.println("3- Sauvegarder");
			System.out.println("4- Fin");
			try {
				choix=sc.nextInt();
			}catch(InputMismatchException E){
				System.err.println("Erreur, veuillez écrire un entier");
				sc.nextLine();
			}
				switch(choix) {
					case 1 :
						while(k <= 0) {
							try {
								System.out.println("Veuillez choisir un k > 0 pour détermnier combien de fois l'algorithme va tenter de choisir des pirates à échanger: ");
								k=sc.nextInt();
							}catch(InputMismatchException E){
								System.err.println("Erreur, veuillez écrire un entier");
								sc.nextLine();
							}
						}
						affectation = new AffectationAutomatique(equipage,k);
						System.out.println("Voici la solution proposé par l'algorithme : ");
						try {
							System.out.println(affectation.toString());
							Thread.sleep(3000);
							System.out.println("Le cout est équivalent à : " + affectation.calculCout()+"\n");
							Thread.sleep(1000);
						}catch (InterruptedException e) {
							e.printStackTrace();
						}
						k = 0;
						break;
						
					case 2 :
						menu2(affectation, equipage, sc);
						break;
						
					case 3:
						Sauvegarde.sauvegarde(affectation, sc);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						break;
						
					case 4 :
						System.out.println("Mon Capitaine, votre liste d'affectation final a un cout de " + affectation.calculCout());
						try {
							Thread.sleep(1000);
							System.out.println(affectation.toString());	
							Thread.sleep(2000);
							System.out.println("Que des pirates soient jaloux ou pas, le butin n'attends personne!");
							Thread.sleep(1000);
							System.out.println("Haussez les voiles moussaillons!");
							Thread.sleep(1000);
							System.out.println("Nous sommes partis!");
						}catch (InterruptedException e) {
							e.printStackTrace();
						}
						break;
	
					default :
						System.out.println("Erreur, votre choix ne figure pas dans le menu");
				}
		}while(choix !=4);
		
		
	}
	
	/**
	 * Méthode du menu traitant de la résolution manuelle ( provenenant de la partie 1 du sujet )
	 * @param affectation une Affectation
	 * @param equipage un Equipage
	 * @param sc Scanner
	 */
	public static void menu2(Affectation affectation,Equipage equipage,Scanner sc) {
		
		int option = -1;
		String o1, o2;
		System.out.println("Voici la liste d'affectation actuelle : ");
		System.out.println(affectation.toString());	
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		do {
			
			System.out.println("***** MENU numero 2 *****");
			System.out.println("1- Echanger objet");
			System.out.println("2- Afficher coÃ»t");
			System.out.println("3- Fin");
			try {
				option=sc.nextInt();
			}catch(InputMismatchException E){
				System.err.println("Erreur, veuillez écrire un int");
				sc.nextLine();
			}
			
			switch(option) {
				case 1 :
					System.out.println("Echanger les objets : ");
					System.out.println("A quel pirate appartient l'objet que vous voulez changer ? ");
					o1 = sc.next();
					System.out.println("Choisir le deuxieme pirate ");
					o2 = sc.next();
					switch(affectation.echanger(o1,o2)) {
						case 1:
							System.out.println("Erreur, premier pirate qui ne figure pas dans l'équipage");
							break;
						case 2:
							System.out.println("Erreur, deuxieme pirate qui ne figure pas dans l'équipage");
							break;
						case 0:
							System.out.println("Voici la nouvelle liste d'affectation");
							System.out.println(affectation.toString());
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							break;
					}
					break;
					
				case 2 : 
					System.out.println("Le cout est équivalent à : " + affectation.calculCout());
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
					break;
					
				case 3 :
					break;
					
				default :
					System.out.println("Erreur, votre choix ne figure pas dans le menu");
			}

		}while(option != 3);

		System.out.println("Votre liste d'affectation a un cout de " + affectation.calculCout());
		try {
			Thread.sleep(1000);
			System.out.println(affectation.toString());	
			Thread.sleep(2000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
