package Views;

import java.io.IOException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception{		
		
		primaryStage.setTitle("Fourmis");
		
		try {
//			 Dimensions de l'écran
			Screen screen =	Screen.getPrimary();
			
			
			Rectangle2D	bounds = screen.getVisualBounds();
			double screenWidth = bounds.getWidth();
			double screenHeight	= bounds.getHeight();
	        //////////////////////////////////////////
			
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("Vue.fxml"));
			BorderPane page = (BorderPane) loader.load();
			Scene scene = new Scene(page, screenWidth, screenHeight);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	

}
