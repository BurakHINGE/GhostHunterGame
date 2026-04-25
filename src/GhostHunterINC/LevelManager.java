package GhostHunterINC;

public class LevelManager {
	ConfigManager config = new ConfigManager();
	
	void loadEntities(int level) {
		int ghost,wisp,ripper;
		
		//Get the number of entities to load to our current level.
		ghost = config.getGhostPoints();
		wisp = config.getWispPoints();
		ripper = config.getRipperPoints();
		
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
	
	void spawnToken() {
		int time = config.getTokenSpawnInterval();
		
		//Check if there is less token than the number of maximum token allowed.
		//If so check if the interval has passed since the last token spawned.
		//If both of these are true spawn a random token.
	}
}
