package GhostHunterINC;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class HUDManager {
	private final double BAR_HEIGHT_RATIO = 0.33;
	private Pane hudPane;
	private Rectangle healthBar;
	private Rectangle healthBarOuter;
	private Rectangle vacuumBar;
	private Rectangle vacuumBarOuter;
	private Text scoreText;
	private Text timeText;
	private Text healthLabel;
	private Text vacuumLabel;
	private DoubleProperty healthPercentage = new SimpleDoubleProperty(1.0);
	private DoubleProperty vacuumPercentage = new SimpleDoubleProperty(1.0);
	
	public HUDManager(int level) {
		hudPane = new Pane();
		//Allows player to click behind the HUD
		hudPane.setPickOnBounds(false);
		
		startUI(level);
	}
	
	public void startUI(int level) {
		//Set score and time
		scoreText = new Text("SCORE : 0");
		scoreText.setFill(Color.BLACK);
		
		scoreText.xProperty().bind(hudPane.widthProperty().divide(2).subtract(50));
		scoreText.yProperty().bind(hudPane.heightProperty().multiply(0.05));
		//score.setFont(Font.loadFont()); Choose a spooky font and set it 
		
		//Set the time according to your level and config.txt
		switch (level){
			case 1:
				int time = ConfigManager.getLevel1Time();
				int minutes = time / 60;
				int seconds = time % 60;
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
		
		timeText.setFill(Color.BLACK);
		//Change the x and y coordinates of time text as you like
		timeText.xProperty().bind(hudPane.widthProperty().divide(2).subtract(30));
		timeText.yProperty().bind(scoreText.yProperty().add(25));
		
		//Set health bar
		Rectangle healthBarOuter = new Rectangle();
		healthBarOuter.setFill(Color.TRANSPARENT);
		healthBarOuter.setStroke(Color.BLACK);
		healthBarOuter.setStrokeWidth(5);
		
		healthBarOuter.xProperty().bind(hudPane.widthProperty().subtract(80));
		healthBarOuter.yProperty().bind(hudPane.heightProperty().multiply(0.1));
		healthBarOuter.widthProperty().setValue(40);
		healthBarOuter.heightProperty().bind(hudPane.heightProperty().multiply(BAR_HEIGHT_RATIO));
		
		healthBar = new Rectangle();
		healthBar.setFill(Color.RED);
		
		healthBar.xProperty().bind(healthBarOuter.xProperty().add(2));
		healthBar.widthProperty().bind(healthBarOuter.widthProperty().subtract(4));
		healthBar.heightProperty().bind(healthBarOuter.heightProperty().subtract(4).multiply(healthPercentage));
		healthBar.yProperty().bind(healthBarOuter.yProperty().add(healthBarOuter.heightProperty()).subtract(healthBar.heightProperty()).subtract(2));
		
		healthLabel = new Text("HEALTH:");
		healthLabel.setFill(Color.BLACK);
		healthLabel.xProperty().bind(healthBarOuter.xProperty().subtract(5));
		healthLabel.yProperty().bind(healthBarOuter.yProperty().subtract(10));
		
		//Set vacuum bar
		Rectangle vacuumBarOuter = new Rectangle();
		vacuumBarOuter.setFill(Color.TRANSPARENT);
		vacuumBarOuter.setStroke(Color.BLACK);
		vacuumBarOuter.setStrokeWidth(5);
		
		vacuumBarOuter.setX(40);
		vacuumBarOuter.yProperty().bind(hudPane.heightProperty().multiply(0.1));
		vacuumBarOuter.widthProperty().setValue(40);
		vacuumBarOuter.heightProperty().bind(hudPane.heightProperty().multiply(BAR_HEIGHT_RATIO));
		
		vacuumBar = new Rectangle();
		vacuumBar.setFill(Color.BLUE);
		
		vacuumBar.xProperty().bind(vacuumBarOuter.xProperty().add(2));
		vacuumBar.widthProperty().bind(vacuumBarOuter.widthProperty().subtract(4));
		vacuumBar.heightProperty().bind(vacuumBarOuter.heightProperty().subtract(4).multiply(vacuumPercentage));
		vacuumBar.yProperty().bind(vacuumBarOuter.yProperty().add(vacuumBarOuter.heightProperty()).subtract(vacuumBar.heightProperty()).subtract(2));
		
		vacuumLabel = new Text("VACUUM:");
		vacuumLabel.setFill(Color.BLACK);
		vacuumLabel.xProperty().bind(vacuumBarOuter.xProperty().subtract(5));
		vacuumLabel.yProperty().bind(vacuumBarOuter.yProperty().subtract(10));
		
		//Add everything to hud pane 
		hudPane.getChildren().addAll(scoreText,timeText,healthBar,healthBarOuter, vacuumBar, vacuumBarOuter);
		
	}
	
	void updatedHealthBar(int currentHealth, int maxHealth) {
		//Calculate the current health percentage and change the height of the health bar
		healthPercentage.set((double) currentHealth / maxHealth);
	}
	
	void updateVacuumBar(int currentEnergy, int maxEnergy) {
		//Calculate the current vacuum percentage and change the height of the vacuum bar
		vacuumPercentage.set((double) currentEnergy / maxEnergy);
	}
	
	void updateScore(int score) {
		scoreText.setText("SCORE: " + score);
	}
	
	void updateTime(int remainingSeconds) {
		int minute = remainingSeconds / 60;
		int second = remainingSeconds % 60;
		
		timeText.setText(String.format("%d:%02d", minute, second));
	}
	
	public Pane getHudPane() {
		return hudPane;
	}

	public void setHudPane(Pane hudPane) {
		this.hudPane = hudPane;
	}
}
