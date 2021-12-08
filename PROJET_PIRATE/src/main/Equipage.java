package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Equipage de pirates comprenant une variable HashMap adjacence qui représente l'équipage en tant que graphe sous forme de liste d'adjacence
 * @author Hu Tony
 * @author Constantine Benjohnson
 *
 */
public class Equipage {

	private Map<Pirate, List<Pirate>> adjacence;

	/**
	 * Construit un Graphe avec une nouvelle HashMap
	 */
	public Equipage() {
		adjacence = new HashMap<Pirate, List<Pirate>>();
	}

	/**
	 * Insère une nouvelle clé Noeud créé avec l'étiquette en paramètre que l'on associe à une nouvelle ArrayList vide dans l'HashMap adjacence si possible.
	 * Sinon, print un message d'erreur
	 * @param s l'étiquette du Noeud à ajouter
	 */
	public void ajoutPirate(String s) {
		Object obj = adjacence.putIfAbsent(new Pirate(s), new ArrayList<Pirate>());
		if(obj != null) {
			System.out.println("Erreur, pirate déjà présent dans la liste");
		}
	}

	/**
	 * Retourne true si l'ajout d'une relation entre le pirate s et le pirate d est réussi, relation définie par insertion des noeuds dans chacune des ArrayList respectifs
	 * @param s noeud source
	 * @param d noeud destination
	 * @return true si ajout réussi, false sinon
	 */
	public boolean ajoutRelation(String s, String d) {
		Pirate n1 = new Pirate("");
		Pirate n2 = new Pirate("");
		for(Pirate v : adjacence.keySet()) {
			if(v.getEtiquette().equals(s)) {
				n1 = v;
			}
			else if(v.getEtiquette().equals(d)) {
				n2 = v;
			}
		}
		if(adjacence.containsKey(n1) && adjacence.containsKey(n2)) {
			adjacence.get(n1).add(n2);
			adjacence.get(n2).add(n1);
			return true;
		}
		return false;
	}

	/**
	 * Prends en entrée un String str qui doit être de la forme "A n1 n2 ... nk" avec a le nom du Pirate et n1 n2 ... nk l'orde de préference de ses objets et modifie pour le pirate A sa liste de preference avec l'orde des préferences de str. Retourne vrai si l'opération est réussi, false sinon
	 * @param str sous forme de "A n1 n2 ... nk", A nom du pirate et n1 n2 ... nk son ordre de préférence
	 * @return true si ajout des preferences réussi, false sinon
	 */
	public boolean ajoutPreference(String str) {
		String[] liste = str.split(" ");
		Pirate n = new Pirate("");
		ArrayList<String> al = new ArrayList<String>(Arrays.asList(liste));
		String etiquette = al.get(0);
		for(Pirate v : adjacence.keySet()) {
			if(v.getEtiquette().equals(etiquette)) {
				n = v;
			}
		}
		if(adjacence.containsKey(n)) {
			if((al.size()-1)==adjacence.size()) {
				al.remove(0);
				n.setPreference(al);
				List<Pirate> values = adjacence.remove(new Pirate(etiquette));
				adjacence.put(n, values);
				return true;
			}
		}
		return false;
	}

	/**
	 * Retourne l'HashMap adjacence de l'objet
	 * @return Map&lt;Noeud, List&lt;Noeud&gt;&gt; adjacence
	 */
	public Map<Pirate, List<Pirate>> getMap(){
		return adjacence;
	}

	/**
	 * Retourne un string qui comprends la liste d'adjacence du graphe ainsi que les préférences de chacun des pirates
	 * @return String
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		for (Pirate v : adjacence.keySet()) {
			builder.append(v.getEtiquette() + ": ");
			for (Pirate w : adjacence.get(v)) {
				builder.append(w.getEtiquette() + " ");
			}
			builder.append("; " + v.getPreference().toString());
			builder.append("\n");
		}
		return (builder.toString());
	}
}
