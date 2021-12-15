package main;

public class AlgorithmeTTCTest {

	public static void main (String[]args) {
		
		Equipage graphe = new Equipage();
		graphe.ajoutObjet("1");
		graphe.ajoutObjet("2");
		graphe.ajoutObjet("3");
		graphe.ajoutObjet("4");
		graphe.ajoutObjet("5");
		graphe.ajoutObjet("6");
		graphe.ajoutPirate("1");
		graphe.ajoutPirate("2");
		graphe.ajoutPirate("3");
		graphe.ajoutPirate("4");
		graphe.ajoutPirate("5");
		graphe.ajoutPirate("6");
		
		System.out.println(graphe.ajoutPreference("1 3 2 4 1 5 6"));
		System.out.println(graphe.ajoutPreference("2 3 5 6 1 2 4"));
		System.out.println(graphe.ajoutPreference("3 3 1 6 5 4 2"));
		System.out.println(graphe.ajoutPreference("4 2 5 6 4 3 1"));
		System.out.println(graphe.ajoutPreference("5 1 3 2 4 5 6"));
		System.out.println(graphe.ajoutPreference("6 2 4 5 6 1 3"));
		
		graphe.ajoutRelation("1", "2");
		graphe.ajoutRelation("2", "3");
		graphe.ajoutRelation("3", "4");
		graphe.ajoutRelation("4", "5");
		graphe.ajoutRelation("5", "6");
		graphe.ajoutRelation("1", "6");
		graphe.ajoutRelation("2", "5");
		graphe.ajoutRelation("3", "4");
		graphe.ajoutRelation("1", "3");
		graphe.ajoutRelation("1", "4");
		graphe.ajoutRelation("1", "5");
		Affectation affectation = new Affectation(graphe);
		affectation.echanger("1", "3");
		affectation.echanger("4", "2");
		affectation.echanger("4", "5");
		System.out.println("Affectation:\n"+ affectation.toString());
		System.out.println("Cout: "+affectation.calculCout());
		AlgorithmeTTC affectauto = new AlgorithmeTTC(affectation);
		System.out.println("Affectation:\n"+ affectauto.toString());
		System.out.println("Cout: "+affectauto.calculCout(graphe));
	}

}
