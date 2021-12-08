package main;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Pirate repr�sentant un pirate auquel on associe une etiquette et une liste de preference
 * @author Hu Tony
 * @author Constantine Benjohnson
 *
 */
public class Pirate {

	private String etiquette;
	private List<String> preference;

	/**
	 * Construit un Noeud avec une ArrayList&lt;String&gt; vide et l'etiquette donn� en param�tre
	 * @param etiquette etiquette du nouveau noeud
	 */
	public Pirate(String etiquette) {
		this.etiquette = etiquette;
		preference = new ArrayList<String>();
	}

	/**
	 * Retourne l'�tiquette du noeud
	 * @return Etiquette du noeud
	 */
	public String getEtiquette() {
		return etiquette;
	}

	/**
	 * Retourne la liste des pr�f�rences du noeud
	 * @return List&lt;String&gt; preference
	 */
	public List<String> getPreference(){
		return preference;
	}

	/**
	 * Modifie la liste des pr�f�rences du noeud par l'ArrayList en entr�
	 * @param al un ArrayList&lt;String&gt; 
	 */
	public void setPreference(ArrayList<String> al) {
		preference = al;
	}

	/**
	 * Retourne le hashcode du noeud, hashcode d�finie seulement par la variable etiquette du noeud
	 * @return int hashCode
	 */
	@Override
	public int hashCode() {
		return etiquette.hashCode();
	}

	/**
	 * Retourne vrai si l'objet en entr�e est de m�me classe et a le m�me hashcode que le noeud
	 * @param o qui est l'objet � comparer
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
