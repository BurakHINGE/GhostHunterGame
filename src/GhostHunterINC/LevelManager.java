package GhostHunterINC;

import javafx.scene.text.Text;

public class LevelManager {
	

	void loadEntities(int level) {
		int ghost = 0,wisp = 0,ripper = 0;
		
		//Get the number of entities to load to our current level.
		switch (level){
		case 1:
			ghost = ConfigManager.getLevel1Ghosts();
			wisp = ConfigManager.getLevel1Wisps();
			ripper = ConfigManager.getLevel1Rippers();
		case 2:
			ghost = ConfigManager.getLevel2Ghosts();
			wisp = ConfigManager.getLevel2Wisps();
			ripper = ConfigManager.getLevel2Rippers();
		case 3:
			ghost = ConfigManager.getLevel3Ghosts();
			wisp = ConfigManager.getLevel3Wisps();
			ripper = ConfigManager.getLevel3Rippers();
	}
		
		//Load the entities.
		for(int i = 0; i < ghost ; i++) {
			//Load the ghosts
		}
		
		for(int i = 0; i < ripper ; i++) {
			//Load the rippers
		}
		
		for(int i = 0; i < wisp ; i++) {
			//Load the wisps
		}
			
		
	}
	
	void spawnToken(int level) {
		int time;
		
		if (level == 1) {
			time = ConfigManager.getLevel1Time();
		}
		else if (level == 2) {
			time = ConfigManager.getLevel2Time();
		}
		else if (level == 3) {
			time = ConfigManager.getLevel3Time();
		}
		
		
		//Check if there is less token than the number of maximum token allowed.
		//If so check if the interval has passed since the last token spawned.
		//If both of these are true spawn a random token.
	}
}
