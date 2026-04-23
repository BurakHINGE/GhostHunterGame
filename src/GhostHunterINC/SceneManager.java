package GhostHunterINC;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneManager {
	
	private Stage stage;
	
	public SceneManager(Stage stage) {
		this.stage = stage;
	}
	
	public Scene mainMenu() { //Main menu UI
		
		VBox layout = new VBox(10);
		
		Button selectLevelButton = new Button("Select Level");
		Button exitButton = new Button("Exit");
		
		selectLevelButton.setOnAction(e -> stage.setScene(selectLevelMenu())); //Open level selection menu
		exitButton.setOnAction(e -> System.exit(0)); //Exit to program
		
		layout.getChildren().addAll(selectLevelButton, exitButton);
		
		return new Scene(layout, 500, 500);	//Return buttons with scene
	}
	
	Scene selectLevelMenu() { //Level selection menu
		
		VBox levelsLayout = new VBox(10);
		
		Button levelEasyButton = new Button("Easy");
		Button levelMediumButton = new Button("Medium");
		Button levelHardButton = new Button("Hard");
		Button returnMainMenuButton = new Button("Main Menu");
		
		returnMainMenuButton.setOnAction(e -> stage.setScene(mainMenu())); //Return to main menu from Level Selection Menu
		
		levelsLayout.getChildren().addAll(levelEasyButton, levelMediumButton, levelHardButton, returnMainMenuButton);
		
		return new Scene(levelsLayout, 500, 500); //Return button with scene
	}
	
	public Scene winPanel() { //Show this panel if user win the game
		
		VBox layout = new VBox(10);
		
		Label winText = new Label("Congratulations, You Win!");
		Button retryButton = new Button("Play Again");
		Button returnMainMenuButton = new Button("Main Menu");
		
//		retryButton.setOnAction(e -> ); Reopen the same level
		returnMainMenuButton.setOnAction(e -> stage.setScene(mainMenu())); //Return to main menu
		
		layout.getChildren().addAll(winText, retryButton, returnMainMenuButton);
		
		return new Scene(layout, 500, 500);
	}
	
	public Scene losePanel() { //Show this panel if user lose the game
		
		VBox layout = new VBox(10);
		
		Label loseText = new Label("Oh No, You Lose. Let's Try Again");
		Button retryButton = new Button("Try Again");
		Button returnMainMenuButton = new Button("Main Menu");
		
//		retryButton.setOnAction(e -> ); Reopen the same level
		returnMainMenuButton.setOnAction(e -> stage.setScene(mainMenu())); //Return to main menu
		
		layout.getChildren().addAll(loseText, retryButton, returnMainMenuButton);
		
		return new Scene(layout, 500, 500);
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
