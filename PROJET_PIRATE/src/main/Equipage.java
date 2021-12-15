package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Equipage de pirates comprenant une variable HashMap adjacence qui repr�sente l'�quipage en tant que graphe sous forme de liste d'adjacence ainsi qu'une variable objet qui repr�sente la liste des objets/tr�sors disponible
 * @author Hu Tony
 * @author Constantine Benjohnson
 *
 */
public class Equipage {

	private Map<Pirate, ArrayList<Pirate>> adjacence;
	private ArrayList<String> objets;

	/**
	 * Construit un Graphe avec une nouvelle HashMap
	 */
	public Equipage() {
		adjacence = new HashMap<Pirate, ArrayList<Pirate>>();
		objets = new ArrayList<String>();
	}

	/**
	 * Tente d'ins�rer une nouvelle cl� Pirate cr��e avec l'�tiquette en param�tre que l'on associe � une nouvelle ArrayList vide dans l'HashMap adjacence si il n'est pas deja dans notre equipage.
	 * @param s l'�tiquette du Noeud � ajouter
	 * @return true si le pirate a �t� ajout�, false sinon
	 */
	public boolean ajoutPirate(String s) {
		Object obj = adjacence.putIfAbsent(new Pirate(s), new ArrayList<Pirate>());
		if(obj != null) {
			return false;
		}
		return true;
	}

	/**
	 * Retourne true si l'ajout d'une relation entre le pirate s et le pirate d est r�ussi, relation d�finie par insertion des noeuds dans chacune des ArrayList respectifs
	 * @param s noeud source
	 * @param d noeud destination
	 * @return true si ajout r�ussi, false sinon
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
	 * Prends en entr�e un String str qui doit �tre de la forme "A n1 n2 ... nk" avec a le nom du Pirate et n1 n2 ... nk l'orde de pr�ference de ses objets et modifie pour le pirate A sa liste de preference avec l'orde des pr�ferences de str. str doit exister dans la liste objets pour que l'operation soit r�ussit. Retourne vrai si l'op�ration est r�ussi, false sinon
	 * @param str sous forme de "A n1 n2 ... nk", A nom du pirate et n1 n2 ... nk son ordre de pr�f�rence
	 * @return 0 si ajout des preferences r�ussi, 1 si le pirate n'est pas dans l'equipage, 2 si l'objet n'est pas dans la liste
	 */
	public int ajoutPreference(String str) {
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
				for(String obj : al) {
					if(!objets.contains(obj)) {
						return 2;
					}
				}
				n.setPreference(al);
				ArrayList<Pirate> values = adjacence.remove(new Pirate(etiquette));
				adjacence.put(n, values);
				return 0;
			}
		}
		else {
			return 1;
		}
		return -1;
	}

	/**
	 * Retourne l'HashMap adjacence de l'objet
	 * @return Map&lt;Noeud, ArrayList&lt;Noeud&gt;&gt; adjacence
	 */
	public Map<Pirate, ArrayList<Pirate>> getMap(){
		return adjacence;
	}

	/**
	 * Ajoute un nouvel objet dans la liste d'objets si il n'y est pas et retourne true si l'operation est r�ussi. Retourne false sinon
	 * @param str
	 * @return true si ajout objet r�ussi, false sinon
	 */
	public boolean ajoutObjet(String str) {
		if(!objets.contains(str)) {
			objets.add(str);
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Retourne le nombre d'objets/la taille de la liste objets
	 * @return int
	 */
	public int getObjetCount() {
		return objets.size();
	}

	/**
	 * Retourne la liste des objets
	 * @return ArrayList&ltString&gt
	 */
	public ArrayList<String> getObjets(){
		return objets;
	}
	
	/**
	 * Retourne un string qui comprends la liste d'adjacence du graphe ainsi que les pr�f�rences de chacun des pirates
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
