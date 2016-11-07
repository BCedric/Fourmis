package Model;
import java.util.ArrayList;

public class Simulation {
	
	Ville nid;
	
	ArrayList<Ville> villes;
	ArrayList<Fourmi> fourmis;
	ArrayList<Chemin> chemins;
	
	
	
	
	public Simulation(double evaporation) {
		super();		
		this.fourmis = new ArrayList<Fourmi>();
		this.chemins = new ArrayList<Chemin>();
		Chemin.rho = evaporation;
	}
	public void faireAvancer(){
		ArrayList<Fourmi> fourmisASupprimer = new ArrayList<Fourmi>();
		for(Fourmi f : this.fourmis){
			f.avancer();
			if(f.getVilleCourante().equals(this.nid) && f.getCheminCourant() == null){
				fourmisASupprimer.add(f);
			}
		}
		for(Fourmi f : fourmisASupprimer){
			f.déposerPheromones();
			this.fourmis.remove(f);
		}		
	}
	
	public void MAJPheromones(){
		for(Chemin c : this.chemins){
			c.MAJPheromones();
		}
	}
	
	public ArrayList<Fourmi> getFourmis() {
		return fourmis;
	}
	
	@Override
	public String toString(){
		String res = "";
		for(Chemin c : this.chemins){
			res+=c.toString()+" | ";
		}
		return res;
		
	}
	
	public void ajouterVille(Ville v){
		if(this.villes == null){
			this.nid = v;
			this.villes = new ArrayList<Ville>();			
		}
		this.villes.add(v);
	}
	

	public ArrayList<Chemin> getChemins() {
		return chemins;
	}
	
	public ArrayList<Ville> getVilles() {
		return villes;
	}
	public Ville getNid() {
		return nid;
	}
	
	
	public static void main(String[] args) {
		int nbFourmis = Integer.parseInt(args[0]);
		int lc1 = Integer.parseInt(args[1]);
		int lc2 = Integer.parseInt(args[2]);
		float pheromone = Float.parseFloat(args[3]);
		int option = Integer.parseInt(args[4]);
		
		Simulation sim = new Simulation(pheromone);
		
		Ville A = new Ville("A");
		Ville B = new Ville("B");
		
		
		sim.ajouterVille(A);
		sim.ajouterVille(B);
		
		Chemin c1 = new Chemin(A,B,lc1);
		Chemin c2 = new Chemin(A,B,lc2);
		
		sim.getChemins().add(c1);
		sim.getChemins().add(c2);
		
		 int j =0, k = 0;
						
		do{
			for(int i= 0; i<nbFourmis; ++i){
				sim.getFourmis().add(new Fourmi(sim, sim.getNid(), option == 1));
			}
			sim.faireAvancer();						
			sim.MAJPheromones();
			j++;
			k++;
			System.out.println(sim.toString());
			
		}while(c1.getFourmis().size() != 0 && c2.getFourmis().size() != 0);

	}
	
	

}
