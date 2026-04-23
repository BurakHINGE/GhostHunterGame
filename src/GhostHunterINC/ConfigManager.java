package GhostHunterINC;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConfigManager {
	
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
			File conf = new File("config.txt"); //
			Scanner output = new Scanner(conf);
			
			while (output.hasNext()) {
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
			System.out.println("Config.txt has been read successfuly");
		}
		catch (FileNotFoundException e) {
			System.out.println("Warning: config.txt file cannot find! Please check the file path.");
		}
		
	}
	
	int getIntProperty(String key) {
			
	}
	
	double getDoubleProperty(String key) {
		
	}
}