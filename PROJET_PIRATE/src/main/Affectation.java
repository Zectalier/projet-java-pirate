package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Map d'affectations des différents objets aux différents pirates
 * @author Hu Tony
 * @author Constantine Benjohnson
 *
 */
public class Affectation {

	protected Map<Pirate, String> affectation;
	protected Equipage equipage;

	/**
	 * Construit un objet Affectation avec une HashMap affectation depuis un Equipage graphe et un String listeobjet, 
	 * listeobjet sous la forme "n1 n2 ... nk", 
	 * et associe naivement à chaque clé Noeud de graphe dans la HashMap affectation un objet de la liste
	 * @param graphe, equipage de k pirates
	 * @param listeobjet, liste de k objets/trésors sous forme "n1 n2 ... nk"
	 */
	public Affectation(Equipage graphe) {
		boolean found;
		int i;
		affectation = new HashMap<Pirate, String>();
		String give;

		@SuppressWarnings("unchecked")
		ArrayList<String> al = (ArrayList<String>)graphe.getObjets().clone();

		for (Pirate v : graphe.getMap().keySet()) {
			found = false;
			i = 0;
			give = "Error";
			List<String> preference = v.getPreference();
			while(!found && i<preference.size()) {
				if(al.contains(preference.get(i))) {
					give = preference.get(i);
					al.remove(preference.get(i));
					found = true;
				}
				i++;
			}
			affectation.put(v, give);
		}
		equipage = graphe;
	}

	/**
	 * Calcul du cout en pirate "jaloux" de l'affectation des objets pour un graphe donné
	 * @param graphe de k pirates
	 * @return int cout de l'affectation
	 */
	public int calculCout() {
		int cout = 0;
		int i;
		boolean found;
		String affecte,affecteVoisin;
		int affectePosition, affectePositionVoisin;

		for (Pirate v : equipage.getMap().keySet()) {
			found = false;
			i = 0;
			affecte = affectation.get(v);
			affectePosition = v.getPreference().indexOf(affecte);

			//Cas si trésor affecté est le favori du pirate
			if(i == 0) {
				if(affecte.equals(v.getPreference().get(0))) {
					found = true;
				}
			}

			while(!found) {

				//On check pour chacun des voisins du pirate
				for(Pirate u : equipage.getMap().get(v)) {
					affecteVoisin = affectation.get(u);
					affectePositionVoisin = u.getPreference().indexOf(affecteVoisin);

					//On regarde si l'ordre de préference de l'objet du voisin est inférieur ou égal à son ordre de préférence pour l'objet et que l'ordre de l'objet qu'il a reçu est supérieur à SON ordre de l'objet du voisin
					if(affectePositionVoisin<=affectePosition && v.getPreference().indexOf(affecteVoisin)<affectePosition) {
						cout++;
						break;
					}
				}
				found = true;
			}
		}

		return cout;
	}

	/**
	 * Echange les objets affecté aux pirates s1 et s2 dans la Map Affectation
	 * @param s1 le premier pirate
	 * @param s2 le deuxième pirate
	 * @return int, 1 si premier pirate pas dans la liste de pirates, 2 si le deuxieme n'est pas dans la liste, 0 si ajout réussi
	 */
	public int echanger(String s1, String s2) {
		Pirate n1 = new Pirate(s1);
		String objet1;
		Pirate n2 = new Pirate(s2);
		String objet2;
		if(affectation.containsKey(n1)) {
			objet1 = affectation.get(n1);
		}else { return 1; }
		if(affectation.containsKey(n2)) {
			objet2 = affectation.get(n2);
		}else { return 2; }
		affectation.replace(n1, objet1, objet2);
		affectation.replace(n2, objet2, objet1);
		return 0;
	}

	public Map<Pirate, String> getAffectation(){
		return affectation;
	}
	/**
	 * Retourne un string qui comprends la liste des objets affectés aux différents pirates
	 * @return String
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		for (Pirate v : affectation.keySet()) {
			builder.append(v.getEtiquette() + ": ");
			builder.append(affectation.get(v));
			builder.append("\n");
		}
		return (builder.toString());
	}
}
