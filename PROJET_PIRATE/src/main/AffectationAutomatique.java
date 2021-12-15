package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe h�ritant de la classe Affectation. Permet de faire l'affectation automatique des tr�sors � l'aide de l'algorithme propos� dans le sujet
 * @author Hu Tony
 * @author Constantine Benjohnson
 */
public class AffectationAutomatique extends Affectation{
	
	/**
	 * Constructeur. Construit une AffectationAutomatique � l'aide d'un equipage.<p>
	 * Il est demand� de fournir un entier k afin de d�termnier combien de fois l'algorithme va tenter de choisir des pirates � �changer.
	 * @param equipage Equipage
	 * @param k int
	 */
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
