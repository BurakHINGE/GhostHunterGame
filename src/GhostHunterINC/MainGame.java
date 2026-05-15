package GhostHunterINC;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class MainGame extends Application {

	public void start(Stage primaryStage) { //Create main stage
		
		ConfigManager config = new ConfigManager();
		config.loadConfig();
		
		Pane gameObjects = new Pane();
		Scene gameScene = new Scene(gameObjects, primaryStage.getWidth(), primaryStage.getHeight());
		Hunter hunter = new Hunter(30.0, 30.0, 0.0, 0.0, 0.0, 0.0);
		ArrayList<GameObject> allEntities = new ArrayList<>();
		LevelManager levelManager = new LevelManager(gameObjects, allEntities);
		
		GameEngine gameEngine = new GameEngine(gameObjects, gameScene, levelManager, hunter,allEntities);
		
		SceneManager sceneManager = new SceneManager(primaryStage, gameEngine);
		primaryStage.setScene(sceneManager.mainMenu());
		primaryStage.setTitle("Ghost Hunter INC");
		primaryStage.show();
		
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
