package GhostHunterINC;

import java.util.ArrayList;

import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class GameEngine {
	
	private ArrayList<ParanormalEntity> enemies; //Create list for enemies
	private Pane gameObjects;
	private Scene gameScene;
	private Timeline gameLoop;
	private LevelManager levelManager;
	
	//Direction keys
	private boolean isWPressed = false;
	private boolean isAPressed = false;
	private boolean isSPressed = false;
	private boolean isDPressed = false;
	
	
	public GameEngine (Pane gameObjects, Scene gameScene, LevelManager levelManager) {
		this.gameObjects = gameObjects;
		this.gameScene = gameScene;
		this.levelManager = levelManager;
		enemies = new ArrayList<>();
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
 		
		
	}
	
	public void gameLoop() {
		
		
		
		
	}
	
	public void handleInput() {
		
		gameScene.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			
			}
			
			
		});
		
		
	}
	
	public void stopGame() {
		gameLoop.stop();
	}
}
