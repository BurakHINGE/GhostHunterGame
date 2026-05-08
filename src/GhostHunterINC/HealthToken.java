package GhostHunterINC;

import javafx.scene.shape.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.Node;

public class HealthToken extends Token {
	double x,y;
	StackPane shape = new StackPane();
	
	public HealthToken(double x, double y) {
		this.x = x;
		this.y = y;
		
		Circle background = new Circle(15);
		background.setFill(Color.GREEN);
		background.setStroke(Color.BLACK);
		background.setStrokeWidth(2);
		
		Rectangle vertical = new Rectangle(6, 18, Color.RED);
		Rectangle horizontal = new Rectangle(18, 6, Color.RED);
		
		this.shape = new StackPane();
		this.shape.getChildren().addAll(background, vertical, horizontal);
		
		this.shape.setTranslateX(this.x);
		this.shape.setTranslateY(this.y);
	}
	
	public boolean checkCollision() {
		//If collides with hunter call applyEffect method.
		//TODO
	}
	
	public void applyEffect(Hunter hunter) {
		//Change the getMaxHealth method with the correct one after its implemented
		int heal_amount = ConfigManager.getMaxHealth();
		
		hunter.heal(heal_amount);
	}

	@Override
	void update(double dt) {
		// TODO Auto-generated method stub
		
	}
	
	public Node getShape() {
		return this.shape;
	}

	@Override
	public void move(double dt) {
		// TODO Auto-generated method stub
		
	}

}
