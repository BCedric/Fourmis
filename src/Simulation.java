import java.util.ArrayList;

public class Simulation {
	
	Ville nid;
	
	ArrayList<Ville> villes;
	ArrayList<Fourmi> fourmis;
	ArrayList<Chemin> chemins;
	
	
	
	
	public Simulation() {
		super();		
		this.fourmis = new ArrayList<Fourmi>();
		this.chemins = new ArrayList<Chemin>();		
	}
	public void faireAvancer(){
		ArrayList<Fourmi> fourmisASupprimer = new ArrayList<Fourmi>();
		for(Fourmi f : this.fourmis){
			f.avancer();
			if(f.getVilleCourante().equals(this.nid) && f.getCheminCourant() == null){
				fourmisASupprimer.add(f);
			}
		}
		System.out.println(fourmisASupprimer.size());
		for(Fourmi f : fourmisASupprimer){
			this.fourmis.remove(f);
		}
	}
	
	public ArrayList<Fourmi> getFourmis() {
		return fourmis;
	}
	
	public boolean rapport(){
		int c1 = 0, c2 = 0;
		for(Fourmi f : this.fourmis){
			if(f.getCheminCourant() != null && f.getCheminCourant().equals(this.chemins.get(0))) c1++;
			else if(f.getCheminCourant() != null &&f.getCheminCourant().equals(this.chemins.get(1))) c2++;
		}
		System.out.println("Il y a "+c1+" fourmis sur le chemin 1 et "+c2+" sur le chemin 2");
		return c1 != 0 && c2 != 0;
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
		Simulation sim = new Simulation();
		
		Ville A = new Ville("A");
		Ville B = new Ville("B");
		
		sim.ajouterVille(A);
		sim.ajouterVille(B);
		
		sim.getChemins().add(new Chemin(A,B,15));
		sim.getChemins().add(new Chemin(A,B,10));
		
		
		
		int j =0;
		do{
			for(int i= 0; i<100; ++i){
				sim.getFourmis().add(new Fourmi(sim, sim.getNid()));
			}
			sim.faireAvancer();			
			if(j> 100 && j % 10 == 0){
				System.out.println();
			}
			
			j++;	
		}while(sim.rapport());

	}
	
	

}
