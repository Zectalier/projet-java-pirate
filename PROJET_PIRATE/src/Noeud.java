import java.util.ArrayList;
import java.util.List;

/**
 * Noeud représentant un pirate à qui on associe une etiquette et une liste de preference
 * @author Hu Tony, Constantine Benjohnson
 *
 */
public class Noeud {

	private String etiquette;
	private List<String> preference;

	/**
	 * Construit un Noeud avec une ArrayList<String> vide et l'etiquette donné en paramètre
	 * @param etiquette
	 */
	public Noeud(String etiquette) {
		this.etiquette = etiquette;
		preference = new ArrayList<String>();
	}

	/**
	 * Retourne l'étiquette du noeud
	 * @return Etiquette du noeud
	 */
	public String getEtiquette() {
		return etiquette;
	}

	/**
	 * Retourne la liste des préférences du noeud
	 * @return List<String> preference
	 */
	public List<String> getPreference(){
		return preference;
	}

	/**
	 * Modifie la liste des préférences du noeud par l'ArrayList en entré
	 * @param newPreference un ArrayList<String> 
	 */
	public void setPreference(ArrayList<String> al) {
		preference = al;
	}

	/**
	 * Retourne le hashcode du noeud, hashcode définie seulement par la variable etiquette du noeud
	 * @return int hashCode
	 */
	@Override
	public int hashCode() {
		return etiquette.hashCode();
	}

	/**
	 * Retourne vrai si l'objet en entrée est de même classe et a le même hashcode que le noeud
	 * @param o qui est l'objet à comparer
	 * @return boolean
	 */
	@Override
	public boolean equals(Object o) {
		if(this.getClass()==o.getClass()) {
			if(this.hashCode()==o.hashCode())
				return true;
		}
		return false;
	}
}
