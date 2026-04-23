package GhostHunterINC;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
		
		selectLevelButton.setPrefSize(100, 50);
		exitButton.setPrefSize(100, 50);
		
		layout.getChildren().addAll(selectLevelButton, exitButton);
		
		layout.setAlignment(Pos.CENTER);
		
		
		selectLevelButton.setOnAction(e -> stage.setScene(selectLevelMenu())); //Open level selection menu
		exitButton.setOnAction(e -> System.exit(0)); //Exit to program
		
		return new Scene(layout, 500, 500);	//Return buttons with scene
	}
	
	Scene selectLevelMenu() { //Level selection menu
		
		BorderPane mainLayout = new BorderPane();
		
		VBox centerLayout = new VBox(10);
		
		centerLayout.setAlignment(Pos.CENTER);
		
		Button levelEasyButton = new Button("Easy");
		Button levelMediumButton = new Button("Medium");
		Button levelHardButton = new Button("Hard");
		
		levelEasyButton.setPrefSize(100, 50);
		levelMediumButton.setPrefSize(100, 50);
		levelHardButton.setPrefSize(100, 50);
		
		
		Button returnMainMenuButton = new Button("Main Menu");

		returnMainMenuButton.setPrefSize(100, 50);
		
		centerLayout.getChildren().addAll(levelEasyButton, levelMediumButton, levelHardButton);	
		
		mainLayout.setCenter(centerLayout);
		mainLayout.setBottom(returnMainMenuButton);
		
		BorderPane.setAlignment(returnMainMenuButton, Pos.CENTER_RIGHT);
		BorderPane.setMargin(returnMainMenuButton, new Insets(10));
		
		returnMainMenuButton.setOnAction(e -> stage.setScene(mainMenu())); //Return to main menu from Level Selection Menu
		
		return new Scene(mainLayout, 500, 500); //Return button with scene
	}
	
	public Scene winPanel() { //Show this panel if user win the game
		
		VBox layout = new VBox(10);
		
		Label winText = new Label("Congratulations, You Win!");
		Button retryButton = new Button("Play Again");
		Button returnMainMenuButton = new Button("Main Menu");
		
		winText.setPrefSize(200, 75);
		retryButton.setPrefSize(100, 50);
		returnMainMenuButton.setPrefSize(100, 50);
		
		layout.getChildren().addAll(winText, retryButton, returnMainMenuButton);
		
		layout.setAlignment(Pos.CENTER);
		
//		retryButton.setOnAction(e -> ); Reopen the same level
		returnMainMenuButton.setOnAction(e -> stage.setScene(mainMenu())); //Return to main menu
		
		return new Scene(layout, 500, 500);
	}
	
	public Scene losePanel() { //Show this panel if user lose the game
		
		VBox layout = new VBox(10);
		
		Label loseText = new Label("Oh No, You Lose. Let's Try Again");
		Button retryButton = new Button("Try Again");
		Button returnMainMenuButton = new Button("Main Menu");
		
		loseText.setPrefSize(200, 75);
		retryButton.setPrefSize(100, 50);
		returnMainMenuButton.setPrefSize(100, 50);
		
		layout.getChildren().addAll(loseText, retryButton, returnMainMenuButton);
		
		layout.setAlignment(Pos.CENTER);
		
//		retryButton.setOnAction(e -> ); Reopen the same level
		returnMainMenuButton.setOnAction(e -> stage.setScene(mainMenu())); //Return to main menu
		
		return new Scene(layout, 500, 500);
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
