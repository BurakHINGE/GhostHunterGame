package GhostHunterINC;

//The whole class is implemented by 150124012_Eyup_Turhan.

import javafx.scene.shape.*;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;

//A token that increases the range of the vacuum temporarily
public class RangeToken extends Token {
	public RangeToken(double x, double y) {
		this.x = x;
		this.y = y;
		
		StackPane shape = new StackPane();
		
		//Visual components of the token
		Circle background = new Circle(15);
		background.setFill(Color.DARKBLUE);
		background.setStroke(Color.BLACK);
		background.setStrokeWidth(2);
		
		Circle wave1 = new Circle(3);
		wave1.setFill(Color.TRANSPARENT);
		wave1.setStroke(Color.CYAN);
		wave1.setStrokeWidth(2);
		
		Circle wave2 = new Circle(7);
		wave2.setFill(Color.TRANSPARENT);
		wave2.setStroke(Color.CYAN);
		wave2.setStrokeWidth(2);
		
		Circle wave3 = new Circle(11);
		wave3.setFill(Color.TRANSPARENT);
		wave3.setStroke(Color.CYAN);
		wave3.setStrokeWidth(2);
		
		//Combine all the parts of the token in the pane
		shape.getChildren().addAll(background, wave1, wave2, wave3);
		
		//Set the visual position of the shape pane
		shape.setTranslateX(this.x);
		shape.setTranslateY(this.y);
		
		this.sPane = shape;
	}

	@Override
	//Increase the range of vacuum
	void applyEffect(Hunter hunter) {
		//Get the increase amount and duration from the config manager.
		int increaseAmount = ConfigManager.getRangeTokenIncrease();
		int duration = ConfigManager.getRangeTokenDuration();
	    hunter.getScanner().rangeTimer(increaseAmount, duration);
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
	
	public Node getShape() {
		return sPane;
	}

	@Override
	//Range token is a static item so we didn't use this method in here.
	public void move(double dt) {}

}
