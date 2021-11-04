
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Graphe d'objet Noeud comprenant une variable Map adjacence qui repr�sente le graphe sous forme de liste d'adjacence
 * @author Hu Tony, Constantine Benjohnson
 *
 */
public class Graphe {

	private Map<Noeud, List<Noeud>> adjacence;

	/**
	 * Construit un Graphe avec une nouvelle HashMap<Noeud, List<Noeud>>
	 */
	public Graphe() {
		adjacence = new HashMap<Noeud, List<Noeud>>();
	}

	/**
	 * Ins�re une nouvelle cl� Noeud cr�� avec l'�tiquette en param�tre que l'on associe � une nouvelle ArrayList<Noeud> vide dans l'HashMap adjacence si possible.
	 * Sinon, print un message d'erreur
	 * @param s l'�tiquette du Noeud � ajouter
	 */
	public void ajoutNoeud(String s) {
		Object obj = adjacence.putIfAbsent(new Noeud(s), new ArrayList<Noeud>());
		if(obj != null) {
			System.out.println("Erreur, pirate d�j� pr�sent dans la liste");
		}
	}

	/**
	 * Retourne true si l'ajout d'une relation entre le pirate s et le pirate d est r�ussi, relation d�finie par insertion des noeuds dans chacune des ArrayList<Noeud> respectifs
	 * @param s noeud source
	 * @param d noeud destination
	 * @return true si ajout r�ussi, false sinon
	 */
	public boolean ajoutRelation(String s, String d) {
		Noeud n1 = new Noeud("");
		Noeud n2 = new Noeud("");
		for(Noeud v : adjacence.keySet()) {
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
	 * Prends en entr�e un String str qui doit �tre de la forme "A n1 n2 ... nk" avec a le nom du Pirate et n1 n2 ... nk l'orde de pr�ference de ses objets et modifie pour le pirate A sa liste de preference avec l'orde des pr�ferences de str. Retourne vrai si l'op�ration est r�ussi, false sinon
	 * @param str sous forme de "A n1 n2 ... nk", A nom du pirate et n1 n2 ... nk son ordre de pr�f�rence
	 * @return true si ajout des preferences r�ussi, false sinon
	 */
	public boolean ajoutPreference(String str) {
		String[] liste = str.split(" ");
		Noeud n = new Noeud("");
		ArrayList<String> al = new ArrayList<String>(Arrays.asList(liste));
		String etiquette = al.get(0);
		for(Noeud v : adjacence.keySet()) {
			if(v.getEtiquette().equals(etiquette)) {
				n = v;
			}
		}
		if(adjacence.containsKey(n)) {
			if((al.size()-1)==adjacence.size()) {
				al.remove(0);
				n.setPreference(al);
				List<Noeud> values = adjacence.remove(new Noeud(etiquette));
				adjacence.put(n, values);
				return true;
			}
		}
		return false;
	}

	/**
	 * Retourne l'HashMap adjacence de l'objet
	 * @return Map<Noeud, List<Noeud>> adjacence
	 */
	public Map<Noeud, List<Noeud>> getMap(){
		return adjacence;
	}

	/**
	 * Retourne un string qui comprends la liste d'adjacence du graphe ainsi que les pr�f�rences de chacun des pirates
	 * @return String
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		for (Noeud v : adjacence.keySet()) {
			builder.append(v.getEtiquette() + ": ");
			for (Noeud w : adjacence.get(v)) {
				builder.append(w.getEtiquette() + " ");
			}
			builder.append("; " + v.getPreference().toString());
			builder.append("\n");
		}
		return (builder.toString());
	}
}
