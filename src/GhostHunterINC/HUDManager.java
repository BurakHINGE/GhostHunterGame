package GhostHunterINC;

//The whole class is implemented by 150124012_Eyup_Turhan.

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

// Displays score, time, health, vacuum, and trap count on the screen.
public class HUDManager {
	private final double BAR_HEIGHT_RATIO = 0.33; // Ratio of the bars height relative to the screen.
	private Pane hudPane;
	private Rectangle healthBar;
	private Rectangle healthBarOuter;
	private Rectangle vacuumBar;
	private Rectangle vacuumBarOuter;
	private Text scoreText;
	private Text timeText;
	private Text healthLabel;
	private Text vacuumLabel;
	private Text trapText;
	
	// Properties to bind the bar heights
	private DoubleProperty healthPercentage = new SimpleDoubleProperty(1.0);
	private DoubleProperty vacuumPercentage = new SimpleDoubleProperty(1.0);
	
	// Timeline for smooth health bar animation.
	private Timeline healthTimeline;
	
	// Custom font we used for text
	Font loadedFont;
	
	private final static String TEXT_STYLE =
	        "-fx-fill: linear-gradient(to bottom, #E0E6ED 0%, #8FA1B0 35%, #4A5B68 50%, #738796 65%, #A8B9C7 100%);"
	      + "-fx-font-family: 'Creepster';"
	      + "-fx-font-weight: bold;";
	
	private final static String STEEL_TEXT_STYLE =
	        "-fx-fill: linear-gradient(to bottom, #FFFFFF 0%, #D1D9E0 40%, #768996 55%, #A2B3C0 100%);"
	      + "-fx-font-family: 'Creepster';"
	      + "-fx-font-weight: bold;";

	// Load the font and initialize the UI components.
	public HUDManager(int level) {
		hudPane = new Pane();
		hudPane.setPickOnBounds(false); //Allows player to click behind the HUD
		
		String fontPath = "file:resources/fonts/Creepster-Regular.ttf";

	    try {
	        loadedFont = Font.loadFont(fontPath, 25);
	        if (loadedFont != null) {
	            System.out.println("Font Yüklendi: " + loadedFont.getFamily());
	        } else {
	            System.out.println("Font bulunamadı, yol yanlış olabilir: " + fontPath);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		startUI(level);
	}
	
	public void startUI(int level) {
		int time ,minutes, seconds;
		//Set score text
		scoreText = new Text(" SCORE : 0");
		scoreText.setFill(Color.WHITE);
		scoreText.setFont(loadedFont);
		
		scoreText.xProperty().bind(hudPane.widthProperty().divide(2).subtract(50));
		scoreText.yProperty().bind(hudPane.heightProperty().multiply(0.05));

		
		//Set the time text according to your level and config.txt
		switch (level){
			case 1:
				time = ConfigManager.getLevel1Time();
				minutes = time / 60;
				seconds = time % 60;
				timeText = new Text(String.format("%d:%02d", minutes, seconds));
				break;
			case 2:
				time = ConfigManager.getLevel2Time();
				minutes = time / 60;
				seconds = time % 60;
				timeText = new Text(String.format("%d:%02d", minutes, seconds));
				break;
			case 3:
				time = ConfigManager.getLevel3Time();
				minutes = time / 60;
				seconds = time % 60;
				timeText = new Text(String.format("%d:%02d", minutes, seconds));
				break;
		}
		
		timeText.setFill(Color.WHITE);
		timeText.xProperty().bind(hudPane.widthProperty().divide(2).subtract(30));
		timeText.yProperty().bind(scoreText.yProperty().add(25));
		timeText.setFont(loadedFont);
		
		//Set trap text
		trapText = new Text("TRAPS: 0");
		trapText.setFill(Color.WHITE);
		
		trapText.xProperty().bind(hudPane.widthProperty().divide(2).subtract(40));
		trapText.yProperty().bind(timeText.yProperty().add(30));
		trapText.setFont(loadedFont);
		
		//Set health bar
		healthBarOuter = new Rectangle();
		healthBarOuter.setFill(Color.TRANSPARENT);
		healthBarOuter.setStroke(Color.BLACK);
		healthBarOuter.setStrokeWidth(5);
		
		healthBarOuter.xProperty().bind(hudPane.widthProperty().subtract(80));
		healthBarOuter.yProperty().bind(hudPane.heightProperty().multiply(0.1));
		healthBarOuter.widthProperty().setValue(40);
		healthBarOuter.heightProperty().bind(hudPane.heightProperty().multiply(BAR_HEIGHT_RATIO));
		
		healthBar = new Rectangle();
		healthBar.setFill(Color.DARKRED);
		
		healthBar.xProperty().bind(healthBarOuter.xProperty().add(2));
		healthBar.widthProperty().bind(healthBarOuter.widthProperty().subtract(4));
		// Bind inner height to outer height multiplied by current percentage
		healthBar.heightProperty().bind(healthBarOuter.heightProperty().subtract(4).multiply(healthPercentage));
		// Adjust Y position to make the bar decrease from top to bottom
		healthBar.yProperty().bind(healthBarOuter.yProperty().add(healthBarOuter.heightProperty()).subtract(healthBar.heightProperty()).subtract(2));
		
		healthLabel = new Text("HEALTH:");
		healthLabel.setStyle(TEXT_STYLE);
		healthLabel.xProperty().bind(healthBarOuter.xProperty().subtract(5));
		healthLabel.yProperty().bind(healthBarOuter.yProperty().subtract(10));
		healthLabel.setFont(loadedFont);
		
		//Set vacuum bar
		vacuumBarOuter = new Rectangle();
		vacuumBarOuter.setFill(Color.TRANSPARENT);
		vacuumBarOuter.setStroke(Color.BLACK);
		vacuumBarOuter.setStrokeWidth(5);
		
		vacuumBarOuter.setX(40);
		vacuumBarOuter.yProperty().bind(hudPane.heightProperty().multiply(0.1));
		vacuumBarOuter.widthProperty().setValue(40);
		vacuumBarOuter.heightProperty().bind(hudPane.heightProperty().multiply(BAR_HEIGHT_RATIO));
		
		vacuumBar = new Rectangle();
		vacuumBar.setFill(Color.DARKBLUE);
		
		vacuumBar.xProperty().bind(vacuumBarOuter.xProperty().add(2));
		vacuumBar.widthProperty().bind(vacuumBarOuter.widthProperty().subtract(4));
		vacuumBar.heightProperty().bind(vacuumBarOuter.heightProperty().subtract(4).multiply(vacuumPercentage));
		vacuumBar.yProperty().bind(vacuumBarOuter.yProperty().add(vacuumBarOuter.heightProperty()).subtract(vacuumBar.heightProperty()).subtract(2));
		
		vacuumLabel = new Text("VACUUM:");
		vacuumLabel.setStyle(TEXT_STYLE);
		vacuumLabel.xProperty().bind(vacuumBarOuter.xProperty().subtract(5));
		vacuumLabel.yProperty().bind(vacuumBarOuter.yProperty().subtract(10));
		vacuumLabel.setFont(loadedFont);
		
		//Add everything to hud pane 
		hudPane.getChildren().addAll(scoreText, timeText, trapText, healthBar, healthBarOuter, healthLabel, vacuumBar, vacuumBarOuter, vacuumLabel);
		
	}
	
	// Update the health bar smoothly
	void updateHealthBar(double currentHealth, double maxHealth) {
		//Calculate the target health percentage and change the height of the health bar
		double targetHealthPercentage = currentHealth / maxHealth;
		
		if(healthTimeline != null) {
			healthTimeline.stop();
		}
		
		healthTimeline = new Timeline();
		
		//Make health percentage to the target with the given time interval
		KeyValue key = new KeyValue(healthPercentage, targetHealthPercentage);
		KeyFrame frame = new KeyFrame(Duration.millis(200), key);
		
		healthTimeline.getKeyFrames().add(frame);
		healthTimeline.play();
	}
	
	//Update the vacuum bar
	void updateVacuumBar(double currentEnergy, double maxEnergy) {
		//Calculate the current vacuum percentage and change the height of the vacuum bar
		vacuumPercentage.set(currentEnergy / maxEnergy);
	}
	
	//Update the score text
	void updateScore(int score) {
		scoreText.setText("SCORE: " + score);
	}
	
	//Update and format the remaining time 
	void updateTime(int remainingSeconds) {
		int minute = remainingSeconds / 60;
		int second = remainingSeconds % 60;
		
		timeText.setText(String.format("%d:%02d", minute, second));
	}
	
	//Update the trap count that hunter has
	public void updateTrapCount(int count) {
	    trapText.setText("TRAPS: " + count);
	}
	
	//Return the pane that contains all the HUD elements
	public Pane getHudPane() {
		return hudPane;
	}

	public void setHudPane(Pane hudPane) {
		this.hudPane = hudPane;
	}
}
