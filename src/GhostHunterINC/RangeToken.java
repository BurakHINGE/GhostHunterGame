package GhostHunterINC;

import javafx.scene.shape.*;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;

public class RangeToken extends Token {
	double x,y;
	StackPane shape = new StackPane();
	
	public RangeToken(double x, double y) {
		this.x = x;
		this.y = y;
		
		Circle background = new Circle(15);
		background.setFill(Color.DARKBLUE);
		background.setStroke(Color.BLACK);
		background.setStrokeWidth(2);
		
		Circle wave1 = new Circle(3);
		wave1.setFill(Color.TRANSPARENT);
		wave1.setStroke(Color.CYAN);
		wave1.setStrokeWidth(2);
		
		Circle wave2 = new Circle(7);
		wave1.setFill(Color.TRANSPARENT);
		wave1.setStroke(Color.CYAN);
		wave1.setStrokeWidth(2);
		
		Circle wave3 = new Circle(11);
		wave1.setFill(Color.TRANSPARENT);
		wave1.setStroke(Color.CYAN);
		wave1.setStrokeWidth(2);
		
		this.shape.getChildren().addAll(background, wave1, wave2, wave3);
		
		this.shape.setTranslateX(this.x);
		this.shape.setTranslateY(this.y);
	}

	@Override
	void applyEffect(Hunter hunter) {
		int increaseAmount = ConfigManager.getRangeTokenIncrease();
	    hunter.getScanner().rangeTimer(increaseAmount,5.0);
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
		return shape;
	}

	@Override
	public void move(double dt) {
		// TODO Auto-generated method stub
		
	}

}
