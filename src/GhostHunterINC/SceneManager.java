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
          + "-fx-font-family: 'Creepster';"
          + "-fx-font-weight: bold;"
          + "-fx-background-radius: 0;"
          + "-fx-border-radius: 0;";

	
	public SceneManager(Stage stage, GameEngine gameEngine) {
		this.stage = stage;
		this.gameEngine = gameEngine;
		
		String fontPath = "file:resources/fonts/Creepster-Regular.ttf";

	    try {
	        Font loadedFont = Font.loadFont(fontPath, 14);
	        if (loadedFont != null) {
	            System.out.println("Font Yüklendi: " + loadedFont.getFamily());
	        } else {
	            System.out.println("Font bulunamadı, yol yanlış olabilir: " + fontPath);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
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
		
		applyButtonStyles(stage.getWidth(), startGameButton, selectLevelButton, exitButton);
		
		stage.widthProperty().addListener((obs, oldVal, newVal) -> {
			applyButtonStyles(newVal.doubleValue(), startGameButton, selectLevelButton, exitButton);
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
		
		applyButtonStyles(stage.getWidth(), levelEasyButton, levelMediumButton, levelHardButton, returnMainMenuButton);
		
		stage.widthProperty().addListener((obs, oldVal, newVal) -> {
			applyButtonStyles(newVal.doubleValue(), levelEasyButton, levelMediumButton, levelHardButton, returnMainMenuButton);
		});
	    
	    centerLayout.getChildren().addAll(levelEasyButton, levelMediumButton, levelHardButton, returnMainMenuButton);
		
	    mainPane.getChildren().addAll(selectMenuView, centerLayout);
		
		BorderPane.setAlignment(returnMainMenuButton, Pos.CENTER_RIGHT); //Align the main menu button in the right (bottom) of border pane
		BorderPane.setMargin(returnMainMenuButton, new Insets(10)); //Add spacing from the edges
		
		returnMainMenuButton.setOnAction(e -> stage.setScene(mainMenu())); //Return to main menu from Level Selection Menu
		levelEasyButton.setOnAction(e -> gameEngine.startGame(1, stage, this));
		levelMediumButton.setOnAction(e -> gameEngine.startGame(2, stage, this));
		levelHardButton.setOnAction(e -> gameEngine.startGame(3, stage, this));
		
		return new Scene(mainPane, stage.getWidth(), stage.getHeight()); //Return border pane with scene
	}

	public Scene stopGamePanel() { //Stopped game menu
		
		StackPane mainPane = new StackPane();
		
		Image pauseGamePicture = new Image("file:pauseMenuPicture.png");
		ImageView pauseMenuView = new ImageView(pauseGamePicture);
		pauseMenuView.fitWidthProperty().bind(stage.widthProperty());
		pauseMenuView.fitHeightProperty().bind(stage.heightProperty());
		pauseMenuView.setPreserveRatio(false);
		
		VBox layout = new VBox(10);
		
		layout.setAlignment(Pos.CENTER);
		
		Label pauseText = new Label("GAME PAUSED");
		
		HBox buttonLayout = new HBox(10);
		
		buttonLayout.setAlignment(Pos.CENTER);
		
		Button resumeButton = new Button("Resume");
		Button returnMainMenuButton = new Button("Main Menu");
		
		resumeButton.setStyle(BUTTON_STYLE);
		returnMainMenuButton.setStyle(BUTTON_STYLE);
		
		resumeButton.prefWidthProperty().bind(stage.widthProperty().divide(4));	
		resumeButton.prefHeightProperty().bind(stage.heightProperty().divide(10));
		
		returnMainMenuButton.prefWidthProperty().bind(stage.widthProperty().divide(4));	
		returnMainMenuButton.prefHeightProperty().bind(stage.heightProperty().divide(10));
		
		applyButtonStyles(stage.getWidth(), resumeButton, returnMainMenuButton);
		
		stage.widthProperty().addListener((obs, oldVal, newVal) -> {
			applyButtonStyles(newVal.doubleValue(), resumeButton, returnMainMenuButton);
		});
		
		buttonLayout.getChildren().addAll(resumeButton, returnMainMenuButton);
		
		layout.getChildren().addAll(pauseText, buttonLayout);
		
		mainPane.getChildren().addAll(pauseMenuView, layout);
		
		resumeButton.setOnAction(e -> { //Game resume from the same time
			stage.setScene(gameEngine.getGameScene());
			
			gameEngine.resumeGame();
		});
		returnMainMenuButton.setOnAction(e -> stage.setScene(mainMenu())); //Return to main menu from stopped game menu
		
		return new Scene(mainPane, stage.getWidth(), stage.getHeight());
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
		
		retryButton.setOnAction(e -> gameEngine.startGame(gameEngine.getCurrentLevel(), stage, this)); //Reopen the same level
		returnMainMenuButton.setOnAction(e -> stage.setScene(mainMenu())); //Return to main menu
		
		return new Scene(layout, stage.getWidth(), stage.getHeight());
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
		
		retryButton.setOnAction(e -> gameEngine.startGame(gameEngine.getCurrentLevel(), stage, this)); //Reopen the same level
		returnMainMenuButton.setOnAction(e -> stage.setScene(mainMenu())); //Return to main menu
		
		return new Scene(layout, stage.getWidth(), stage.getHeight());
	}
	
	private void applyButtonStyles(double width, Button... buttons) {
	    double validWidth = Double.isNaN(width) ? 800 : width; 
	    double fontSize = Math.max(12, validWidth / 45);
	    String dynamicStyle = BUTTON_STYLE + "-fx-font-size: " + fontSize + "px;";

	    for (Button btn : buttons) {
	        btn.setStyle(dynamicStyle);
	        btn.setOnMouseEntered(e -> btn.setStyle(dynamicStyle + "-fx-background-color: #4dfa9a; -fx-text-fill: black;"));
	        btn.setOnMouseExited(e -> btn.setStyle(dynamicStyle));
	    }
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}

}