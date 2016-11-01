package Model;
import java.util.ArrayList;

public class Chemin {
	
	static double rho = 0.5;
	private Ville villeA;
	private Ville villeB;
	private int longueur;
	private double pheromoneActive;
	private double pheromoneEnAttente;
	private ArrayList<Fourmi> fourmis;
	
	public Chemin(Ville villeA, Ville villeB, int longueur) {
		super();
		this.villeA = villeA;
		this.villeB = villeB;
		this.longueur = longueur;
		this.pheromoneActive = 0;
		this.villeA.ajouterChemin(this);
		this.villeB.ajouterChemin(this);	
		this.fourmis = new ArrayList<Fourmi>();
	}

	

	public int getLongueur() {
		return longueur;
	}

	public double getPheromoneActive() {
		return pheromoneActive;
	}
	
	public void ajouterPheromones(double ph){
		this.pheromoneEnAttente+=ph;
	}
	
	public void MAJPheromones(){
		this.pheromoneActive = this.pheromoneActive * Chemin.rho + this.pheromoneEnAttente;
		this.pheromoneEnAttente = 0;
	}
	
	
	
	public Ville getAutreVille(Ville v){
		if( v.equals(villeA)) return this.villeB;
		else if(v.equals(villeB)) return this.villeA;
		else return null;
	}
	
	
	
	public ArrayList<Fourmi> getFourmis() {
		return fourmis;
	}



	@Override
	public String toString() {
		return "Chemin "+this.villeA.getNom()+this.villeB.getNom()+" : "+this.fourmis.size()+" fourmis";
	}

}
