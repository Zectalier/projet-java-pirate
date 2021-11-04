import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Map d'affectations des différents objets aux différents pirates
 * @author Hu Tony
 *
 */
public class Affectation {

	private Map<Noeud, String> affectation;

	/**
	 * Construit un objet Affectation avec une HashMap affectation depuis un Graphe graphe et un String listeobjet, 
	 * listeobjet sous la forme "n1 n2 ... nk", 
	 * et associe naivement à chaque clé Noeud de graphe dans la HashMap affectation un objet de la liste
	 * @param graphe, graphe de k pirates
	 * @param listeobjet, liste de k objets/trésors sous forme "n1 n2 ... nk"
	 */
	public Affectation(Graphe graphe, String listeobjet) {
		boolean found;
		int i;
		affectation = new HashMap<Noeud, String>();
		String give;

		String[] liste = listeobjet.split(" ");
		ArrayList<String> al = new ArrayList<String>(Arrays.asList(liste));

		for (Noeud v : graphe.getMap().keySet()) {
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
	}

	/**
	 * Calcul du cout en pirate "jaloux" de l'affectation des objets pour un graphe donné
	 * @param graphe de k pirates
	 * @return int cout de l'affectation
	 */
	public int calculCout(Graphe graphe) {
		int cout = 0;
		int i;
		boolean found;
		String affecte,affecteVoisin;
		int affectePosition, affectePositionVoisin;

		for (Noeud v : graphe.getMap().keySet()) {
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
				for(Noeud u : graphe.getMap().get(v)) {
					affecteVoisin = affectation.get(u);
					affectePositionVoisin = u.getPreference().indexOf(affecteVoisin);

					//On regarde si l'ordre de préference de l'objet du voisin est inférieur à son ordre de préférence pour l'objet et que l'orde de l'objet qu'il a reçu est supérieur à SON ordre de l'objet du voisin
					if(affectePositionVoisin<affectePosition && v.getPreference().indexOf(affecteVoisin)<affectePosition) {
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
	 */
	public void echanger(String s1, String s2) {
		Noeud n1 = new Noeud(s1);
		Noeud n2 = new Noeud(s2);
		String objet1 = affectation.get(n1);
		String objet2 = affectation.get(n2);
		affectation.replace(n1, objet1, objet2);
		affectation.replace(n2, objet2, objet1);
	}

	/**
	 * Retourne un string qui comprends la liste des objets affectés aux différents pirates
	 * @return String
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		for (Noeud v : affectation.keySet()) {
			builder.append(v.getEtiquette() + ": ");
			builder.append(affectation.get(v));
			builder.append("\n");
		}
		return (builder.toString());
	}
}
