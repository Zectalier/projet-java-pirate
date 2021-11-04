package main;
/**
 * Tests des différents méthodes des classes Graphe, Noeud et Affectation
 * @author Hu Tony, Constantine Benjohnson
 *
 */
public class GrapheTest {

	public static void main(String[] args) {

		Graphe graphe = new Graphe();
		graphe.ajoutNoeud("A");
		graphe.ajoutNoeud("B");
		graphe.ajoutNoeud("C");
		graphe.ajoutNoeud("D");
		graphe.ajoutNoeud("A");
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
