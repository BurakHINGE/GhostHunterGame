package GhostHunterINC;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HUDManager {
	ConfigManager config = new ConfigManager();
	private final double MAX_HEIGHT = 200.0;
	private Pane hudPane;
	private Rectangle healthBar;
	private Rectangle vacuumBar;
	private Text scoreText;
	private Text timeText;
	
	public HUDManager() {
		hudPane = new Pane();
		startUI();
	}
	
	private void startUI(int level) {
		//Set score and time
		scoreText = new Text("SCORE : 0");
		scoreText.setFill(Color.WHITE);
		//score.setFont(Font.loadFont()); Choose a spooky font and set it 
		//Change the x and y coordinates of score text as you like
		scoreText.setX(300);
		scoreText.setY(300);
		
		//Set the time according to your level and config.txt
		switch (level){
			case 1:
				int time = config.getLevel1Time();
				int minutes = time / 60;
				int seconds = time % 60;
				timeText = new Text(String.format("%d:%02d", minutes, seconds));
			case 2:
				time = config.getLevel2Time();
				minutes = time / 60;
				seconds = time % 60;
				timeText = new Text(String.format("%d:%02d", minutes, seconds));
			case 3:
				time = config.getLevel3Time();
				minutes = time / 60;
				seconds = time % 60;
				timeText = new Text(String.format("%d:%02d", minutes, seconds));
		}
		
		timeText.setFill(Color.WHITE);
		//Change the x and y coordinates of time text as you like
		timeText.setX(400);
		timeText.setY(500);
		
		//Set health bar
		Rectangle healthBarOuter = new Rectangle(750,50,40,MAX_HEIGHT);
		healthBarOuter.setFill(Color.TRANSPARENT);
		healthBarOuter.setStroke(Color.BLACK);
		healthBarOuter.setStrokeWidth(5);
		
		healthBar = new Rectangle(752,52,36,MAX_HEIGHT - 4);
		healthBar.setFill(Color.RED);
		
		//Set vacuum bar
		Rectangle vacuumBarOuter = new Rectangle(30,50,40,MAX_HEIGHT);
		vacuumBarOuter.setFill(Color.TRANSPARENT);
		vacuumBarOuter.setStroke(Color.BLACK);
		vacuumBarOuter.setStrokeWidth(5);
		
		vacuumBar = new Rectangle(32,52,36,MAX_HEIGHT - 4);
		vacuumBar.setFill(Color.ALICEBLUE);
		
		//Add everything to hud pane 
		hudPane.getChildren().addAll(scoreText,timeText,healthBar,healthBarOuter, vacuumBar, vacuumBarOuter);
		
	}
	
	void updatedHealthBar(int currentHealth, int maxHealth) {
		//Calculate the current health percentage and change the height of the health bar
		double healthPercentage = currentHealth / maxHealth;
		double newHeigth = MAX_HEIGHT * healthPercentage;
		
		healthBar.setHeight(newHeigth);
		//Don't forget to change the y coordinates of the health bar.
	}
	
	void updateVacuumBar(int currentEnergy, int maxEnergy) {
		//Calculate the current vacuum percentage and change the height of the vacuum bar
		double vacuumPercentage = currentEnergy / maxEnergy;
		double newHeight = MAX_HEIGHT * vacuumPercentage;
		
		vacuumBar.setHeight(newHeight);
		//Don't forget to change the y coordinates of the vacuum bar.
		
	}
	
	void updateScore(int score) {
		scoreText.setText("SCORE: " + score);
	}
	
	void updateTime(int remainingSeconds) {
		int minute = remainingSeconds / 60;
		int second = remainingSeconds % 60;
		
		timeText.setText(String.format("%d:%02d", minute, second));
	}
}
