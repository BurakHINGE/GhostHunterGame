package GhostHunterINC;

import java.util.ArrayList;
import javafx.scene.layout.Pane;

public class GameEngine {
	
	private ArrayList<ParanormalEntity> enemies;
	private Pane gameObjects;
	
	
	public GameEngine (Pane gameObjects) {
		this.gameObjects = gameObjects;
		enemies = new ArrayList<>();
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
