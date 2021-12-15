package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * -NOT ACTUALLY USED-<p>
 * Classe qui essaye de résoudre le problème avec l'algorithme Top Trading Cycle, 
 * cependant ne marche pas pour trouver une solution optimale dans notre problème du à la jalousie possible entre pirates.
 * En effet l'algorithme essaye de trouver une solution le plus équitable possible, mais prends pas en compte d'éventuel problèmes de jalousie 
 * entre plusieurs individus
 * @author Hu Tony
 *
 */
public class AlgorithmeTTC {

	private Map<Pirate, String> affectation;
	
	/**
	 * Constructeur tentant de créer une affectation à l'aide de l'algorithme TTC
	 * @param affectation Affectation
	 */
	public AlgorithmeTTC(Affectation affectation) {
		//Affectation affectation = new Affectation(graphe,listeobjet);
		Map<Pirate, String> topPrefTout = new HashMap<Pirate, String>();
		Map<Pirate,ArrayList<String>> listePirate = new HashMap<Pirate,ArrayList<String>>();
		boolean end = false;
		for(Pirate p : affectation.getAffectation().keySet()) {
			listePirate.put(p, (new ArrayList<String>()));
			listePirate.get(p).add(affectation.getAffectation().get(p));
			listePirate.get(p).add("0");
		}
		for(Pirate p : affectation.getAffectation().keySet()) {
			topPrefTout.put(p, topPreference(p,listePirate,affectation));
			if(topPrefTout.get(p).equals(affectation.getAffectation().get(p)) && listePirate.get(p).get(1).equals("0")) {
				listePirate.get(p).set(1,"1");
			}
		}
		while(!end) {
			end = true;
			for(Pirate p : affectation.getAffectation().keySet()) {
				topPrefTout.put(p, topPreference(p,listePirate,affectation));
			}
			for(Pirate p : listePirate.keySet()) {
				topPrefTout.put(p, topPreference(p,listePirate,affectation));
				if(!listePirate.get(p).get(1).equals("1")) {
					if(topPrefTout.get(p).equals(affectation.getAffectation().get(p)) && listePirate.get(p).get(1).equals("0")) {
						listePirate.get(p).set(1,"1");
					}
					else {
						for(Pirate v : listePirate.keySet()) {
							if(topPrefTout.get(p).equals(listePirate.get(v).get(0)) && listePirate.get(v).get(1).equals("0")) {
								listePirate.get(v).set(0, listePirate.get(p).get(0));
								listePirate.get(p).set(0, topPrefTout.get(p));
								affectation.echanger(p.getEtiquette(), v.getEtiquette());
								listePirate.get(p).set(1,"1");
								break;
							}
						}
					}
					end = false;
				}
			}
		}
		this.affectation = affectation.getAffectation();
	}
	
	/**
	 * Méthode permettant d'obtenir le "top" préférence toujours disponible
	 * @param pirate Pirate
	 * @param listePirate liste
	 * @param affectation Affectation
	 * @return String
	 */
	private String topPreference(Pirate pirate, Map<Pirate,ArrayList<String>>listePirate, Affectation affectation) {
		int i = 0;
		boolean found;
		String topPref = "";
		while(i != -1) {
			found = false;
			topPref = pirate.getPreference().get(i);
			if(topPref.equals(listePirate.get(pirate).get(0))) {
				return topPref;
			}
			else{
				while(!found) {
					for(Pirate p : listePirate.keySet()) {
						if(!listePirate.get(p).get(1).equals("1") && !p.equals(pirate) && listePirate.get(p).get(0).equals(topPref)) {
							found = true;
							break;
						}
					}
					if(found == true) {
						i=-1;
					}
					else{
						found = true;
						i++;
					}
				}
			}
		}
		return topPref;
	}
	
	/**
	 * Algorithme de calcul du cout (même que dans Affectation)
	 * @param graphe Equipage
	 * @return int
	 */
	public int calculCout(Equipage graphe) {
		int cout = 0;
		int i;
		boolean found;
		String affecte,affecteVoisin;
		int affectePosition, affectePositionVoisin;

		for (Pirate v : graphe.getMap().keySet()) {
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
				for(Pirate u : graphe.getMap().get(v)) {
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
