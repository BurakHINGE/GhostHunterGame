package GhostHunterINC;

import javafx.scene.shape.*;
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
	public boolean checkCollision() {
		//If collides with hunter call applyEffect method.
		return false;
	}

	@Override
	void applyEffect(Hunter hunter) {
		int increase_amount = ConfigManager.getRangeTokenIncrease(); 
		
		//Increase the range with the given amount
		hunter.scanner.expandRange(increase_amount);
		
	}

	@Override
	void update(double dt) {
		// TODO Auto-generated method stub
		
	}
	
	public StackPane getShape() {
		return shape;
	}

	@Override
	public void move(double dt) {
		// TODO Auto-generated method stub
		
	}

}
