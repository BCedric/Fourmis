package Views;

import Model.Chemin;
import javafx.scene.control.TextField;
import javafx.scene.effect.Light.Point;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class CheminIHM extends Line{

	private TextField longueur;
	private Pane drawingPane;
	private Chemin model;
	private VilleIHM v1;
	private VilleIHM v2;
	
	public CheminIHM(VilleIHM p1, VilleIHM p2, Pane drawingPane){
		super();
		
		this.v1 = p1;
		this.v2 = p2;
		this.model = model;
		this.setStartX(p1.getX());
		this.setStartY(p1.getY());
		this.setEndX(p2.getX());
		this.setEndY(p2.getY());
		this.drawingPane = drawingPane;
		this.longueur = new TextField();
		if(p1.getX()<p2.getX()) {						
			this.longueur.setLayoutX(p1.getX()+2*(p2.getX()-p1.getX())/3);
			this.longueur.setLayoutY(p1.getY()+2*(p2.getY()-p1.getY())/3);
		} else {
			this.longueur.setLayoutX(p2.getX()+2*(p1.getX()-p2.getX())/3);
			this.longueur.setLayoutY(p2.getY()+2*(p1.getY()-p2.getY())/3);
		}
		
		this.longueur.setPrefWidth(30);
		drawingPane.getChildren().add(this.longueur);
	}

	public Chemin getModel() {
		return model;
	}

	public void setModel(Chemin model) {
		this.model = model;
	}

	public VilleIHM getV1() {
		return v1;
	}

	public VilleIHM getV2() {
		return v2;
	}

	public TextField getLongueur() {
		return longueur;
	}
	
	
	
}
