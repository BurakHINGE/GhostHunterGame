package GhostHunterINC;

import javafx.application.Application;
import javafx.stage.Stage;



public class MainGame extends Application {

	@Override
	public void start(Stage primaryStage) { //Create main stage
		
		ConfigManager config = new ConfigManager();
		config.loadConfig();
		
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