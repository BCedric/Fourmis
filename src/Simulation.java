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
//		System.out.println(fourmisASupprimer.size());
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
	
	public boolean rapport(){
		int c1 = 0, c2 = 0, c3 = 0, c4 = 0, c5 = 0, c6 = 0;
		for(Fourmi f : this.fourmis){
			if(f.getCheminCourant() != null && f.getCheminCourant().equals(this.chemins.get(0))) c1++;
			else if(f.getCheminCourant() != null &&f.getCheminCourant().equals(this.chemins.get(1))) c2++;
			else if(f.getCheminCourant() != null &&f.getCheminCourant().equals(this.chemins.get(2))) c3++;
			else if(f.getCheminCourant() != null &&f.getCheminCourant().equals(this.chemins.get(3))) c4++;
			else if(f.getCheminCourant() != null &&f.getCheminCourant().equals(this.chemins.get(4))) c5++;
			else if(f.getCheminCourant() != null &&f.getCheminCourant().equals(this.chemins.get(5))) c6++;
		}
		System.out.println("Chemin AB : "+c1+" fourmis, et "+Double.toString(this.chemins.get(0).getPheromoneActive()).substring(0,3)
				+ ",Chemin BC : "+c2+" fourmis, et "+Double.toString(this.chemins.get(1).getPheromoneActive()).substring(0,3)
				+ ",Chemin CD : "+c3+" fourmis, et "+Double.toString(this.chemins.get(2).getPheromoneActive()).substring(0,3)
				+ ",Chemin AD : "+c4+" fourmis, et "+Double.toString(this.chemins.get(3).getPheromoneActive()).substring(0,3)
				+ ",Chemin DB : "+c5+" fourmis, et "+Double.toString(this.chemins.get(4).getPheromoneActive()).substring(0,3)
				+ ",Chemin AC : "+c6+" fourmis, et "+Double.toString(this.chemins.get(5).getPheromoneActive()).substring(0,3)
				);
//		return !(c2 == 0 && c4 == 0);
		return true;
		
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
			if(j> 100 && j % 10 == 0){
				System.out.println();
			}
			sim.MAJPheromones();
			j++;
			k++;
			
		}while(sim.rapport());

	}
	
	

}
