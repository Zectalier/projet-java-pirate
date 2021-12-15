package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AffectationAutomatique extends Affectation{
	
	public AffectationAutomatique(Equipage equipage, int k) {
		super(equipage);
		List<Pirate> keysAsArray = new ArrayList<Pirate>(affectation.keySet());
		ArrayList<Pirate> voisinP;
		Random r;
		for(int i = 0; i < k; i++) {
			r = new Random();
			Pirate p = keysAsArray.get(r.nextInt(keysAsArray.size()));
			voisinP = equipage.getMap().get(p);
			Pirate v = voisinP.get(r.nextInt(voisinP.size()));
			Affectation a = new Affectation(equipage);
			a.echanger(p.getEtiquette(), v.getEtiquette());
			if(this.calculCout() > a.calculCout()) {
				affectation = a.getAffectation();
			}
		}
	}
	
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
