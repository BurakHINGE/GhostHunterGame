package GhostHunterINC;

import javafx.scene.shape.Shape;

public class HealthToken extends Token {
	double x,y;
	Shape shape;
	
	public HealthToken(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean checkCollision() {
		//If collides with hunter call applyEffect method.
		
	}
	
	public void applyEffect(Hunter hunter) {
		//Change the getMaxHealth method with the correct one after its implemented
		int heal_amount = ConfigManager.getMaxHealth();
		
		hunter.heal(heal_amount);
	}

	@Override
	void update() {
		// TODO Auto-generated method stub
		
	}
	
	Shape getShape() {
		return shape;
	}
}
