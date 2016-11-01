package Views;

import Model.Ville;
import javafx.scene.control.Label;
import javafx.scene.effect.Light.Point;
import javafx.scene.text.Text;

public class VilleIHM extends Point{
	
	Text text;
	Ville model;
	
	

	public VilleIHM(double x, double y, Text text){
		super();
		this.setX(x);
		this.setY(y);
		this.text = text;		
	}
	
	public Text getText() {
		return text;
	}

	public Ville getModel() {
		return model;
	}

	public void setModel(Ville model) {
		this.model = model;
	}
	
}
