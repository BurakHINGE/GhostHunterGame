package GhostHunterINC;

import javafx.scene.shape.Shape;

public class RangeToken extends Token {
	double x,y;
	Shape shape;
	
	public RangeToken(double x, double y) {
		this.x = x;
		this.y = y;
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
	void update() {
		// TODO Auto-generated method stub
		
	}
	
	Shape getShape() {
		return shape;
	}
	
	

}
