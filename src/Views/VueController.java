package Views;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import java.util.ArrayList;

import Model.Chemin;
import Model.Fourmi;
import Model.Simulation;
import Model.Ville;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;

import javafx.scene.control.Slider;
import javafx.scene.effect.Light.Point;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class VueController {
	
	private ArrayList<CheminIHM> chemins = new ArrayList<CheminIHM>();
	private ArrayList<VilleIHM> villes = new ArrayList<VilleIHM>();
	private Simulation sim;
	private Service<Void> service  = new Service<Void>() {

        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {

                @Override
                protected Void call() throws Exception {                		
        			while(!isCancelled()){
        				onStart();
        			}
					return null;
                }
            };
        }
    };
	
	
	
	@FXML
	private ComboBox nbVilles;
	@FXML
	private Slider nbFourmis;
	@FXML
	private Slider pheromones;
	@FXML
	private Button boutton;
	@FXML
	private Pane drawingPane;
	@FXML
	private CheckBox tailleChemin;

	// Event Listener on ComboBox[#nbVilles].onAction
	@FXML
	public void handleButtonAction(ActionEvent event) {
		
		
		this.drawingPane.getChildren().clear();
		this.villes.clear();
		this.chemins.clear();
		
		CheminIHM cihm;
		double centreX = this.drawingPane.getWidth()/2;
		double centreY = this.drawingPane.getHeight()/2;
		int nbVilles = Integer.parseInt((String) this.nbVilles.getValue());
		double angle = 2*Math.PI/nbVilles;
		VilleIHM vihm;
		Text t;
		char car = 'A';
		Ville v;
						
		for(int i = 0; i< nbVilles ; ++i){
			v = new Ville(""+car);
			
			t = new Text(""+car);
			t.setX(210*Math.cos(i*angle)+centreX);
			t.setY(210*Math.sin(i*angle)+centreY);
			vihm = new VilleIHM(200*Math.cos(i*angle)+centreX, 200*Math.sin(i*angle)+centreY, t);
			this.drawingPane.getChildren().add(t);
			this.villes.add(vihm);
			
			
			car ++;
		}
		
		for(int i = 0; i < this.villes.size(); ++i){
			for(int j = i+1; j< this.villes.size(); ++j){
				
				cihm = new CheminIHM(this.villes.get(i), this.villes.get(j), this.drawingPane);
				this.chemins.add(cihm);
				this.drawingPane.getChildren().add(cihm);
			}
		}
		
	}
	
	// Event Listener on Button[#boutton].onAction
		@FXML
		public void startOnAction(ActionEvent event) {
			
			if(this.boutton.getText().equals("Lancer")){
				
				
				this.boutton.setText("Stop");
				this.sim = new Simulation(this.pheromones.getValue());
				Chemin c;
				int l;
				Ville v;
				
				for(VilleIHM vihm : this.villes){
					v = new Ville(vihm.getText().getText());
					vihm.setModel(v);
					sim.ajouterVille(v);
				}
										
				for(CheminIHM cihm : this.chemins){					
					if(!cihm.getLongueur().getText().trim().isEmpty()){
						l = Integer.parseInt(cihm.getLongueur().getText());
					}
					else{
						l =20;
					}
					
					c = new Chemin(cihm.getV1().getModel(), cihm.getV2().getModel(), l);
					cihm.setModel(c);
					sim.getChemins().add(c);
					
				}
				this.nbVilles.setDisable(true);
		        this.service.start();
				
			} else if(this.boutton.getText().equals("Stop")){
				this.nbVilles.setDisable(false);
				this.service.cancel();			
				this.service.reset();
				this.boutton.setText("Lancer");
			}			
		}
		
		private void onStart(){
			
			
			int j =0, k=0;
						
			for(CheminIHM cihm : this.chemins){
				if(cihm.getModel().getFourmis().size() == 0) cihm.setOpacity(0.2);
				else cihm.setOpacity(1);
			}
			for(int i= 0; i<this.nbFourmis.getValue(); ++i){				
				sim.getFourmis().add(new Fourmi(sim, sim.getNid(), this.tailleChemin.isSelected()));
			}
			sim.faireAvancer();						
			sim.MAJPheromones();
			j++;
			k++;
			System.out.println(sim.toString());
			
		}
	
	@FXML
	private void initialize(){
		this.nbVilles.getItems().addAll("4", "5", "6", "7", "8");
	}
}
