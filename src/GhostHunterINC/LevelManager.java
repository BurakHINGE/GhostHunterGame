package GhostHunterINC;

public class LevelManager {
	ConfigManager config = new ConfigManager();
	
	void loadEntities(int level) {
		int ghost,wisp,ripper;
		
		//Get the number of entities to load to our current level.
		if (level == 1) {
			ghost = config.getLevel1Ghosts();
			wisp = config.getLevel1Wisps();
			ripper = config.getLevel1Rippers();
		}
		else if (level == 2) {
			ghost = config.getLevel2Ghosts();
			wisp = config.getLevel2Wisps();
			ripper = config.getLevel2Rippers();
		}
		else if (level == 3) {
			ghost = config.getLevel3Ghosts();
			wisp = config.getLevel3Wisps();
			ripper = config.getLevel3Rippers();
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
			time = config.getLevel1Time();
		}
		else if (level == 2) {
			time = config.getLevel2Time();
		}
		else if (level == 3) {
			time = config.getLevel3Time();
		}
		
		
		//Check if there is less token than the number of maximum token allowed.
		//If so check if the interval has passed since the last token spawned.
		//If both of these are true spawn a random token.
	}
}
