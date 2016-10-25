import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fourmi {
	
	private static final double alpha = 2;
	private static final double beta = 1;
	private static final double Q = 1;
	private ArrayList<Ville> villesVisitees;
	private ArrayList<Chemin> CheminsParcourus;
	private Ville villeCourante; //Ville ou dernière ville courante
	private Chemin cheminCourant;
	private int cheminRestant;
	private Simulation simulation;
	
	public Fourmi(Simulation simulation, Ville nid){		
		this.villesVisitees = new ArrayList<Ville>();
		this.CheminsParcourus = new ArrayList<Chemin>();
		
		this.villeCourante = nid;
		this.cheminCourant = null;
		this.cheminRestant = 0;
		
		this.simulation = simulation;
	}
	
	
	//revoir renvoie
	public void avancer(){		
		if(this.cheminCourant == null){			
			Chemin newChemin = this.choisirChemin();
			this.cheminCourant = newChemin;
			this.cheminRestant = newChemin.getLongueur();
			this.villesVisitees.add(this.villeCourante);						
		} else {
			this.cheminRestant--;			
			if(this.cheminRestant<=0){
				this.villeCourante = this.cheminCourant.getAutreVille(this.villeCourante);
				this.CheminsParcourus.add(this.cheminCourant);
				this.cheminCourant = null;
			}			
		}
	}
	
	

	
	
	private Chemin choisirChemin(){
		HashMap<Chemin, Double> villesCandidates = this.calculProba();
		if(villesCandidates.size() == 1){
			for(Map.Entry<Chemin, Double> entry : villesCandidates.entrySet()){
				return entry.getKey();
			}
		}
		
		double sum = 0;
		for(Map.Entry<Chemin, Double> entry : villesCandidates.entrySet()){
			sum+=entry.getValue();
		}
		if(sum == 0){
			System.out.println("coucou");
		}
		double nbAleatoire = Math.random()*sum;
		sum = 0;
		int l;
		for(Map.Entry<Chemin, Double> entry : villesCandidates.entrySet()){
			if(nbAleatoire>= sum && nbAleatoire<= sum + entry.getValue()) {
				l = entry.getKey().getLongueur();
				return entry.getKey();				
			}
			sum+=entry.getValue();
		}
		return null;
	}
	
	
	private HashMap<Chemin, Double> calculProba(){
		HashMap<Chemin, Double> res = new HashMap<Chemin, Double>();
		double tau, nu;
		double somme = this.calculSomme();
		if(somme != 0){
			if(this.villesVisitees.size()+1 != this.simulation.getVilles().size()){
				for(Chemin c : this.villeCourante.getChemins()){
					if(!this.villesVisitees.contains(c.getAutreVille(this.villeCourante))){
						tau = Math.pow(c.getPheromoneActive(), this.alpha);
						nu = Math.pow(c.getLongueur(), -this.beta);
						res.put(c,(tau*nu));
					} 
					else {
						res.put(c, (double) 0);
					}
				}
			} else {
				for(Chemin c : this.villeCourante.getChemins()){
					if(this.simulation.getNid().equals(c.getAutreVille(this.villeCourante))){
						tau = Math.pow(c.getPheromoneActive(), this.alpha);
						nu = Math.pow(c.getLongueur(), -this.beta);
						res.put(c,(tau*nu));
					} 
					else {
						res.put(c, (double) 0);
					}
				}
			}
			
			
		} else {
			for(Chemin c : this.villeCourante.getChemins()){
				res.put(c, (1.00/this.villeCourante.getChemins().size()));
			}
		}
		return res;
		
		
	}
	
	
	
	private double calculSomme(){
		if(this.villeCourante != null){
			double sum = 0;
			double tau, nu;
			for(Chemin c : this.villeCourante.getChemins()){
				tau = Math.pow(c.getPheromoneActive(), this.alpha);
				nu = Math.pow(c.getLongueur(), -this.beta);
				sum+=tau*nu;
			}
			return sum;
		}
		return 0;
		
	}
	
	public void déposerPheromones(){
		int longueurTotale = 0;
		for(Chemin c : this.CheminsParcourus){
			longueurTotale += c.getLongueur();
		}
		
		for(Chemin c : this.CheminsParcourus){
			c.ajouterPheromones(Fourmi.Q/longueurTotale);
		}
		
	}
	
	public Ville getVilleCourante() {
		return villeCourante;
	}

	public Chemin getCheminCourant() {
		return cheminCourant;
	}

}
