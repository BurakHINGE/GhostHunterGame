package GhostHunterINC;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameEngine {
	
	private ArrayList<GameObject> allEntities;
	private Pane gameObjects;
	private Scene gameScene;
	private Timeline gameLoop;
	private LevelManager levelManager;
	private Hunter hunter;
	private int currentLevel;
	private HUDManager hudManager;
	
	//Direction keys
	private boolean isWPressed = false;
	private boolean isAPressed = false;
	private boolean isSPressed = false;
	private boolean isDPressed = false;
	
	public GameEngine() {
		this.allEntities = new ArrayList<>();
	}
	
	public GameEngine (Pane gameObjects, Scene gameScene, LevelManager levelManager, Hunter hunter, ArrayList allEntities) {
		this.gameObjects = gameObjects;
		this.gameScene = gameScene;
		this.hunter = hunter;
		gameObjects = new Pane();
		allEntities = new ArrayList<>();
		levelManager = new LevelManager(gameObjects, allEntities);
		
		handleInput();
	}
	
	public void startGame(int level, Stage stage) {
		this.currentLevel = level;
		
		this.allEntities = new ArrayList<>();
		
		this.gameObjects = new Pane();
		
		double areaX;
		double areaY;
		double areaW;
		double areaH;
		
		//Get special area informations
		if (level == 1) {
			areaX = ConfigManager.getLevel1PlayableAreaX();
			areaY = ConfigManager.getLevel1PlayableAreaY();
			areaW = ConfigManager.getLevel1PlayableAreaWidth();
			areaH = ConfigManager.getLevel1PlayableAreaHeight();
		}
		else if (level == 2) {
			areaX = ConfigManager.getLevel2PlayableAreaX();
			areaY = ConfigManager.getLevel2PlayableAreaY();
			areaW = ConfigManager.getLevel2PlayableAreaWidth();
			areaH = ConfigManager.getLevel2PlayableAreaHeight();
		}
		else { //Level 3
			areaX = ConfigManager.getLevel3PlayableAreaX();
			areaY = ConfigManager.getLevel3PlayableAreaY();
			areaW = ConfigManager.getLevel3PlayableAreaWidth();
			areaH = ConfigManager.getLevel3PlayableAreaHeight();
		}
		
		//Set hunter's start position
		double startX = areaX + (areaW / 2);
		double startY = areaY + (areaH / 2);
		
		this.hunter = new Hunter(startX, startY, areaX, areaY, areaW, areaH);
		
		this.gameObjects.getChildren().add(this.hunter.getHunterShape());
		
		this.levelManager = new LevelManager(this.gameObjects, this.allEntities);
		this.hudManager = new HUDManager(level);
		
		this.levelManager.loadEntities(level);
		
		Pane levelPane = new Pane();
		levelPane.getChildren().addAll(this.gameObjects, this.hudManager.getHudPane());
		
		this.gameScene = new Scene(levelPane, 500, 500);
		
		handleInput();
		stage.setScene(this.gameScene);
		
		levelPane.requestFocus();
		
		startTimelineLoop();
	}
	
public void startTimelineLoop() {
		
		if (gameLoop != null) {
			gameLoop.stop();
		}
		
		gameLoop = new Timeline(new KeyFrame(Duration.millis(16), e -> {
			hunterMovement();
		}));

		gameLoop.setCycleCount(Timeline.INDEFINITE); 
		
		gameLoop.play(); 
	}
	
	public void handleInput() {
		
		gameScene.setOnKeyPressed(e -> { //Activate key when pressed
			switch (e.getCode()) {
				case W: {
					isWPressed = true;
					break;
				}
				case A: {
					isAPressed = true;
					break;
				}
				case S: {
					isSPressed = true;
					break;
				}
				case D: {
					isDPressed = true;
					break;
				}
				//Add cheat key
			}			
		});
		
		gameScene.setOnKeyReleased(e -> { //Deactivate key when pressed
			switch (e.getCode()) {
				case W: {
					isWPressed = false;
					break;
				}
				case A: {
					isAPressed = false;
					break;
				}
				case S: {
					isSPressed = false;
					break;
				}
				case D: {
					isDPressed = false;
					break;
				}
				//Add cheat key
			}			
		});
	}
	
	public void hunterMovement() {
		
		double speed = 5.0;
		
		//Dynamic coordinates
		double newX = hunter.getX();
		double newY = hunter.getY();
		
		//Movement
		if (isWPressed) {
			newY -= speed;
		}
		if (isAPressed) {
			newX -= speed;
		}
		if (isSPressed) {
			newY += speed;
		}
		if (isDPressed) {
			newX += speed;
		}
		
		//Fixed coordinates
		double minX = hunter.getAreaX();
		double minY = hunter.getAreaY();
		double maxX = hunter.getAreaX() + hunter.getAreaW();
		double maxY = hunter.getAreaY() + hunter.getAreaH();
		
		double hunterSize = 40.0;
		
		double hunterWidth; //Get info from hunter class
		double hunterHeight; //Get info from hunter class
		
		//Check the borders
		if (newX < minX) { 
			newX = minX;
		}
		if (newX + hunterSize < maxX) {
			newX = maxX - hunterSize;
		}
		if (newY < minY) {
			newY = minY;
		}
		if (newY + hunterSize < maxY) {
			newY = maxY - hunterSize;
		}
		
		//Set coordinates for hunter
		
	}
	
	public void stopGame() { //Stop the game
		gameLoop.stop();
	}

	public ArrayList<GameObject> getEnemies() {
		return allEntities;
	}

	public Pane getGameObjects() {
		return gameObjects;
	}

	public Scene getGameScene() {
		return gameScene;
	}

	public Timeline getGameLoop() {
		return gameLoop;
	}
	
	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setEnemies(ArrayList<GameObject> allEntities) {
		this.allEntities = allEntities;
	}

	public void setGameObjects(Pane gameObjects) {
		this.gameObjects = gameObjects;
	}

	public void setGameScene(Scene gameScene) {
		this.gameScene = gameScene;
	}

	public void setGameLoop(Timeline gameLoop) {
		this.gameLoop = gameLoop;
	}
	
	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}
}
