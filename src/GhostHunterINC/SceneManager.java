package GhostHunterINC;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

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
	private final String RECTANGLE_STYLE = 
            "-fx-fill: rgba(10, 30, 20, 0.8);"
          + "-fx-stroke: #4dfa9a;"
          + "-fx-stroke-width: 2px;";
	
	public SceneManager(Stage stage, GameEngine gameEngine) {
		this.stage = stage;
		this.gameEngine = gameEngine;
		
		this.stage.setFullScreen(true);
		this.stage.setFullScreenExitHint("");
		
		String fontPath = "file:resources/fonts/Creepster-Regular.ttf";

	    try {
	        Font loadedFont = Font.loadFont(fontPath, 14);
	        if (loadedFont != null) {
	            System.out.println("Font has been uploaded successfuly: " + loadedFont.getFamily());
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
		Button controlButton = new Button("Controls");
		Button exitButton = new Button("Exit");
		
		startGameButton.setStyle(BUTTON_STYLE);
		selectLevelButton.setStyle(BUTTON_STYLE);
		controlButton.setStyle(BUTTON_STYLE);
		exitButton.setStyle(BUTTON_STYLE);

		//Set button sizes according to stage size
		startGameButton.prefWidthProperty().bind(stage.widthProperty().divide(4));
        selectLevelButton.prefWidthProperty().bind(stage.widthProperty().divide(4));
        controlButton.prefWidthProperty().bind(stage.widthProperty().divide(4));
        exitButton.prefWidthProperty().bind(stage.widthProperty().divide(4));

        startGameButton.prefHeightProperty().bind(stage.heightProperty().divide(10));
        selectLevelButton.prefHeightProperty().bind(stage.heightProperty().divide(10));
        controlButton.prefHeightProperty().bind(stage.heightProperty().divide(10));
        exitButton.prefHeightProperty().bind(stage.heightProperty().divide(10));
		
		applyButtonStyles(stage.getWidth(), startGameButton, selectLevelButton, controlButton, exitButton);
		
		stage.widthProperty().addListener((obs, oldVal, newVal) -> {
			applyButtonStyles(newVal.doubleValue(), startGameButton, selectLevelButton, controlButton, exitButton);
		});
		
		layout.getChildren().addAll(startGameButton, selectLevelButton, controlButton, exitButton);
		
		mainPane.getChildren().addAll(mainMenuView, layout);
		
		startGameButton.setOnAction(e -> {
			gameEngine.startGame(1, stage, this);
			showIntro(stage);
		});
		selectLevelButton.setOnAction(e -> switchScene(selectLevelMenu())); //Open level selection menu
		controlButton.setOnAction(e -> switchScene(controlsMenu()));
		exitButton.setOnAction(e -> System.exit(0));
		
		Scene mainMenuScene = new Scene(mainPane, stage.getWidth(), stage.getHeight());
		
		mainMenuScene.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ESCAPE) {
				event.consume();
				stage.setFullScreen(true);
			}
		});
		
		return mainMenuScene;	
	}
	
Scene selectLevelMenu() { //Level selection menu
		
		StackPane mainPane = new StackPane();
		
		Image selectMenuImage = new Image("file:selectLevelPicture.png");
		ImageView selectMenuView = new ImageView(selectMenuImage);
		selectMenuView.fitWidthProperty().bind(stage.widthProperty());
		selectMenuView.fitHeightProperty().bind(stage.heightProperty());
		selectMenuView.setPreserveRatio(false);
		
		VBox mainLayout = new VBox(40);
		mainLayout.setAlignment(Pos.CENTER); //Align the buttons in the center of VBox
		
		Label menuTitle = new Label("CHOOSE LOCATION");
		
		VBox mapsLayout = new VBox(40);
		mapsLayout.setAlignment(Pos.CENTER);
		
		ImageView level1View = new ImageView(new Image("file:level1Picture.png"));
		ImageView level2View = new ImageView(new Image("file:level2Picture.png"));
		ImageView level3View = new ImageView(new Image("file:level3Picture.png"));

		ImageView[] mapViews = {level1View, level2View, level3View};
		for (ImageView view : mapViews) {
			view.fitWidthProperty().bind(stage.widthProperty().divide(8));
			view.fitHeightProperty().bind(stage.heightProperty().divide(7));
			view.setPreserveRatio(false);
		}
		
		Label label1 = new Label("1");
		Label label2 = new Label("2");
		Label label3 = new Label("3");
		
		Label[] levelLabels = {label1, label2, label3};
		
		StackPane easyButtonContent1 = new StackPane(level1View, label1);
		StackPane mediumButtonContent2 = new StackPane(level2View, label2);
		StackPane hardButtonContent3 = new StackPane(level3View, label3);
		
		Button levelEasyButton = new Button();
		Button levelMediumButton = new Button();
		Button levelHardButton = new Button();
		
		levelEasyButton.setGraphic(easyButtonContent1);
		levelMediumButton.setGraphic(mediumButtonContent2);
		levelHardButton.setGraphic(hardButtonContent3);
		
		Button[] mapButtons = {levelEasyButton, levelMediumButton, levelHardButton};
		for (Button btn : mapButtons) {
			btn.prefWidthProperty().bind(stage.widthProperty().divide(8));
			btn.prefHeightProperty().bind(stage.heightProperty().divide(7));
		}
		
		level1View.fitWidthProperty().bind(levelEasyButton.prefWidthProperty());
		level1View.fitHeightProperty().bind(levelEasyButton.prefHeightProperty());
		
		level2View.fitWidthProperty().bind(levelMediumButton.prefWidthProperty());
		level2View.fitHeightProperty().bind(levelMediumButton.prefHeightProperty());
		
		level3View.fitWidthProperty().bind(levelHardButton.prefWidthProperty());
		level3View.fitHeightProperty().bind(levelHardButton.prefHeightProperty());
		
		for (ImageView view : mapViews) {
			view.setPreserveRatio(false);
		}
		
		String mapButtonStyle = "-fx-background-color: rgba(0, 0, 0, 0);"
	              + "-fx-border-color: #4dfa9a;"
	              + "-fx-border-width: 3px;"
	              + "-fx-padding: 0;"
	              + "-fx-background-radius: 0;"
	              + "-fx-border-radius: 0;";
		
		levelEasyButton.setStyle(mapButtonStyle);
		levelMediumButton.setStyle(mapButtonStyle);
		levelHardButton.setStyle(mapButtonStyle);
		
		for (Button btn : mapButtons) {
			btn.setOnMouseEntered(e -> {
				btn.setStyle(mapButtonStyle + "-fx-border-color: #ffffff; -fx-effect: dropshadow(gaussian, #4dfa9a, 15, 0.5, 0, 0);");
			});
			btn.setOnMouseExited(e -> {
				btn.setStyle(mapButtonStyle);
			});
		}

		Button returnMainMenuButton = new Button("Main Menu");
		returnMainMenuButton.setStyle(BUTTON_STYLE);
		returnMainMenuButton.prefWidthProperty().bind(stage.widthProperty().divide(4));	
		returnMainMenuButton.prefHeightProperty().bind(stage.heightProperty().divide(10));
		
		Label[] emptyLabels = {};
		
		applyButtonStyles(stage.getWidth(), returnMainMenuButton);
		applyLabelStyles(stage.getWidth(), menuTitle, emptyLabels, emptyLabels);
		
		double initialFontSize = Math.max(20, stage.getWidth() / 50) * 2.5;
		for (Label lbl : levelLabels) {
			lbl.setTextFill(Color.WHITE);
			lbl.setStyle("-fx-font-family: Creepster; -fx-font-weight: bold; -fx-font-size: " + initialFontSize + "px; "
					   + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 5, 0.0, 0, 0);");
		}
		
		stage.widthProperty().addListener((obs, oldVal, newVal) -> {
			applyButtonStyles(newVal.doubleValue(), returnMainMenuButton);
			applyLabelStyles(newVal.doubleValue(), menuTitle, emptyLabels, emptyLabels); 
			
			double dynamicFontSize = Math.max(20, newVal.doubleValue() / 50) * 2.5;
			for (Label lbl : levelLabels) {
				lbl.setStyle("-fx-font-family: Creepster; -fx-font-weight: bold; -fx-font-size: " + dynamicFontSize + "px; "
						   + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 5, 0.0, 0, 0);");
			}
		});
	    
	    mapsLayout.getChildren().addAll(levelEasyButton, levelMediumButton, levelHardButton);
	    mainLayout.getChildren().addAll(menuTitle, mapsLayout);
		
	    mainPane.getChildren().addAll(selectMenuView, mainLayout, returnMainMenuButton);
	    
		StackPane.setAlignment(returnMainMenuButton, Pos.BOTTOM_RIGHT); 
		StackPane.setMargin(returnMainMenuButton, new Insets(10)); 
		
		StackPane.setAlignment(menuTitle, Pos.TOP_CENTER);
		StackPane.setMargin(menuTitle, new Insets(130));
		
		returnMainMenuButton.setOnAction(e -> switchScene(mainMenu())); 
		levelEasyButton.setOnAction(e -> gameEngine.startGame(1, stage, this));
		levelMediumButton.setOnAction(e -> gameEngine.startGame(2, stage, this));
		levelHardButton.setOnAction(e -> gameEngine.startGame(3, stage, this));
		
		Scene selectLevelScene = new Scene(mainPane, stage.getWidth(), stage.getHeight());
		
		selectLevelScene.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ESCAPE) {
				switchScene(mainMenu());
			}
		});
		
		return selectLevelScene;
	}
	
	public Scene controlsMenu() {
	    StackPane mainPane = new StackPane();
	    mainPane.setStyle("-fx-background-color: #050b14;");
	    
	    VBox mainLayout = new VBox(60);
	    mainLayout.setAlignment(Pos.TOP_CENTER);
	    mainLayout.setPadding(new Insets(80, 0, 0, 0));
	    
	    Label menuTitle = new Label("CONTROLS");
	    
	    HBox directionLayouts = new HBox(100);
	    directionLayouts.setAlignment(Pos.CENTER);
	    
	    GridPane wasdGrid = new GridPane();
	    wasdGrid.setHgap(10);
	    wasdGrid.setVgap(10);
	    wasdGrid.setAlignment(Pos.CENTER);
	    
	    Rectangle w = new Rectangle();
	    Rectangle a = new Rectangle();
	    Rectangle s = new Rectangle();
	    Rectangle d = new Rectangle();
	    
	    Label labelW = new Label("W");
	    Label labelA = new Label("A");
	    Label labelS = new Label("S");
	    Label labelD = new Label("D");
	    
	    Label descW = new Label("Up");
	    Label descA = new Label("Left");
	    Label descS = new Label("Down");
	    Label descD = new Label("Right");
	    
	    GridPane arrowGrid = new GridPane();
	    arrowGrid.setHgap(10);
	    arrowGrid.setVgap(10);
	    arrowGrid.setAlignment(Pos.CENTER);
	    
	    Rectangle upArrow = new Rectangle();
	    Rectangle leftArrow = new Rectangle();
	    Rectangle downArrow = new Rectangle();
	    Rectangle rightArrow = new Rectangle();
	    
	    Label labelUp = new Label("▲");
	    Label labelLeft = new Label("◀");
	    Label labelDown = new Label("▼");
	    Label labelRight = new Label("▶");
	    
	    Label descUp = new Label("Up");
	    Label descLeft = new Label("Left");
	    Label descDown = new Label("Down");
	    Label descRight = new Label("Right");
	    
	    VBox spaceLayout = new VBox(10);
	    spaceLayout.setAlignment(Pos.CENTER);
	    
	    Label descSpace = new Label("Vacuum");
	    
	    Rectangle space = new Rectangle();
	    Label labelSpace = new Label("SPACE");
	    
	    StackPane keySpace = new StackPane(space, labelSpace);
	    spaceLayout.getChildren().addAll(descSpace, keySpace);
	    
	    Rectangle esc = new Rectangle();
	    Rectangle g = new Rectangle();
	    Rectangle b = new Rectangle();
	    Rectangle r = new Rectangle();
	    
	    Label labelEsc = new Label("ESC");
	    Label labelG = new Label("G");
	    Label labelB = new Label("B");
	    Label labelR = new Label("R");
	    
	    Label descEsc = new Label("Stop Game");
	    Label descG = new Label("Visibility Cheat");
	    Label descB = new Label("Border Cheat");
	    Label descR = new Label("Put Trap");
	    
	    Rectangle[] allRectangles = {w, a, s, d, upArrow, leftArrow, downArrow, rightArrow, space, esc, g, b, r};
	    for (Rectangle rect: allRectangles) {
	        rect.widthProperty().bind(stage.widthProperty().divide(24));
	        rect.heightProperty().bind(stage.widthProperty().divide(24));
	        rect.setStyle(RECTANGLE_STYLE);
	    }
	    
	    space.widthProperty().bind(stage.widthProperty().divide(6));
	    space.heightProperty().bind(stage.widthProperty().divide(24));
	    space.setStyle(RECTANGLE_STYLE);
	    
	    esc.widthProperty().bind(stage.widthProperty().divide(16));
	    
	    Label[] allLabels = {descW, descA, descS, descD, descUp, descLeft, descDown, descRight};
	    for (Label desc: allLabels) {
	        GridPane.setHalignment(desc, HPos.CENTER);
	    }
	    
	    Label[] keyLabels = {labelW, labelA, labelS, labelD, labelUp, labelLeft, labelDown, labelRight, labelSpace, labelEsc, labelG, labelB, labelR};
	    Label[] descLabels = {descW, descA, descS, descD, descUp, descLeft, descDown, descRight, descSpace, descEsc, descG, descB, descR};

	    applyLabelStyles(stage.getWidth(), menuTitle, keyLabels, descLabels);
	    
	    stage.widthProperty().addListener((obs, oldVal, newVal) -> {
	        applyLabelStyles(newVal.doubleValue(), menuTitle, keyLabels, descLabels);
	    });
	    
	    StackPane keyW = new StackPane(w, labelW);
	    StackPane keyA = new StackPane(a, labelA);
	    StackPane keyS = new StackPane(s, labelS);
	    StackPane keyD = new StackPane(d, labelD);
	    
	    StackPane keyUp = new StackPane(upArrow, labelUp);
	    StackPane keyLeft = new StackPane(leftArrow, labelLeft);
	    StackPane keyDown = new StackPane(downArrow, labelDown);
	    StackPane keyRight = new StackPane(rightArrow, labelRight);
	    
	    StackPane keyEsc = new StackPane(esc, labelEsc);
	    StackPane keyG = new StackPane(g, labelG);
	    StackPane keyB = new StackPane(b, labelB);
	    StackPane keyR = new StackPane(r, labelR);
	    
	    wasdGrid.add(descW, 2, 0);
	    wasdGrid.add(descA, 0, 2);
	    wasdGrid.add(descS, 2, 3);
	    wasdGrid.add(descD, 4, 2);
	    wasdGrid.add(keyW, 2, 1);
	    wasdGrid.add(keyA, 1, 2);
	    wasdGrid.add(keyS, 2, 2);
	    wasdGrid.add(keyD, 3, 2);
	    
	    arrowGrid.add(descUp, 2, 0);
	    arrowGrid.add(descLeft, 0, 2);
	    arrowGrid.add(descDown, 2, 3);
	    arrowGrid.add(descRight, 4, 2);
	    arrowGrid.add(keyUp, 2, 1);
	    arrowGrid.add(keyLeft, 1, 2);
	    arrowGrid.add(keyDown, 2, 2);
	    arrowGrid.add(keyRight, 3, 2);
	    
	    directionLayouts.getChildren().addAll(wasdGrid, spaceLayout, arrowGrid);
	    
	    GridPane extraKeysGrid = new GridPane();
	    extraKeysGrid.setHgap(30);
	    extraKeysGrid.setVgap(15);
	    extraKeysGrid.setAlignment(Pos.CENTER);
	    
	    extraKeysGrid.add(keyEsc, 0, 0);
	    extraKeysGrid.add(descEsc, 1, 0);
	    extraKeysGrid.add(keyG, 0, 1);
	    extraKeysGrid.add(descG, 1, 1);
	    
	    extraKeysGrid.add(keyB, 4, 0);
	    extraKeysGrid.add(descB, 5, 0);
	    extraKeysGrid.add(keyR, 4, 1);
	    extraKeysGrid.add(descR, 5, 1);
	    
	    VBox.setMargin(extraKeysGrid, new Insets(80, 0, 0, 0));
	    
	    Button returnMainMenuButton = new Button("Main Menu");
	    returnMainMenuButton.setStyle(BUTTON_STYLE);
	    returnMainMenuButton.prefWidthProperty().bind(stage.widthProperty().divide(4));    
	    returnMainMenuButton.prefHeightProperty().bind(stage.heightProperty().divide(10));
	    
	    applyButtonStyles(stage.getWidth(), returnMainMenuButton);
	    stage.widthProperty().addListener((obs, oldVal, newVal) -> {
	        applyButtonStyles(newVal.doubleValue(), returnMainMenuButton);
	    });
	    returnMainMenuButton.setOnAction(e -> switchScene(mainMenu()));
	    
	    mainLayout.getChildren().addAll(menuTitle, directionLayouts, extraKeysGrid);
	    mainPane.getChildren().addAll(mainLayout, returnMainMenuButton);
	    
	    StackPane.setAlignment(returnMainMenuButton, Pos.BOTTOM_RIGHT);
	    StackPane.setMargin(returnMainMenuButton, new Insets(0, 40, 40, 0));

	    Scene controlsScene = new Scene(mainPane, stage.getWidth(), stage.getHeight());
		
		controlsScene.setOnKeyPressed(event -> {
			if (event.getCode() == javafx.scene.input.KeyCode.ESCAPE) {
				switchScene(mainMenu());
			}
		});
		
		return controlsScene;       
	}

	public VBox stopGamePanel() {
	    VBox overlay = new VBox();
	    overlay.setAlignment(Pos.CENTER);
	    overlay.setStyle("-fx-background-color: rgba(5, 15, 10, 0.8);");
	    overlay.prefWidthProperty().bind(stage.widthProperty());
	    overlay.prefHeightProperty().bind(stage.heightProperty());

	    VBox box = new VBox(25);
	    box.setMaxSize(600, 450);
	    box.setAlignment(Pos.CENTER);
	    box.setStyle("-fx-background-color: rgba(10, 30, 20, 0.95); " +
	                 "-fx-border-color: #4dfa9a; " +
	                 "-fx-border-width: 4px;");
	    box.setPadding(new Insets(40));

	    Label pauseText = new Label("GAME PAUSED");
	    pauseText.setTextFill(Color.web("#4dfa9a"));
	    pauseText.setStyle("-fx-font-family: Creepster; -fx-font-size: 50px; -fx-font-weight: bold;");

	    Button resumeButton = new Button("RESUME");
	    Button returnMainMenuButton = new Button("MAIN MENU");

	    resumeButton.setStyle(BUTTON_STYLE);
	    returnMainMenuButton.setStyle(BUTTON_STYLE);
	    
	    resumeButton.setPrefSize(280, 60);
	    returnMainMenuButton.setPrefSize(280, 60);

	    applyButtonStyles(stage.getWidth(), resumeButton, returnMainMenuButton);

	    stage.widthProperty().addListener((obs, oldVal, newVal) -> {
	        applyButtonStyles(newVal.doubleValue(), resumeButton, returnMainMenuButton);
	    });

	    resumeButton.setOnAction(e -> { 
	        gameEngine.resumeGame(); 
	    });
	    
	    returnMainMenuButton.setOnAction(e -> {
	        switchScene(mainMenu());
	    });

	    box.getChildren().addAll(pauseText, resumeButton, returnMainMenuButton);
	    overlay.getChildren().add(box);

	    return overlay;
	}
	
	public VBox winPanel(int finalScore) {

	    VBox overlay = new VBox();
	    overlay.setAlignment(Pos.CENTER);
	    overlay.setStyle("-fx-background-color: rgba(5, 15, 10, 0.8);");
	    overlay.prefWidthProperty().bind(stage.widthProperty());
	    overlay.prefHeightProperty().bind(stage.heightProperty());

	    VBox box = new VBox(25);
	    box.setMaxSize(600, 650);
	    box.setAlignment(Pos.CENTER);
	    box.setStyle("-fx-background-color: rgba(10, 30, 20, 0.95); " +
	                 "-fx-border-color: #4dfa9a; " +
	                 "-fx-border-width: 4px;");
	    box.setPadding(new Insets(40));
	    
	    String winMessage = "ALL GHOSTS VACUUMED";
	    if (gameEngine.getCurrentLevel() == 3) {
	    	winMessage = "HUNT COMPLETED";
	    }

	    Label winText = new Label(winMessage);
	    winText.setTextFill(Color.web("#4dfa9a"));
	    winText.setStyle("-fx-font-family: Creepster; " +
                "-fx-font-size: 50px; " + 
                "-fx-font-weight: bold;");
	    
	    Label scoreText = new Label("FINAL SCORE: " + finalScore);
	    scoreText.setTextFill(Color.web("#4dfa9a"));
	    scoreText.setStyle("-fx-font-family: Creepster; -fx-font-size: 32px; -fx-font-weight: bold;");
	    scoreText.setCache(false);

	    Button nextlevelButton = new Button("NEXT HUNT");
	    Button returnMainMenuButton = new Button("MAIN MENU");

	    nextlevelButton.setStyle(BUTTON_STYLE);
	    returnMainMenuButton.setStyle(BUTTON_STYLE);
	    
	    nextlevelButton.setPrefSize(280, 60);
	    returnMainMenuButton.setPrefSize(280, 60);

	    applyButtonStyles(stage.getWidth(), nextlevelButton, returnMainMenuButton);

	    nextlevelButton.setOnAction(e -> {
	    	int currentLevel = gameEngine.getCurrentLevel() + 1;
	    	
	    	if (currentLevel <= 3) {
	    		gameEngine.startGame(currentLevel, stage, this);
	    	}
	    	else {
	    		switchScene(mainMenu());
	    	}	
	    });
	    returnMainMenuButton.setOnAction(e -> {
	    	switchScene(mainMenu());
	        stage.setFullScreen(true);
	    });
	    
	    box.getChildren().addAll(winText, scoreText);

	    if (gameEngine.getCurrentLevel() < 3) {
	        box.getChildren().add(nextlevelButton);
	    }
	    
	    box.getChildren().add(returnMainMenuButton);
	    
	    overlay.getChildren().add(box);

	    return overlay;
	}
	
	public VBox losePanel(int reason, int finalScore) {
	    VBox overlay = new VBox();
	    overlay.setAlignment(Pos.CENTER);
	    overlay.setStyle("-fx-background-color: rgba(15, 5, 5, 0.8);");
	    overlay.prefWidthProperty().bind(stage.widthProperty());
	    overlay.prefHeightProperty().bind(stage.heightProperty());

	    VBox box = new VBox(25);
	    box.setMaxSize(600, 600);
	    box.setAlignment(Pos.CENTER);
	    box.setStyle("-fx-background-color: rgba(20, 10, 10, 0.95); " +
	                 "-fx-border-color: #ff4d4d; " + 
	                 "-fx-border-width: 4px;" +
	                 "-fx-border-radius: 0;");
	    box.setPadding(new Insets(40));
	    
	    String deathMessage = "FAIL";
	    
	    if (reason == 1) {
	    	deathMessage = "Hunter Destroyed";
	    }
	    else if (reason == 2){
	    	deathMessage = "Time Expired";
	    }

	    Label gameOverMessage = new Label("Game Over");
	    gameOverMessage.setFont(Font.font("Creepster", 45));
	    gameOverMessage.setTextFill(Color.web("#ff4d4d"));
	    
	    gameOverMessage.setStyle("-fx-font-family: Creepster; " +
                "-fx-font-size: 50px; " + 
                "-fx-font-weight: bold;");
	    
	    Label loseText = new Label(deathMessage);
	    loseText.setFont(Font.font("Creepster", 45));
	    loseText.setTextFill(Color.web("#ff4d4d"));

	    loseText.setStyle("-fx-font-family: Creepster; " +
                "-fx-font-size: 50px; " + 
                "-fx-font-weight: bold;");
	    
	    Label scoreText = new Label("FINAL SCORE: " + finalScore);
	    scoreText.setTextFill(Color.web("#ff4d4d"));
	    scoreText.setStyle("-fx-font-family: Creepster; -fx-font-size: 32px; -fx-font-weight: bold;");
	    scoreText.setCache(false);

	    Button retryButton = new Button("TRY AGAIN");
	    Button returnMainMenuButton = new Button("RETURN MENU");

	    double validWidth = Double.isNaN(stage.getWidth()) ? 800 : stage.getWidth(); 
	    double fontSize = Math.max(12, validWidth / 45);

	    String redButtonStyle = "-fx-background-color: rgba(30, 10, 10, 0.8);"
	                          + "-fx-border-color: #ff4d4d;"
	                          + "-fx-border-width: 2px;"
	                          + "-fx-text-fill: #ff4d4d;"
	                          + "-fx-font-family: 'Creepster';"
	                          + "-fx-font-weight: bold;"
	                          + "-fx-background-radius: 0;"
	                          + "-fx-border-radius: 0;"
	                          + "-fx-font-size: " + fontSize + "px;";

	    retryButton.setStyle(redButtonStyle);
	    returnMainMenuButton.setStyle(redButtonStyle);
	    
	    retryButton.setPrefSize(280, 60);
	    returnMainMenuButton.setPrefSize(280, 60);

	    Button[] redButtons = {retryButton, returnMainMenuButton};
	    for (Button btn : redButtons) {
	        btn.setOnMouseEntered(e -> btn.setStyle(redButtonStyle + "-fx-background-color: #ff4d4d; -fx-text-fill: black;"));
	        btn.setOnMouseExited(e -> btn.setStyle(redButtonStyle));
	    }

	    retryButton.setOnAction(e -> gameEngine.startGame(gameEngine.getCurrentLevel(), stage, this));
	    returnMainMenuButton.setOnAction(e -> {
	        switchScene(mainMenu());
	        stage.setFullScreen(true);
	    });

	    box.getChildren().addAll(gameOverMessage, loseText, scoreText, retryButton, returnMainMenuButton);
	    overlay.getChildren().add(box);

	    return overlay;
	}
	
	public void showIntro(Stage stage) {

	    String[] introImages = {
	        "file:intro1.png", 
	        "file:intro2.png", 
	        "file:intro3.png", 
	        "file:intro4.png", 
	        "file:intro5.png"
	    };

	    StackPane introRoot = new StackPane();
	    introRoot.setStyle("-fx-background-color: black;");
	    
	    ImageView imageView = new ImageView();
	    imageView.setPreserveRatio(true);
	    imageView.fitWidthProperty().bind(stage.widthProperty());
	    imageView.fitHeightProperty().bind(stage.heightProperty());
	    
	    introRoot.getChildren().add(imageView);
	    Scene introScene = new Scene(introRoot, stage.getWidth(), stage.getHeight());
	    switchScene(introScene);

	    playImageSequence(0, introImages, imageView, stage);
	}

	private void playImageSequence(int index, String[] images, ImageView imageView, Stage stage) {
		
	    if (index >= images.length) {
	        GameEngine game = new GameEngine();
	        game.startGame(1, stage, this);
	        return;
	    }

	    Image img = new Image(images[index]);
	    imageView.setImage(img);
	    imageView.setOpacity(0);

	    FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), imageView);
	    fadeIn.setFromValue(0);
	    fadeIn.setToValue(1);

	    PauseTransition stayVisible = new PauseTransition(Duration.seconds(2.5));

	    FadeTransition fadeOut = new FadeTransition(Duration.seconds(1.5), imageView);
	    fadeOut.setFromValue(1);
	    fadeOut.setToValue(0);

	    SequentialTransition sequence = new SequentialTransition(fadeIn, stayVisible, fadeOut);
	    
	    sequence.setOnFinished(e -> {
	        playImageSequence(index + 1, images, imageView, stage);
	    });

	    sequence.play();

	    imageView.getScene().setOnKeyPressed(event -> {
	        if (event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.ESCAPE) {
	            sequence.stop();
	            GameEngine game = new GameEngine();
	            game.startGame(1, stage, this);
	        }
	    });
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
	
	private void applyLabelStyles(double width, Label menuTitle, Label[] keyLabels, Label[] descLabels) {

	    double validWidth = (Double.isNaN(width) || width <= 0) ? 1024 : width; 
	    
	    double keyFontSize = Math.max(20, validWidth / 50);
	    double descFontSize = Math.max(20, validWidth / 50);
	    double titleFontSize = Math.max(32, validWidth / 30);

	    for (Label lbl : keyLabels) {
	        lbl.setTextFill(Color.web("#4dfa9a"));
	        lbl.setStyle("-fx-font-family: Creepster; -fx-font-weight: bold; -fx-font-size: " + keyFontSize + "px;");
	    }

	    for (Label desc : descLabels) {
	        desc.setTextFill(Color.WHITE); 
	        desc.setStyle("-fx-font-family: Creepster; -fx-font-weight: bold; -fx-font-size: " + descFontSize + "px;");
	    }
	    
	    menuTitle.setTextFill(Color.web("#4dfa9a"));
	    menuTitle.setStyle("-fx-font-family: Creepster; -fx-font-weight: bold; -fx-font-size: " + titleFontSize + "px;");
	}
	
	private void switchScene(Scene scene) {
	    stage.setScene(scene);
	    stage.setFullScreen(true);
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}

}