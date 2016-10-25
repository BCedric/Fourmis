import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Ville {

	private String nom;
	private ArrayList<Chemin> chemins;
	
	public Ville(String nom) {
		super();
		this.chemins = new ArrayList<Chemin>();
		this.nom = nom;
	}
	
	public ArrayList<Chemin> getChemins() {
		return chemins;
	}

	public String getNom(){
		return this.nom;
	}
	
	public void ajouterChemin(Chemin c){
		this.chemins.add(c);
	}
	
	
}
