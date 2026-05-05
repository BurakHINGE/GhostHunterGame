package GhostHunterINC;

import javafx.scene.shape.Shape;

public class EyeToken extends Token {
	double x,y;
	Shape shape;
	
	public EyeToken(double x, double y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public boolean checkCollision() {
		//If collides with hunter call applyEffect method.
		return false;
	}

	@Override
	//Make all the entities visible for given time.
	void applyEffect(Hunter hunter) {
		int time = ConfigManager.getEyeTokenDuration();
		
		//Make all entities visible for given time
		
	}

	@Override
	void update() {
		// TODO Auto-generated method stub
		
	}
	
	Shape getShape() {
		return shape;
	}

}
