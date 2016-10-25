import java.util.ArrayList;

public class Chemin {
	
	private static final double rho = 0.5;
	private Ville villeA;
	private Ville villeB;
	private int longueur;
	private double pheromoneActive;
	private double pheromoneEnAttente;
	
	public Chemin(Ville villeA, Ville villeB, int longueur) {
		super();
		this.villeA = villeA;
		this.villeB = villeB;
		this.longueur = longueur;
		this.pheromoneActive = 0;
		this.villeA.ajouterChemin(this);
		this.villeB.ajouterChemin(this);
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
	
	

}
