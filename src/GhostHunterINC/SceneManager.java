package GhostHunterINC;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SceneManager {
	
	private Stage stage;
	private HUDManager hud = new HUDManager(1);
	private GameEngine gameEngine;
	private final String BUTTON_STYLE = 
            "-fx-background-color: rgba(10, 30, 20, 0.8);"
          + "-fx-border-color: #4dfa9a;"
          + "-fx-border-width: 2px;"
          + "-fx-text-fill: #4dfa9a;"
          + "-fx-font-family: 'Monospaced';"
          + "-fx-font-weight: bold;"
          + "-fx-background-radius: 0;"
          + "-fx-border-radius: 0;";

	
	public SceneManager(Stage stage, GameEngine gameEngine) {
		this.stage = stage;
		this.gameEngine = gameEngine;
	}
	
	public Scene mainMenu() { //Main menu UI
		
		StackPane mainPane = new StackPane(); 
		
		Image mainMenuImage = new Image("file:mainMenuPicture.png");
		ImageView mainMenuView = new ImageView(mainMenuImage);
		
		mainMenuView.fitWidthProperty().bind(stage.widthProperty());
		mainMenuView.fitHeightProperty().bind(stage.heightProperty());
		mainMenuView.setPreserveRatio(false);
		
		VBox layout = new VBox(20);
		layout.setAlignment(Pos.CENTER_RIGHT);
		layout.setPadding(new Insets(0, 80, 0, 0));
		
		Button startGameButton = new Button("Start Hunting");
		Button selectLevelButton = new Button("Select Level");
		Button exitButton = new Button("Exit");
		
		startGameButton.setStyle(BUTTON_STYLE);
		selectLevelButton.setStyle(BUTTON_STYLE);
		exitButton.setStyle(BUTTON_STYLE);

		//Set button sizes according to stage size
		startGameButton.prefWidthProperty().bind(stage.widthProperty().divide(4));
        selectLevelButton.prefWidthProperty().bind(stage.widthProperty().divide(4));
        exitButton.prefWidthProperty().bind(stage.widthProperty().divide(4));

        startGameButton.prefHeightProperty().bind(stage.heightProperty().divide(10));
        selectLevelButton.prefHeightProperty().bind(stage.heightProperty().divide(10));
        exitButton.prefHeightProperty().bind(stage.heightProperty().divide(10));
		
		startGameButton.setOnMouseEntered(e -> startGameButton.setStyle(BUTTON_STYLE + "-fx-background-color: #4dfa9a; -fx-text-fill: black;"));
		startGameButton.setOnMouseExited(e -> startGameButton.setStyle(BUTTON_STYLE));
		
		selectLevelButton.setOnMouseEntered(e -> selectLevelButton.setStyle(BUTTON_STYLE + "-fx-background-color: #4dfa9a; -fx-text-fill: black;"));
	    selectLevelButton.setOnMouseExited(e -> selectLevelButton.setStyle(BUTTON_STYLE));
	    
	    exitButton.setOnMouseEntered(e -> exitButton.setStyle(BUTTON_STYLE + "-fx-background-color: #4dfa9a; -fx-text-fill: black;"));
	    exitButton.setOnMouseExited(e -> exitButton.setStyle(BUTTON_STYLE));
	    
	    stage.widthProperty().addListener((obs, oldVal, newVal) -> {
	        double fontSize = Math.max(12, newVal.doubleValue() / 45); 

	        String dynamicStyle = BUTTON_STYLE + "-fx-font-size: " + fontSize + "px;";
	        
	        startGameButton.setStyle(dynamicStyle);
	        selectLevelButton.setStyle(dynamicStyle);
	        exitButton.setStyle(dynamicStyle);

	        startGameButton.setOnMouseEntered(e -> startGameButton.setStyle(dynamicStyle + "-fx-background-color: #4dfa9a; -fx-text-fill: black;"));
	        startGameButton.setOnMouseExited(e -> startGameButton.setStyle(dynamicStyle));
	        
	        selectLevelButton.setOnMouseEntered(e -> selectLevelButton.setStyle(dynamicStyle + "-fx-background-color: #4dfa9a; -fx-text-fill: black;"));
	        selectLevelButton.setOnMouseExited(e -> selectLevelButton.setStyle(dynamicStyle));
	        
	        exitButton.setOnMouseEntered(e -> exitButton.setStyle(dynamicStyle + "-fx-background-color: #4dfa9a; -fx-text-fill: black;"));
	        exitButton.setOnMouseExited(e -> exitButton.setStyle(dynamicStyle));
	    });
		
		layout.getChildren().addAll(startGameButton, selectLevelButton, exitButton);
		
		mainPane.getChildren().addAll(mainMenuView, layout);
		
		//startGameButton.setOnAction(e -> );
		selectLevelButton.setOnAction(e -> stage.setScene(selectLevelMenu())); //Open level selection menu
		exitButton.setOnAction(e -> System.exit(0)); //Exit to program
		
		return new Scene(mainPane, stage.getWidth(), stage.getHeight());	//Return buttons with scene
	}
	
	Scene selectLevelMenu() { //Level selection menu
		
		StackPane mainPane = new StackPane();
		
		Image selectMenuImage = new Image("file:selectMenuPicture.png");
		ImageView selectMenuView = new ImageView(selectMenuImage);
		selectMenuView.fitWidthProperty().bind(stage.widthProperty());
		selectMenuView.fitHeightProperty().bind(stage.heightProperty());
		selectMenuView.setPreserveRatio(false);
		
		VBox centerLayout = new VBox(20);
		centerLayout.setAlignment(Pos.CENTER); //Align the buttons in the center of VBox
		
		Button levelEasyButton = new Button("Easy");
		Button levelMediumButton = new Button("Medium");
		Button levelHardButton = new Button("Hard");
		
		levelEasyButton.setStyle(BUTTON_STYLE);
		levelMediumButton.setStyle(BUTTON_STYLE);
		levelHardButton.setStyle(BUTTON_STYLE);
		
		levelEasyButton.prefWidthProperty().bind(stage.widthProperty().divide(4));
	    levelMediumButton.prefWidthProperty().bind(stage.widthProperty().divide(4));
	    levelHardButton.prefWidthProperty().bind(stage.widthProperty().divide(4));

	    levelEasyButton.prefHeightProperty().bind(stage.heightProperty().divide(10));
	    levelMediumButton.prefHeightProperty().bind(stage.heightProperty().divide(10));
	    levelHardButton.prefHeightProperty().bind(stage.heightProperty().divide(10));
		
		Button returnMainMenuButton = new Button("Main Menu");
		
		returnMainMenuButton.setStyle(BUTTON_STYLE);

		returnMainMenuButton.prefWidthProperty().bind(stage.widthProperty().divide(4));	
		returnMainMenuButton.prefHeightProperty().bind(stage.heightProperty().divide(10));
		
		levelEasyButton.setOnMouseEntered(e -> levelEasyButton.setStyle(BUTTON_STYLE + "-fx-background-color: #4dfa9a; -fx-text-fill: black;"));
	    levelEasyButton.setOnMouseExited(e -> levelEasyButton.setStyle(BUTTON_STYLE));
	    
	    levelMediumButton.setOnMouseEntered(e -> levelMediumButton.setStyle(BUTTON_STYLE + "-fx-background-color: #4dfa9a; -fx-text-fill: black;"));
	    levelMediumButton.setOnMouseExited(e -> levelMediumButton.setStyle(BUTTON_STYLE));
	    
	    levelHardButton.setOnMouseEntered(e -> levelHardButton.setStyle(BUTTON_STYLE + "-fx-background-color: #4dfa9a; -fx-text-fill: black;"));
	    levelHardButton.setOnMouseExited(e -> levelHardButton.setStyle(BUTTON_STYLE));
	    
	    returnMainMenuButton.setOnMouseEntered(e -> returnMainMenuButton.setStyle(BUTTON_STYLE + "-fx-background-color: #4dfa9a; -fx-text-fill: black;"));
	    returnMainMenuButton.setOnMouseExited(e -> returnMainMenuButton.setStyle(BUTTON_STYLE));
	    
	    stage.widthProperty().addListener((obs, oldVal, newVal) -> {
	        double fontSize = Math.max(12, newVal.doubleValue() / 45); 
	        String dynamicStyle = BUTTON_STYLE + "-fx-font-size: " + fontSize + "px;";
	        
	        levelEasyButton.setStyle(dynamicStyle);
	        levelMediumButton.setStyle(dynamicStyle);
	        levelHardButton.setStyle(dynamicStyle);
	        returnMainMenuButton.setStyle(dynamicStyle);
	    });
	    
	    centerLayout.getChildren().addAll(levelEasyButton, levelMediumButton, levelHardButton, returnMainMenuButton);
		
	    mainPane.getChildren().addAll(selectMenuView, centerLayout);
		
		BorderPane.setAlignment(returnMainMenuButton, Pos.CENTER_RIGHT); //Align the main menu button in the right (bottom) of border pane
		BorderPane.setMargin(returnMainMenuButton, new Insets(10)); //Add spacing from the edges
		
		returnMainMenuButton.setOnAction(e -> stage.setScene(mainMenu())); //Return to main menu from Level Selection Menu
		levelEasyButton.setOnAction(e -> gameEngine.startGame(1, stage));
		levelMediumButton.setOnAction(e -> gameEngine.startGame(2, stage));
		levelHardButton.setOnAction(e -> gameEngine.startGame(3, stage));
		
		return new Scene(mainPane, stage.getWidth(), stage.getHeight()); //Return border pane with scene
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
		
		retryButton.setOnAction(e -> gameEngine.startGame(gameEngine.getCurrentLevel(), stage)); //Reopen the same level
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
		
		retryButton.setOnAction(e -> gameEngine.startGame(gameEngine.getCurrentLevel(), stage)); //Reopen the same level
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
