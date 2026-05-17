package GhostHunterINC;

//The whole class is implemented by 150124045_Mehmet_Burak_Mentese.

import javafx.application.Application;
import javafx.stage.Stage;

public class MainGame extends Application {

	@Override
	public void start(Stage primaryStage) { //Create main stage
		
		//Read info from config manager
		ConfigManager config = new ConfigManager();
		config.loadConfig();
		
		//Create game engine
		GameEngine gameEngine = new GameEngine();
		
		//Create main scene
		SceneManager sceneManager = new SceneManager(primaryStage, gameEngine);
		primaryStage.setScene(sceneManager.mainMenu());
		primaryStage.setTitle("Ghost Hunter INC");
		primaryStage.show();
	}
	
	//Launch the program
	public static void main(String[] args) {
		launch(args);
	}
}