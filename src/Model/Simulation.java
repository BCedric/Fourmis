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
		Simulation sim = new Simulation(0.8);
		
		Ville A = new Ville("A");
		Ville B = new Ville("B");
		Ville C = new Ville("C");
		Ville D = new Ville("D");
		
		sim.ajouterVille(A);
		sim.ajouterVille(B);
		sim.ajouterVille(C);
		sim.ajouterVille(D);
		
		sim.getChemins().add(new Chemin(A,B,5));
		sim.getChemins().add(new Chemin(B,C,10));
		sim.getChemins().add(new Chemin(C,D,9));
		sim.getChemins().add(new Chemin(A,D,10));
		sim.getChemins().add(new Chemin(D,B,8));
		sim.getChemins().add(new Chemin(C,A,14));
		
		
		
		int j =0, k=0;
		do{
			for(int i= 0; i<100; ++i){
				sim.getFourmis().add(new Fourmi(sim, sim.getNid()));
			}
			sim.faireAvancer();						
			sim.MAJPheromones();
			j++;
			k++;
			System.out.println(sim.toString());
			
		}while(true);

	}
	
	

}
