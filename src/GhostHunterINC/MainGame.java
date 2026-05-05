package GhostHunterINC;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class MainGame extends Application {

	public void start(Stage primaryStage) { //Create main stage
		
		GameEngine gameEngine = new GameEngine();
		
		SceneManager sceneManager = new SceneManager(primaryStage, gameEngine);
		primaryStage.setScene(sceneManager.mainMenu());
		primaryStage.setTitle("Ghost Hunter INC");
		primaryStage.show();
		
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
