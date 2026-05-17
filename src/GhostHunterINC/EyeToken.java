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

public class EyeToken extends Token {
	double x,y;
	StackPane shape = new StackPane();
	private ArrayList<GameObject> allEntities;
	
	public EyeToken(double x, double y, ArrayList<GameObject> allEntities) {
		this.x = x;
		this.y = y;
		this.allEntities = allEntities;
		
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
		
		this.shape.getChildren().addAll(background, eye, iris, pupil);
		
		this.shape.setTranslateX(this.x);
		this.shape.setTranslateY(this.y);
		
	}
	
	@Override
	//Make all the entities visible for given time.
	void applyEffect(Hunter hunter) {
		int time = ConfigManager.getEyeTokenDuration();
		
		//Make all entities visible for given time
		for(GameObject obj : allEntities) {
			if(obj instanceof ParanormalEntity) {
				((ParanormalEntity) obj).setRevealedByToken(true);
				obj.getShape().setVisible(true);
			}
		}
		
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
	public void update(double dt) {
		//Increase the time every frame
		tick++;
		
		//Sin function gave us a smooth curve 
		double changeY = Math.sin(tick * 0.05) * 5;
		
		this.getShape().setTranslateY(this.y + changeY);
	}

	
	public StackPane getShape() {
		return this.shape;
	}
	
	@Override
	public void move(double dt) {
		// TODO Auto-generated method stub
		
	}

}
