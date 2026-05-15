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
	    hunter.getScanner().expandRange(increaseAmount);
	    
	    Timeline timer = new Timeline(new KeyFrame(Duration.seconds(5), e -> {
	        hunter.getScanner().revertExpand(increaseAmount);
	    }));
	    timer.setCycleCount(1);
	    timer.play();
	
	}

	@Override
	public void update(double dt) {
		//Increase the time every frame
		tick++;
		
		//Change the size of the token with sin function between 0.9 and 1.1
		double scale = 1.0 + (Math.sin(tick* 0.1) * 0.1);
		
		this.getShape().setScaleX(scale);
		this.getShape().setScaleY(scale);
	}
	
	public StackPane getShape() {
		return shape;
	}

	@Override
	public void move(double dt) {
		// TODO Auto-generated method stub
		
	}

}
