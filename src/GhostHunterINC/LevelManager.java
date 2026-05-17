package GhostHunterINC;

import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

// This class handles the spawning of enemies and tokens for every level.
public class LevelManager {
	Pane enemies;
	ArrayList<GameObject> allEntities;
	private Timeline tokenTimeline;
	double areaX, areaY, areaW, areaH, randX, randY;
	
	// Constructor gets 2 parameters;
	// 1- enemies : The pane where every visual we want to add on the screen added.
	// 2- allEntities : Main list that contains all active game objects.
	LevelManager(Pane enemies, ArrayList<GameObject> allEntities){
		this.enemies = enemies;
		this.allEntities = allEntities;
	}
	
	// Load paranormal entities with random coordinates to the screen based on current level.
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
				areaW = ConfigManager.level1PlayableAreaWidth;
			    areaH = ConfigManager.level1PlayableAreaHeight;
			    
			    randX = Math.random() * (areaW - 150) + 50; 
			    randY = Math.random() * (areaH - 150) + 50;
				
			    Ghost newGhost = new Ghost(randX, randY, 0, 0, areaW, areaH);
				newGhost.getShape().setVisible(false);
				allEntities.add(newGhost);
				enemies.getChildren().add(newGhost.getShape());
			}
			
			for(int i = 0; i < ripper ; i++) {
				//Load the rippers
				areaW = ConfigManager.level1PlayableAreaWidth;
			    areaH = ConfigManager.level1PlayableAreaHeight;
			    
			    randX = Math.random() * (areaW - 150) + 50; 
			    randY = Math.random() * (areaH - 150) + 50;
				
			    Ripper newRipper = new Ripper(randX, randY, 0, 0, areaW, areaH);
				newRipper.getShape().setVisible(false);
				allEntities.add(newRipper);
				enemies.getChildren().add(newRipper.getShape());
			}
			
			for(int i = 0; i < wisp ; i++) {
				//Load the wisps
				areaW = ConfigManager.level1PlayableAreaWidth;
			    areaH = ConfigManager.level1PlayableAreaHeight;
			    
			    randX = Math.random() * (areaW - 150) + 50; 
			    randY = Math.random() * (areaH - 150) + 50;
				
			    Wisp newWisp = new Wisp(randX, randY, 0, 0, areaW, areaH);
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
				areaW = ConfigManager.level2PlayableAreaWidth;
			    areaH = ConfigManager.level2PlayableAreaHeight;
			    
			    randX = Math.random() * (areaW - 150) + 50; 
			    randY = Math.random() * (areaH - 150) + 50;
				
			    Ghost newGhost = new Ghost(randX, randY, 0, 0, areaW, areaH);
				newGhost.getShape().setVisible(false);
				allEntities.add(newGhost);
				enemies.getChildren().add(newGhost.getShape());
			}
			
			for(int i = 0; i < ripper ; i++) {
				//Load the rippers
				areaW = ConfigManager.level2PlayableAreaWidth;
			    areaH = ConfigManager.level2PlayableAreaHeight;
			    
			    randX = Math.random() * (areaW - 150) + 50; 
			    randY = Math.random() * (areaH - 150) + 50;
				
			    Ripper newRipper = new Ripper(randX, randY, 0, 0, areaW, areaH);
				newRipper.getShape().setVisible(false);
				allEntities.add(newRipper);
				enemies.getChildren().add(newRipper.getShape());
			}
			
			for(int i = 0; i < wisp ; i++) {
				//Load the wisps
				areaW = ConfigManager.level2PlayableAreaWidth;
			    areaH = ConfigManager.level2PlayableAreaHeight;
			    
			    randX = Math.random() * (areaW - 150) + 50; 
			    randY = Math.random() * (areaH - 150) + 50;
				
			    Wisp newWisp = new Wisp(randX, randY, 0, 0, areaW, areaH);
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
				areaW = ConfigManager.level3PlayableAreaWidth;
			    areaH = ConfigManager.level3PlayableAreaHeight;
			    
			    randX = Math.random() * (areaW - 150) + 50; 
			    randY = Math.random() * (areaH - 150) + 50;
				
			    Ghost newGhost = new Ghost(randX, randY, 0, 0, areaW, areaH);
				newGhost.getShape().setVisible(false);
				allEntities.add(newGhost);
				enemies.getChildren().add(newGhost.getShape());
			}
			
			for(int i = 0; i < ripper ; i++) {
				//Load the rippers
				areaW = ConfigManager.level3PlayableAreaWidth;
			    areaH = ConfigManager.level3PlayableAreaHeight;
			    
			    randX = Math.random() * (areaW - 150) + 50; 
			    randY = Math.random() * (areaH - 150) + 50;
				
			    Ripper newRipper = new Ripper(randX, randY, 0, 0, areaW, areaH);
				newRipper.getShape().setVisible(false);
				allEntities.add(newRipper);
				enemies.getChildren().add(newRipper.getShape());
			}
			
			for(int i = 0; i < wisp ; i++) {
				//Load the wisps
				areaW = ConfigManager.level3PlayableAreaWidth;
			    areaH = ConfigManager.level3PlayableAreaHeight;
			    
			    randX = Math.random() * (areaW - 150) + 50; 
			    randY = Math.random() * (areaH - 150) + 50;
				
			    Wisp newWisp = new Wisp(randX, randY, 0, 0, areaW, areaH);
				newWisp.getShape().setVisible(false);
				allEntities.add(newWisp);
				enemies.getChildren().add(newWisp.getShape());
			}
			
			break;
	}
			
		
	}
	
	void startTokenSpawn(int level) {
		// The event thats gonna start on every 5 seconds.
		KeyFrame frame = new KeyFrame(Duration.seconds(5), e -> {
			int activeToken = 0;
			
			//Check token number on the screen
			for(GameObject o: allEntities) {
				if(o instanceof Token) {
					activeToken++;
				}
			}
			
			// Spawn new token if there are less than 2 token
			if(activeToken < 2) {
				spawnToken(level);
			}
		});
		
		//Create the timeline and make it cycle infinitely.
		tokenTimeline = new Timeline(frame);
		tokenTimeline.setCycleCount(Timeline.INDEFINITE);
		tokenTimeline.play();
	}
	
	//Stop token generation when the game end or the player has gone to the next level.
	void stopTokenSpawn() {
		if(tokenTimeline != null) {
			tokenTimeline.stop();
		}
	}
	
	// Pause token spawn when the game is paused.
	void pauseTokenSpawn() {
		if(tokenTimeline != null) {
			tokenTimeline.pause();
		}
	}
	
	// Resume token spawn
	void resumeTokenSpawn() {
		if(tokenTimeline != null) {
			tokenTimeline.play();
		}
	}
	
	// Create a random type of token with random coordinates.
	void spawnToken(int level) {
		double areaW, areaH;
		Token token = null;
		
		if(level == 1) { 
	        areaW = ConfigManager.getLevel1PlayableAreaWidth(); 
	        areaH = ConfigManager.getLevel1PlayableAreaHeight();
	        areaX = ConfigManager.getLevel1PlayableAreaX();
	        areaY = ConfigManager.getLevel1PlayableAreaY();
	    }
		else if (level == 2) {
			areaW = ConfigManager.getLevel2PlayableAreaWidth(); 
	        areaH = ConfigManager.getLevel2PlayableAreaHeight();
	        areaX = ConfigManager.getLevel2PlayableAreaX();
	        areaY = ConfigManager.getLevel2PlayableAreaY();
		}
		else {
			areaW = ConfigManager.getLevel3PlayableAreaWidth(); 
	        areaH = ConfigManager.getLevel3PlayableAreaHeight();
	        areaX = ConfigManager.getLevel3PlayableAreaX();
	        areaY = ConfigManager.getLevel3PlayableAreaY();
		}
		
		randX = Math.random() * (areaW - 40);
		randY = Math.random() * (areaH - 40);
		
		//Choose a random token type to generate.
		int tokenType = (int)(Math.random() * 4) + 1;
		
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
			case 4:
				token = new TrapToken(randX, randY);
				break;
		}
		
		//Add the token to our Pane and ArrayList.
		if(token != null) {
			allEntities.add(token);
			enemies.getChildren().add(token.getShape());
		}
		
	}
}

