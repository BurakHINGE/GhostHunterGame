package GhostHunterINC;

import javafx.scene.shape.*;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import java.util.ArrayList;

//A token that temporarily reveals all the paranormal entities
public class EyeToken extends Token {
	//Main game object list that contains all the paranormal entities
	private ArrayList<GameObject> allEntities;
	
	public EyeToken(double x, double y, ArrayList<GameObject> allEntities) {
		this.x = x;
		this.y = y;
		this.allEntities = allEntities;
		
		//Visual components of the token
		Circle background = new Circle(15);
		background.setFill(Color.DARKGREEN);
		background.setStroke(Color.BLACK);
		background.setStrokeWidth(2);
		
		Ellipse eye = new Ellipse(10, 6);
		eye.setFill(Color.WHITE);
		eye.setStroke(Color.BLACK);
		eye.setStrokeWidth(1);
		
		Circle iris = new Circle(4);
		iris.setFill(Color.ALICEBLUE);
		
		Circle pupil = new Circle(2);
		pupil.setFill(Color.BLACK);
		
		//Combine all the parts of the token in the pane
		this.sPane.getChildren().addAll(background, eye, iris, pupil);
		
		//Set the visual position of the shape pane
		this.sPane.setTranslateX(this.x);
		this.sPane.setTranslateY(this.y);
		
	}
	
	@Override
	//Make all the entities visible for given time.
	void applyEffect(Hunter hunter) {
		//Get the effect duration of the token
		int time = ConfigManager.getEyeTokenDuration();
		
		//Change the revealed by token value to true so that game engine can make the entities visible
		for(GameObject obj : allEntities) {
			if(obj instanceof ParanormalEntity) {
				((ParanormalEntity) obj).setRevealedByToken(true);
			}
		}
		
		//Start a timer to revert the changes after the duration
		PauseTransition duration = new PauseTransition(Duration.seconds(time));
		
		duration.setOnFinished(e -> {
			for(GameObject obj : allEntities) {
				if(obj instanceof ParanormalEntity) {
					((ParanormalEntity) obj).setRevealedByToken(false);
				}
			}
		});
		duration.play();
	}

	@Override
	//Smooth floating animation for the token
	public void update(double dt) {
		//Increase the time every frame
		tick++;
		
		//We used sin function to get a smooth curve 
		double changeY = Math.sin(tick * 0.05) * 5;
		
		this.getShape().setTranslateY(this.y + changeY);
	}

	
	public StackPane getShape() {
		return this.sPane;
	}
	
	@Override
	//Eye token is a static item so we didn't use this method in here.
	public void move(double dt) {}

}
