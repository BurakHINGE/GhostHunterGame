package GhostHunterINC;

//The whole class is implemented by 150124045_Mehmet_Burak_Mentese.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConfigManager {
	
	//Hunter's variables
	public static int maxHealth;
	public static int maxVacuum;
	public static int entityDamage;
	public static int vacuumDecrease;
	public static int vacuumIncrease;
	
	//Level 1 variables
	public static int level1PlayableAreaX;
	public static int level1PlayableAreaY;
	public static int level1PlayableAreaWidth;
	public static int level1PlayableAreaHeight;
	public static int level1Time;
	public static int level1Ghosts;
	public static int level1Rippers;
	public static int level1Wisps;
	
	//Level 2 variables
	public static int level2PlayableAreaX;
	public static int level2PlayableAreaY;
	public static int level2PlayableAreaWidth;
	public static int level2PlayableAreaHeight;
	public static int level2Time;
	public static int level2Ghosts;
	public static int level2Rippers;
	public static int level2Wisps;
	
	//Level 3 variables
	public static int level3PlayableAreaX;
	public static int level3PlayableAreaY;
	public static int level3PlayableAreaWidth;
	public static int level3PlayableAreaHeight;
	public static int level3Time;
	public static int level3Ghosts;
	public static int level3Rippers;
	public static int level3Wisps;
	
	//Token variables
	public static int healthTokenIncrease;
	public static int rangeTokenIncrease;
	public static int eyeTokenDuration;
	public static int trapTokenDuration;
	public static int rangeTokenDuration;
	
	//Point variables
	public static int ghostPoints;
	public static int ripperPoints;
	public static int wispPoints;
	
	public static void loadConfig() { //Read configures from config.txt
		try {
			File conf = new File("config.txt"); //Check file path
			Scanner output = new Scanner(conf);
			
			while (output.hasNext()) { //Check is there any sentence
				String configName = output.next();
				
				//Read hunter's values
				if (configName.equals("maximum_health:")) {
					maxHealth = output.nextInt();
				}
				else if (configName.equals("maximum_vacuum:")) {
					maxVacuum = output.nextInt();
				}
				else if (configName.equals("entity_damage:")) {
					entityDamage = output.nextInt();
				}
				else if (configName.equals("vacuum_decrease:")) {
					vacuumDecrease = output.nextInt();
				}
				else if (configName.equals("vacuum_increase:")) {
					vacuumIncrease = output.nextInt();
				}
				
				//Level 1 values
				else if (configName.equals("level_1_playable_area_x:")) {
					level1PlayableAreaX = output.nextInt();
				}
				else if (configName.equals("level_1_playable_area_y:")) {
					level1PlayableAreaY = output.nextInt();
				}
				else if (configName.equals("level_1_playable_area_width:")) {
					level1PlayableAreaWidth = output.nextInt();
				}
				else if (configName.equals("level_1_playable_area_height:")) {
					level1PlayableAreaHeight = output.nextInt();
				}
				else if (configName.equals("level_1_time:")) {
					level1Time = output.nextInt();
				}
				else if (configName.equals("level_1_ghosts:")) {
					level1Ghosts = output.nextInt();
				}
				else if (configName.equals("level_1_rippers:")) {
					level1Rippers = output.nextInt();
				}
				else if (configName.equals("level_1_wisps:")) {
					level1Wisps = output.nextInt();
				}
				
				//Level 2 values
				else if (configName.equals("level_2_playable_area_x:")) {
					level2PlayableAreaX = output.nextInt();
				}
				else if (configName.equals("level_2_playable_area_y:")) {
					level2PlayableAreaY = output.nextInt();
				}
				else if (configName.equals("level_2_playable_area_width:")) {
					level2PlayableAreaWidth = output.nextInt();
				}
				else if (configName.equals("level_2_playable_area_height:")) {
					level2PlayableAreaHeight = output.nextInt();
				}
				else if (configName.equals("level_2_time:")) {
					level2Time = output.nextInt();
				}
				else if (configName.equals("level_2_ghosts:")) {
					level2Ghosts = output.nextInt();
				}
				else if (configName.equals("level_2_rippers:")) {
					level2Rippers = output.nextInt();
				}
				else if (configName.equals("level_2_wisps:")) {
					level2Wisps = output.nextInt();
				}
				
				//Level 3 values
				else if (configName.equals("level_3_playable_area_x:")) {
					level3PlayableAreaX = output.nextInt();
				}
				else if (configName.equals("level_3_playable_area_y:")) {
					level3PlayableAreaY = output.nextInt();
				}
				else if (configName.equals("level_3_playable_area_width:")) {
					level3PlayableAreaWidth = output.nextInt();
				}
				else if (configName.equals("level_3_playable_area_height:")) {
					level3PlayableAreaHeight = output.nextInt();
				}
				else if (configName.equals("level_3_time:")) {
					level3Time = output.nextInt();
				}
				else if (configName.equals("level_3_ghosts:")) {
					level3Ghosts = output.nextInt();
				}
				else if (configName.equals("level_3_rippers:")) {
					level3Rippers = output.nextInt();
				}
				else if (configName.equals("level_3_wisps:")) {
					level3Wisps = output.nextInt();
				}
				
				//Token values
				else if (configName.equals("health_token_increase:")) {
					healthTokenIncrease = output.nextInt();
				}
				else if (configName.equals("range_token_increase:")) {
					rangeTokenIncrease = output.nextInt();
				}
				else if (configName.equals("eye_token_duration:")) {
					eyeTokenDuration = output.nextInt();
				}
				else if(configName.equals("trap_token_duration:")) {
					trapTokenDuration = output.nextInt();
				}
				else if(configName.equals("range_token_duration:")) {
					rangeTokenDuration = output.nextInt();
				}
				
				//Point values
				else if (configName.equals("ghost_points:")) {
					ghostPoints = output.nextInt();
				}
				else if (configName.equals("ripper_points:")) {
					ripperPoints = output.nextInt();
				}
				else if (configName.equals("wisp_points:")) {
					wispPoints = output.nextInt();
				}
			}
			
			output.close();
			System.out.println("Config.txt has been read successfuly"); //Debug info
		}
		catch (FileNotFoundException e) {
			System.out.println("Warning: config.txt file cannot find! Please check the file path.");
		}
	}

	//Getter and setter methods
	public static int getMaxHealth() {
		return maxHealth;
	}

	public static int getMaxVacuum() {
		return maxVacuum;
	}

	public static int getEntityDamage() {
		return entityDamage;
	}

	public static int getVacuumDecrease() {
		return vacuumDecrease;
	}

	public static int getVacuumIncrease() {
		return vacuumIncrease;
	}

	public static int getLevel1PlayableAreaX() {
		return level1PlayableAreaX;
	}

	public static int getLevel1PlayableAreaY() {
		return level1PlayableAreaY;
	}

	public static int getLevel1PlayableAreaWidth() {
		return level1PlayableAreaWidth;
	}

	public static int getLevel1PlayableAreaHeight() {
		return level1PlayableAreaHeight;
	}

	public static int getLevel1Time() {
		return level1Time;
	}

	public static int getLevel1Ghosts() {
		return level1Ghosts;
	}

	public static int getLevel1Rippers() {
		return level1Rippers;
	}

	public static int getLevel1Wisps() {
		return level1Wisps;
	}

	public static int getLevel2PlayableAreaX() {
		return level2PlayableAreaX;
	}

	public static int getLevel2PlayableAreaY() {
		return level2PlayableAreaY;
	}

	public static int getLevel2PlayableAreaWidth() {
		return level2PlayableAreaWidth;
	}

	public static int getLevel2PlayableAreaHeight() {
		return level2PlayableAreaHeight;
	}

	public static int getLevel2Time() {
		return level2Time;
	}

	public static int getLevel2Ghosts() {
		return level2Ghosts;
	}

	public static int getLevel2Rippers() {
		return level2Rippers;
	}

	public static int getLevel2Wisps() {
		return level2Wisps;
	}

	public static int getLevel3PlayableAreaX() {
		return level3PlayableAreaX;
	}

	public static int getLevel3PlayableAreaY() {
		return level3PlayableAreaY;
	}

	public static int getLevel3PlayableAreaWidth() {
		return level3PlayableAreaWidth;
	}

	public static int getLevel3PlayableAreaHeight() {
		return level3PlayableAreaHeight;
	}

	public static int getLevel3Time() {
		return level3Time;
	}

	public static int getLevel3Ghosts() {
		return level3Ghosts;
	}

	public static int getLevel3Rippers() {
		return level3Rippers;
	}

	public static int getLevel3Wisps() {
		return level3Wisps;
	}

	public static int getHealthTokenIncrease() {
		return healthTokenIncrease;
	}

	public static int getRangeTokenIncrease() {
		return rangeTokenIncrease;
	}

	public static int getEyeTokenDuration() {
		return eyeTokenDuration;
	}
	
	public static int getRangeTokenDuration() {
		return rangeTokenDuration;
	}
	
	public static int getGhostPoints() {
		return ghostPoints;
	}
	
	public static int getRipperPoints() {
		return ripperPoints;
	}
	
	public static int getWispPoints() {
		return wispPoints;
	}

	public static void setMaxHealth(int maxHealth) {
		ConfigManager.maxHealth = maxHealth;
	}

	public static void setMaxVacuum(int maxVacuum) {
		ConfigManager.maxVacuum = maxVacuum;
	}

	public static void setEntityDamage(int entityDamage) {
		ConfigManager.entityDamage = entityDamage;
	}

	public static void setVacuumDecrease(int vacuumDecrease) {
		ConfigManager.vacuumDecrease = vacuumDecrease;
	}

	public static void setVacuumIncrease(int vacuumIncrease) {
		ConfigManager.vacuumIncrease = vacuumIncrease;
	}

	public static void setLevel1PlayableAreaX(int level1PlayableAreaX) {
		ConfigManager.level1PlayableAreaX = level1PlayableAreaX;
	}

	public static void setLevel1PlayableAreaY(int level1PlayableAreaY) {
		ConfigManager.level1PlayableAreaY = level1PlayableAreaY;
	}

	public static void setLevel1PlayableAreaWidth(int level1PlayableAreaWidth) {
		ConfigManager.level1PlayableAreaWidth = level1PlayableAreaWidth;
	}

	public static void setLevel1PlayableAreaHeight(int level1PlayableAreaHeight) {
		ConfigManager.level1PlayableAreaHeight = level1PlayableAreaHeight;
	}

	public static void setLevel1Time(int level1Time) {
		ConfigManager.level1Time = level1Time;
	}

	public static void setLevel1Ghosts(int level1Ghosts) {
		ConfigManager.level1Ghosts = level1Ghosts;
	}

	public static void setLevel1Rippers(int level1Rippers) {
		ConfigManager.level1Rippers = level1Rippers;
	}

	public static void setLevel1Wisps(int level1Wisps) {
		ConfigManager.level1Wisps = level1Wisps;
	}

	public static void setLevel2PlayableAreaX(int level2PlayableAreaX) {
		ConfigManager.level2PlayableAreaX = level2PlayableAreaX;
	}

	public static void setLevel2PlayableAreaY(int level2PlayableAreaY) {
		ConfigManager.level2PlayableAreaY = level2PlayableAreaY;
	}

	public static void setLevel2PlayableAreaWidth(int level2PlayableAreaWidth) {
		ConfigManager.level2PlayableAreaWidth = level2PlayableAreaWidth;
	}

	public static void setLevel2PlayableAreaHeight(int level2PlayableAreaHeight) {
		ConfigManager.level2PlayableAreaHeight = level2PlayableAreaHeight;
	}

	public static void setLevel2Time(int level2Time) {
		ConfigManager.level2Time = level2Time;
	}

	public static void setLevel2Ghosts(int level2Ghosts) {
		ConfigManager.level2Ghosts = level2Ghosts;
	}

	public static void setLevel2Rippers(int level2Rippers) {
		ConfigManager.level2Rippers = level2Rippers;
	}

	public static void setLevel2Wisps(int level2Wisps) {
		ConfigManager.level2Wisps = level2Wisps;
	}

	public static void setLevel3PlayableAreaX(int level3PlayableAreaX) {
		ConfigManager.level3PlayableAreaX = level3PlayableAreaX;
	}

	public static void setLevel3PlayableAreaY(int level3PlayableAreaY) {
		ConfigManager.level3PlayableAreaY = level3PlayableAreaY;
	}

	public static void setLevel3PlayableAreaWidth(int level3PlayableAreaWidth) {
		ConfigManager.level3PlayableAreaWidth = level3PlayableAreaWidth;
	}

	public static void setLevel3PlayableAreaHeight(int level3PlayableAreaHeight) {
		ConfigManager.level3PlayableAreaHeight = level3PlayableAreaHeight;
	}

	public static void setLevel3Time(int level3Time) {
		ConfigManager.level3Time = level3Time;
	}

	public static void setLevel3Ghosts(int level3Ghosts) {
		ConfigManager.level3Ghosts = level3Ghosts;
	}

	public static void setLevel3Rippers(int level3Rippers) {
		ConfigManager.level3Rippers = level3Rippers;
	}

	public static void setLevel3Wisps(int level3Wisps) {
		ConfigManager.level3Wisps = level3Wisps;
	}

	public static void setHealthTokenIncrease(int healthTokenIncrease) {
		ConfigManager.healthTokenIncrease = healthTokenIncrease;
	}

	public static void setRangeTokenIncrease(int rangeTokenIncrease) {
		ConfigManager.rangeTokenIncrease = rangeTokenIncrease;
	}

	public static void setEyeTokenDuration(int eyeTokenDuration) {
		ConfigManager.eyeTokenDuration = eyeTokenDuration;
	}
	
	public static void setRangeTokenDuration(int rangeTokenDuration) {
		ConfigManager.rangeTokenDuration = rangeTokenDuration;
	}
	
	public static void setGhostPoints(int ghostPoints) {
		ConfigManager.ghostPoints = ghostPoints;
	}
	
	public static void setRipperPoints(int ripperPoints) {
		ConfigManager.ripperPoints = ripperPoints;
	}
	
	public static void setWispPoints(int wispPoints) {
		ConfigManager.wispPoints = wispPoints;
	}
}	