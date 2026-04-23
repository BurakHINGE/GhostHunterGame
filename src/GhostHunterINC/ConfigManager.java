package GhostHunterINC;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConfigManager {
	
	//Create variables for reading values
	public static int maxHealth;
	public static int level1Time;
	public static int level2Time;
	public static int level3Time;
	public static double ghostSpeed;
	public static double ripperSpeed;
	public static double wispSpeed;
	public static int ghostPoints;
	public static int ripperPoints;
	public static int wispPoints;
	public static int tokenSpawnInterval;
	
	void loadConfig() { //Read configures from config.txt
		try {
			File conf = new File("config.txt"); //Check file path
			Scanner output = new Scanner(conf);
			
			while (output.hasNext()) { //Check is there any sentence
				String configName = output.next();
				
				if (configName.equals("maximum_health")) {
					maxHealth = output.nextInt();
				}
				else if (configName.equals("level_1_time")) {
					level1Time = output.nextInt();
				}
				else if (configName.equals("level_2_time")) {
					level2Time = output.nextInt();
				}
				else if (configName.equals("level_3_time")) {
					level3Time = output.nextInt();
				}
				else if (configName.equals("ghost_speed")) {
					ghostSpeed = output.nextInt();
				}
				else if (configName.equals("ripper_speed")) {
					ripperSpeed = output.nextInt();
				}
				else if (configName.equals("wisp_speed")) {
					wispSpeed = output.nextInt();
				}
				else if (configName.equals("ghost_points")) {
					ghostPoints = output.nextInt();
				}
				else if (configName.equals("ripper_points")) {
					ripperPoints = output.nextInt();
				}
				else if (configName.equals("wisp_points")) {
					wispPoints = output.nextInt();
				}
				else if (configName.equals("token_spawn_interval")) {
					tokenSpawnInterval = output.nextInt();
				}	
			}
			
			output.close();
			System.out.println("Config.txt has been read successfuly"); //Debug info
		}
		catch (FileNotFoundException e) {
			System.out.println("Warning: config.txt file cannot find! Please check the file path.");
		}
		
	}
	
	int getIntProperty(String key) {
			
	}
	
	double getDoubleProperty(String key) {
		
	}

	public static int getMaxHealth() {
		return maxHealth;
	}

	public static void setMaxHealth(int maxHealth) {
		ConfigManager.maxHealth = maxHealth;
	}

	public static int getLevel1Time() {
		return level1Time;
	}

	public static void setLevel1Time(int level1Time) {
		ConfigManager.level1Time = level1Time;
	}

	public static int getLevel2Time() {
		return level2Time;
	}

	public static void setLevel2Time(int level2Time) {
		ConfigManager.level2Time = level2Time;
	}

	public static int getLevel3Time() {
		return level3Time;
	}

	public static void setLevel3Time(int level3Time) {
		ConfigManager.level3Time = level3Time;
	}

	public static double getGhostSpeed() {
		return ghostSpeed;
	}

	public static void setGhostSpeed(double ghostSpeed) {
		ConfigManager.ghostSpeed = ghostSpeed;
	}

	public static double getRipperSpeed() {
		return ripperSpeed;
	}

	public static void setRipperSpeed(double ripperSpeed) {
		ConfigManager.ripperSpeed = ripperSpeed;
	}

	public static double getWispSpeed() {
		return wispSpeed;
	}

	public static void setWispSpeed(double wispSpeed) {
		ConfigManager.wispSpeed = wispSpeed;
	}

	public static int getGhostPoints() {
		return ghostPoints;
	}

	public static void setGhostPoints(int ghostPoints) {
		ConfigManager.ghostPoints = ghostPoints;
	}

	public static int getRipperPoints() {
		return ripperPoints;
	}

	public static void setRipperPoints(int ripperPoints) {
		ConfigManager.ripperPoints = ripperPoints;
	}

	public static int getWispPoints() {
		return wispPoints;
	}

	public static void setWispPoints(int wispPoints) {
		ConfigManager.wispPoints = wispPoints;
	}

	public static int getTokenSpawnInterval() {
		return tokenSpawnInterval;
	}

	public static void setTokenSpawnInterval(int tokenSpawnInterval) {
		ConfigManager.tokenSpawnInterval = tokenSpawnInterval;
	}
}