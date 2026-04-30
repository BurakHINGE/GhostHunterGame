package GhostHunterINC;

import java.util.ArrayList;
import javafx.scene.layout.Pane;

public class GameEngine {
	
	private ArrayList<ParanormalEntity> enemies = new ArrayList<>();
	private Pane gameObjects;
	private LevelManager levelManager;
	
	
	public GameEngine (Pane gameObjects, LevelManager levelManager) {
		this.gameObjects = gameObjects;
		this.levelManager = levelManager;
	}
	
	
	
	void startGame(int level) {
		
	}
	
	void gameLoop() {
		
	}
	
	void handleInput(KeyEvent event) {
		
	}
	
	void stopGame() {
		
	}
}
