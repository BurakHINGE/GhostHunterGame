package GhostHunterINC;

import javafx.scene.shape.*;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;

public class EyeToken extends Token {
	double x,y;
	StackPane shape = new StackPane();
	
	public EyeToken(double x, double y) {
		this.x = x;
		this.y = y;
		
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
	}

	@Override
	public void update(double dt) {
		tick++;
		
		//Angle will go from -15 degrees to 15 degrees slowly
		double angle = Math.sin(tick * 0.03) * 15;
		
		this.getShape().setRotate(angle);
		
	}

	
	public StackPane getShape() {
		return this.shape;
	}
	
	@Override
	public void move(double dt) {
		// TODO Auto-generated method stub
		
	}

}
