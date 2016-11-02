package Model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fourmi {
	
	private static final double alpha = 2;
	private static final double beta = 0;
	private static final double Q = 1;
	private ArrayList<Ville> villesVisitees;
	private ArrayList<Chemin> CheminsParcourus;
	private Ville villeCourante; //Ville ou dernière ville courante
	private Chemin cheminCourant;
	private int cheminRestant;
	private Simulation simulation;
	private boolean choixChemin; // vrai: le chemin est choisi selon le nombre de phéromone, faux : le chemin est choisi selon des  probabilité en tenant compte de la longueur des chemins et du nombre de phéromones
	
	public Fourmi(Simulation simulation, Ville nid, boolean choixChemin){		
		this.villesVisitees = new ArrayList<Ville>();
		this.CheminsParcourus = new ArrayList<Chemin>();
		
		this.villeCourante = nid;
		this.cheminCourant = null;
		this.cheminRestant = 0;
		
		this.simulation = simulation;
		
		this.choixChemin = choixChemin;
	}
	
	
	//revoir renvoie
	public void avancer(){		
		if(this.cheminCourant == null){
			Chemin newChemin;
			if(this.choixChemin) newChemin = this.choisirChemin();
			else newChemin = this.choisirCheminAvecLongueur();
			this.cheminCourant = newChemin;
			this.cheminRestant = newChemin.getLongueur();
			this.cheminCourant.getFourmis().add(this);
			this.villesVisitees.add(this.villeCourante);						
		} else {
			this.cheminRestant--;			
			if(this.cheminRestant<=0){
				this.villeCourante = this.cheminCourant.getAutreVille(this.villeCourante);
				this.CheminsParcourus.add(this.cheminCourant);
				this.cheminCourant.getFourmis().remove(this);
				this.cheminCourant = null;
			}			
		}
	}
	
	private Chemin choisirCheminAvecLongueur(){
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
	
	
	
	private Chemin choisirChemin(){
		ArrayList<Chemin> cheminsCandidats = new ArrayList<Chemin>();
		ArrayList<Chemin> res = new ArrayList<Chemin>();
		double pheromones;
		
		if(this.villesVisitees.size()+1 != this.simulation.getVilles().size()){
			for(Chemin c : this.villeCourante.getChemins()){
				if(!this.villesVisitees.contains(c.getAutreVille(this.villeCourante))){
					cheminsCandidats.add(c);
				} 				
			}
		} else {
			for(Chemin c : this.villeCourante.getChemins()){
				if(c.getAutreVille(this.villeCourante).equals(this.simulation.nid)){
					cheminsCandidats.add(c);
				}
			}
		}
		
		pheromones = cheminsCandidats.get(0).getPheromoneActive();
		
		for(Chemin c : cheminsCandidats){
			if(pheromones < c.getPheromoneActive()){
				res.clear();
				pheromones = c.getPheromoneActive();
				res.add(c);
			} else if(pheromones == c.getPheromoneActive()) {
				res.add(c);
			}
		}
		
		
		if(res.size()>1){			
			return res.get((int) (Math.random()*res.size()));
		}
		return res.get(0);							
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
