package GhostHunterINC;

import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class LevelManager {
	Pane enemies;
	ArrayList<GameObject> allEntities;
	private Timeline tokenTimeline;
	
	LevelManager(Pane enemies, ArrayList allEntities){
		this.enemies = enemies;
		this.allEntities = allEntities;
	}

	void loadEntities(int level) {
		int ghost = 0,wisp = 0,ripper = 0;
		
		//Get the number of entities to load to our current level.
		switch (level){
		case 1:
			ghost = ConfigManager.getLevel1Ghosts();
			wisp = ConfigManager.getLevel1Wisps();
			ripper = ConfigManager.getLevel1Rippers();
			
			//Load the entities.
			for(int i = 0; i < ghost ; i++) {
				//Load the ghosts
				double randX = Math.random() * 800 + 200;
				double randY = Math.random() * 400 + 200;
				
				Ghost newGhost = new Ghost(randX, randY,ConfigManager.level1PlayableAreaX, ConfigManager.level1PlayableAreaY, ConfigManager.level1PlayableAreaWidth, ConfigManager.level1PlayableAreaHeight);
				newGhost.getShape().setVisible(false);
				allEntities.add(newGhost);
				enemies.getChildren().add(newGhost.getShape());
			}
			
			for(int i = 0; i < ripper ; i++) {
				//Load the rippers
				double randX = Math.random() * 800 + 200;
				double randY = Math.random() * 400 + 200;
				
				Ripper newRipper = new Ripper(randX, randY,ConfigManager.level1PlayableAreaX, ConfigManager.level1PlayableAreaY, ConfigManager.level1PlayableAreaWidth, ConfigManager.level1PlayableAreaHeight);
				newRipper.getShape().setVisible(false);
				allEntities.add(newRipper);
				enemies.getChildren().add(newRipper.getShape());
			}
			
			for(int i = 0; i < wisp ; i++) {
				//Load the wisps
				double randX = Math.random() * 800 + 200;
				double randY = Math.random() * 400 + 200;
				
				Wisp newWisp = new Wisp(randX, randY,ConfigManager.level1PlayableAreaX, ConfigManager.level1PlayableAreaY, ConfigManager.level1PlayableAreaWidth, ConfigManager.level1PlayableAreaHeight);
				newWisp.getShape().setVisible(false);
				allEntities.add(newWisp);
				enemies.getChildren().add(newWisp.getShape());
			}
			
			break;
		case 2:
			ghost = ConfigManager.getLevel2Ghosts();
			wisp = ConfigManager.getLevel2Wisps();
			ripper = ConfigManager.getLevel2Rippers();
			
			//Load the entities.
			for(int i = 0; i < ghost ; i++) {
				//Load the ghosts
				double randX = Math.random() * 800 + 200;
				double randY = Math.random() * 400 + 200;
				
				Ghost newGhost = new Ghost(randX, randY,ConfigManager.level2PlayableAreaX, ConfigManager.level2PlayableAreaY, ConfigManager.level2PlayableAreaWidth, ConfigManager.level2PlayableAreaHeight);
				newGhost.getShape().setVisible(false);
				allEntities.add(newGhost);
				enemies.getChildren().add(newGhost.getShape());
			}
			
			for(int i = 0; i < ripper ; i++) {
				//Load the rippers
				double randX = Math.random() * 800 + 200;
				double randY = Math.random() * 400 + 200;
				
				Ripper newRipper = new Ripper(randX, randY,ConfigManager.level2PlayableAreaX, ConfigManager.level2PlayableAreaY, ConfigManager.level2PlayableAreaWidth, ConfigManager.level2PlayableAreaHeight);
				newRipper.getShape().setVisible(false);
				allEntities.add(newRipper);
				enemies.getChildren().add(newRipper.getShape());
			}
			
			for(int i = 0; i < wisp ; i++) {
				//Load the wisps
				double randX = Math.random() * 800 + 200;
				double randY = Math.random() * 400 + 200;
				
				Wisp newWisp = new Wisp(randX, randY,ConfigManager.level2PlayableAreaX, ConfigManager.level2PlayableAreaY, ConfigManager.level2PlayableAreaWidth, ConfigManager.level2PlayableAreaHeight);
				newWisp.getShape().setVisible(false);
				allEntities.add(newWisp);
				enemies.getChildren().add(newWisp.getShape());
			}
			
			break;
		case 3:
			ghost = ConfigManager.getLevel3Ghosts();
			wisp = ConfigManager.getLevel3Wisps();
			ripper = ConfigManager.getLevel3Rippers();
			
			//Load the entities.
			for(int i = 0; i < ghost ; i++) {
				//Load the ghosts
				double randX = Math.random() * 800 + 200;
				double randY = Math.random() * 400 + 200;
				
				Ghost newGhost = new Ghost(randX, randY,ConfigManager.level3PlayableAreaX, ConfigManager.level3PlayableAreaY, ConfigManager.level3PlayableAreaWidth, ConfigManager.level3PlayableAreaHeight);
				newGhost.getShape().setVisible(false);
				allEntities.add(newGhost);
				enemies.getChildren().add(newGhost.getShape());
			}
			
			for(int i = 0; i < ripper ; i++) {
				//Load the rippers
				double randX = Math.random() * 800 + 200;
				double randY = Math.random() * 400 + 200;
				
				Ripper newRipper = new Ripper(randX, randY,ConfigManager.level3PlayableAreaX, ConfigManager.level3PlayableAreaY, ConfigManager.level3PlayableAreaWidth, ConfigManager.level3PlayableAreaHeight);
				newRipper.getShape().setVisible(false);
				allEntities.add(newRipper);
				enemies.getChildren().add(newRipper.getShape());
			}
			
			for(int i = 0; i < wisp ; i++) {
				//Load the wisps
				double randX = Math.random() * 800 + 200;
				double randY = Math.random() * 400 + 200;
				
				Wisp newWisp = new Wisp(randX, randY,ConfigManager.level3PlayableAreaX, ConfigManager.level3PlayableAreaY, ConfigManager.level3PlayableAreaWidth, ConfigManager.level3PlayableAreaHeight);
				newWisp.getShape().setVisible(false);
				allEntities.add(newWisp);
				enemies.getChildren().add(newWisp.getShape());
			}
			
			break;
	}
			
		
	}
	
	void startTokenSpawn(int level) {
		//The event thats gonna start on every 5 seconds.
		KeyFrame frame = new KeyFrame(Duration.seconds(5), e -> {
			int activeToken = 0;
			
			//Check token number on the screen
			for(GameObject o: allEntities) {
				if(o instanceof Token) {
					activeToken++;
				}
			}
			
			if(activeToken < 2) {
				spawnToken(level);
			}
		});
		
		//Create the timeline and make it cycle infinitely.
		tokenTimeline = new Timeline(frame);
		tokenTimeline.setCycleCount(Timeline.INDEFINITE);
		tokenTimeline.play();
		
		
		//Check if there is less token than the number of maximum token allowed.
		//If so check if the interval has passed since the last token spawned.
		//If both of these are true spawn a random token.
	}
	
	//Stop token generation when the game end or the player has gone to the next level.
	void stopTokenSpawn() {
		if(tokenTimeline != null) {
			tokenTimeline.stop();
		}
	}
	
	void pauseTokenSpawn() {
		if(tokenTimeline != null) {
			tokenTimeline.pause();
		}
	}
	
	void resumeTokenSpawn() {
		if(tokenTimeline != null) {
			tokenTimeline.play();
		}
	}
	
	void spawnToken(int level) {
		double randX = 0, randY = 0;
		Token token = null;
		
		switch (level) {
			case 1:
				randX = Math.random() * (ConfigManager.level1PlayableAreaWidth - 30) + ConfigManager.level1PlayableAreaX;
				randY = Math.random() * (ConfigManager.level1PlayableAreaHeight - 30) + ConfigManager.level1PlayableAreaY;
				break;
			case 2:
				randX = Math.random() * (ConfigManager.level2PlayableAreaWidth - 30) + ConfigManager.level2PlayableAreaX;
				randY = Math.random() * (ConfigManager.level2PlayableAreaHeight - 30) + ConfigManager.level2PlayableAreaY;
				break;
			case 3:
				randX = Math.random() * (ConfigManager.level3PlayableAreaWidth - 30) + ConfigManager.level3PlayableAreaX;
				randY = Math.random() * (ConfigManager.level3PlayableAreaHeight - 30) + ConfigManager.level3PlayableAreaY;
				break;
		}
		
		//Choose a random token type to generate.
		int tokenType = (int)(Math.random() * 3) + 1;
		
		switch(tokenType) {
			case 1:
				token = new HealthToken(randX, randY);
				break;
			case 2:
				token = new EyeToken(randX, randY, allEntities);
				break;
			case 3:
				token = new RangeToken(randX, randY);
				break;
		}
		
		//Add the token to our Pane and ArrayList.
		if(token != null) {
			allEntities.add(token);
			enemies.getChildren().add(token.getShape());
		}
		
	}
}

