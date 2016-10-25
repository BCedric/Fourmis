import java.util.ArrayList;

public class Chemin {
	
	private Ville villeA;
	private Ville villeB;
	private int longueur;
	private ArrayList<Float> pheromone;
	
	public Chemin(Ville villeA, Ville villeB, int longueur) {
		super();
		this.villeA = villeA;
		this.villeB = villeB;
		this.longueur = longueur;
		this.pheromone = new ArrayList<Float>();
		this.villeA.ajouterChemin(this);
		this.villeB.ajouterChemin(this);
	}

	public int getLongueur() {
		return longueur;
	}

	public ArrayList<Float> getPheromone() {
		return pheromone;
	}
	
	private void miseAJourPheromones(){
		float f;
		ArrayList<Float> newPhero = new ArrayList<Float>();
		for(int i =0; i< this.pheromone.size();++i){
			f = (float) (this.pheromone.get(i) - 0.1);
			if( f > 0) newPhero.add((float) (f-0.1));
		}
		this.pheromone = newPhero;
	}
	
	public float getSommePheromone(){
		//this.miseAJourPheromones();
		float sum = 0;
		for(float f : this.pheromone){
			sum+=f;
		}
		return sum;
	}
	
	public Ville getAutreVille(Ville v){
		if( v.equals(villeA)) return this.villeB;
		else if(v.equals(villeB)) return this.villeA;
		else return null;
	}
	
	

}
