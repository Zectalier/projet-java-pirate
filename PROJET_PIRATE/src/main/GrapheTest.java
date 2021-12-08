package main;
/**
 * Classe qui comprends la méthode grapheTest() pour les tests des différents méthodes des classes Equipage, Pirate et Affectation
 * @author Hu Tony
 * @author Constantine Benjohnson
 *
 */
public class GrapheTest {

	/**
	 * Méthode qui permet de faire différents tests pour les méthodes des classes Graphe, Noeud et Affectation
	 */
	public static void grapheTest() {

		Equipage graphe = new Equipage();
		graphe.ajoutPirate("A");
		graphe.ajoutPirate("B");
		graphe.ajoutPirate("C");
		graphe.ajoutPirate("D");
		graphe.ajoutPirate("A");
		graphe.ajoutRelation("A", "B");
		graphe.ajoutRelation("D", "B");
		graphe.ajoutRelation("C", "B");
		graphe.ajoutRelation("E", "B");

		System.out.println(graphe.ajoutPreference("A 1 2 3 4"));
		System.out.println(graphe.ajoutPreference("B 1 3 2 4"));
		System.out.println(graphe.ajoutPreference("C 3 2 1 4"));
		System.out.println(graphe.ajoutPreference("D 1 4 2 3"));
		System.out.println(graphe.ajoutPreference("E 1 2 3 4"));
		System.out.println(graphe.ajoutPreference("A 1 2 3"));

		System.out.println("Graphe:\n"+ graphe.toString());
		Affectation affectation = new Affectation(graphe,"1 2 3 4");
		System.out.println("Affectation:\n"+ affectation.toString());
		System.out.println("Cout: "+affectation.calculCout(graphe));
		affectation.echanger("C","B");
		affectation.echanger("A","B");
		System.out.println("Affectation:\n"+ affectation.toString());
		System.out.println("Cout: "+affectation.calculCout(graphe));
		affectation.echanger("A","B");
		System.out.println("Affectation:\n"+ affectation.toString());
		System.out.println("Cout: "+affectation.calculCout(graphe));
	}

}
