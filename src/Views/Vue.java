package Views;



import com.sun.javafx.geom.Rectangle;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Vue extends Application {
	
	@FXML
	private ComboBox<String> nbVilles;
	
	@FXML
	

	public static void main(String[] args) {
        Application.launch(Vue.class, args);
    }
	
	@Override
	public void start(Stage arg0) throws Exception {	
		
		
		
		
		
		
		
		
		
		

//		// Dimensions de l'écran
//		Screen screen =	Screen.getPrimary();
//		
//		
//		Rectangle2D	bounds = screen.getVisualBounds();
//		double screenWidth = bounds.getWidth();
//		double screenHeight	= bounds.getHeight();
//        //////////////////////////////////////////
//        
//        BorderPane root = new BorderPane();
//        
//        
//        // Mise en place du menu coté gauche
//        VBox vbox = new VBox();
//        vbox.setStyle("-fx-background-color: #336699;");                        
//        
//        
//        
//        HBox hboxFourmis = new HBox();             
//        Label nbFourmisLabel = new Label("Nombre de Fourmis");
//        nbFourmisLabel.setPadding(new Insets(10));
//        Slider nbFourmiSlider = new Slider();
//        nbFourmiSlider.setShowTickLabels(true);
//        nbFourmiSlider.setStyle("-fx-color: black");      
//        nbFourmiSlider.setMin(10);
//        nbFourmiSlider.setMax(500);
//        hboxFourmis.getChildren().addAll(nbFourmisLabel,nbFourmiSlider);
//        
//        HBox hboxVilles = new HBox(); 
//        Label nbVillesLabel = new Label("Nombre de villes");
//        ObservableList<String> options = 
//        	    FXCollections.observableArrayList(
//        	        "1",
//        	        "2",
//        	        "3",
//        	        "4"
//        	    );
//        this.nbVillesBox = new ComboBox(options);
//        this.nbVillesBox.setOnAction(new EventHandler<ActionEvent>(){
//
//			@Override
//			public void handle(ActionEvent arg0) {
//				
//				
//			}
//        	
//        });
//        
//        this.nbVillesBox.setPadding(new Insets(10));
//        nbVillesLabel.setPadding(new Insets(10));
//        hboxVilles.getChildren().addAll(nbVillesLabel,this.nbVillesBox);
//        
//        
//        VBox.setMargin(hboxVilles, new Insets(10));
//        VBox.setMargin(hboxFourmis, new Insets(10));
//        
//        Button button = new Button("Lancer"); 
//        
////        vbox.setAlignment(Pos.TOP_CENTER);
//        vbox.getChildren().addAll(hboxFourmis, hboxVilles, button);
//        root.setLeft(vbox);
//        
//        //////////////////////////////////////////////////////////////////
//        
//        
//        
//        
//        
//        
//        Scene scene = new Scene(root, screenWidth, screenHeight);
//                     
//        arg0.setScene(scene);
//        arg0.show();
//		
	}
	
	//Event Combobox
	private void nbVillesOnChange(){
		
	}
	
	

}
