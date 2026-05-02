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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SceneManager {
	
	private Stage stage;
	private HUDManager hud = new HUDManager(1);
	
	public SceneManager(Stage stage) {
		this.stage = stage;
	}
	
	public Scene mainMenu() { //Main menu UI
		
		VBox layout = new VBox(10);
		
		Button selectLevelButton = new Button("Select Level");
		Button exitButton = new Button("Exit");
		
		layout.setStyle("-fx-background-color: lightgray;");
		selectLevelButton.setStyle("-fx-background-color: magenta;");
		selectLevelButton.setTextFill(Color.WHITE);
		selectLevelButton.setFont(new Font(17));
		selectLevelButton.setPrefSize(120, 50);
		exitButton.setStyle("-fx-background-color: magenta");
		exitButton.setTextFill(Color.WHITE);
		exitButton.setFont(new Font(17));
		exitButton.setPrefSize(120, 50);
		
		layout.getChildren().addAll(selectLevelButton, exitButton);
		layout.setAlignment(Pos.CENTER); //Align the buttons in the center
		
		
		selectLevelButton.setOnAction(e -> stage.setScene(selectLevelMenu())); //Open level selection menu
		exitButton.setOnAction(e -> System.exit(0)); //Exit to program
		
		return new Scene(layout, 500, 500);	//Return buttons with scene
	}
	
	Scene selectLevelMenu() { //Level selection menu
		BorderPane mainLayout = new BorderPane();
		
		VBox centerLayout = new VBox(10);
		
		centerLayout.setAlignment(Pos.CENTER); //Align the buttons in the center of VBox
		
		Button levelEasyButton = new Button("Easy");
		Button levelMediumButton = new Button("Medium");
		Button levelHardButton = new Button("Hard");
		
		mainLayout.setStyle("-fx-background-color: lightgray");
		levelEasyButton.setStyle("-fx-background-color: lightgreen");
		levelEasyButton.setTextFill(Color.WHITE);
		levelEasyButton.setFont(new Font(18));
		levelEasyButton.setPrefSize(100, 50);
		levelMediumButton.setStyle("-fx-background-color: gold");
		levelMediumButton.setTextFill(Color.WHITE);
		levelMediumButton.setFont(new Font(15));
		levelMediumButton.setPrefSize(100, 50);
		levelHardButton.setStyle("-fx-background-color: darkred");
		levelHardButton.setTextFill(Color.WHITE);
		levelHardButton.setFont(new Font(15));
		levelHardButton.setPrefSize(100, 50);
		
		centerLayout.getChildren().addAll(levelEasyButton, levelMediumButton, levelHardButton);
		
		Button returnMainMenuButton = new Button("Main Menu");

		returnMainMenuButton.setStyle("-fx-background-color: silver");
		returnMainMenuButton.setTextFill(Color.WHITE);
		returnMainMenuButton.setFont(new Font(18));
		returnMainMenuButton.setPrefSize(120, 50);	
		
		mainLayout.setCenter(centerLayout); //Align the VBox in the center of border pane
		mainLayout.setBottom(returnMainMenuButton); //Align the main menu button in the bottom of border pane
		
		BorderPane.setAlignment(returnMainMenuButton, Pos.CENTER_RIGHT); //Align the main menu button in the right (bottom) of border pane
		BorderPane.setMargin(returnMainMenuButton, new Insets(10)); //Add spacing from the edges
		
		returnMainMenuButton.setOnAction(e -> stage.setScene(mainMenu())); //Return to main menu from Level Selection Menu
		
		return new Scene(mainLayout, 500, 500); //Return border pane with scene
	}
	
	public Scene stopGamePanel() { //Stopped game menu
		
		VBox layout = new VBox(10);
		
		layout.setAlignment(Pos.CENTER);
		
		Label pauseText = new Label("GAME PAUSED");
		
		HBox buttonLayout = new HBox(10);
		
		buttonLayout.setAlignment(Pos.CENTER);
		
		Button resumeButton = new Button("Resume");
		Button returnMainMenuButton = new Button("Main Menu");
		
		buttonLayout.getChildren().addAll(resumeButton, returnMainMenuButton);
		
		layout.getChildren().addAll(pauseText, buttonLayout);
		
//		resumeButton.setOnAction(e -> ); Game resume from the same time
		returnMainMenuButton.setOnAction(e -> stage.setScene(mainMenu())); //Return to main menu from stopped game menu
		
		return new Scene(layout, 500, 500);
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
