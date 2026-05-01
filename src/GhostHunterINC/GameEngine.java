package GhostHunterINC;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class GameEngine {
	
	private ArrayList<GameObject> enemies; //Create list for enemies
	private Pane gameObjects;
	private Scene gameScene;
	private Timeline gameLoop;
	private LevelManager levelManager;
	private Hunter hunter;
	
	//Direction keys
	private boolean isWPressed = false;
	private boolean isAPressed = false;
	private boolean isSPressed = false;
	private boolean isDPressed = false;
	
	
	public GameEngine (Pane gameObjects, Scene gameScene, LevelManager levelManager, Hunter hunter) {
		this.gameObjects = gameObjects;
		this.gameScene = gameScene;
		this.levelManager = levelManager;
		this.hunter = hunter;
		enemies = new ArrayList<>();
		
		handleInput();
	}
	
	public void startGame(int level) {
		
		enemies.clear(); //Clear all former enemies 
		gameObjects.getChildren().clear(); //Clear all former objects
		
		if (level == 1) {
			//Load level1
		}
		else if (level == 2) {
			//Load level2
		}
		else if (level == 3) {
			//Load level3
		}
		
		gameLoop.play();
 		
		
	}
	
	public void gameLoop() {
		
		gameLoop = new Timeline(new KeyFrame(Duration.millis(16), e -> {
			//Add hunter movements
		}));
		
		
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
	
	public void hunterMovement(int level) {
		
		double speed = 5.0;
		
		//Dynamic coordinates
		double newX; //Get info from hunter class
		double newY; //Get info from hunter class
		
		//Fixed coordinates
		double minX;
		double minY;
		double maxX;
		double maxY;
		
		
		//Set minX and minY according to configManager
		if (level == 1) {
			minX = ConfigManager.getLevel1PlayableAreaX();
			minY = ConfigManager.getLevel1PlayableAreaY();
		}
		else if (level == 2) {
			minX = ConfigManager.getLevel2PlayableAreaX();
			minY = ConfigManager.getLevel2PlayableAreaY();
		}
		else if (level == 3) {
			minX = ConfigManager.getLevel3PlayableAreaX();
			minY = ConfigManager.getLevel3PlayableAreaY();
		}
		
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
		
		double hunterWidth; //Get info from hunter class
		double hunterHeight; //Get info from hunter class
		
		//Check the borders
		if (newX < minX) { 
			newX = minX;
		}
		if (newX + hunterWidth < maxX) {
			newX = maxX - hunterWidth;
		}
		if (newY < minY) {
			newY = minY;
		}
		if (newY + hunterHeight < maxY) {
			newY = maxY - hunterHeight;
		}
		
		//Set coordinates for hunter
		
	}
	
	public void stopGame() { //Stop the game
		gameLoop.stop();
	}
}
