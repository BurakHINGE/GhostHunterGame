package GhostHunterINC;

import javafx.application.Application;
import javafx.stage.Stage;



public class MainGame extends Application {

	public void start(Stage primaryStage) { //Create main stage
		
		SceneManager sceneManager = new SceneManager(primaryStage);
		
		primaryStage.setScene(sceneManager.mainMenu());
		primaryStage.setTitle("Ghost Hunter INC");
		primaryStage.show();
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
