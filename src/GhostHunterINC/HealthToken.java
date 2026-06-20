package GhostHunterINC;

//The whole class is implemented by 150124012_Eyup_Turhan.

import javafx.scene.shape.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.Node;

//A token that heals the hunter
public class HealthToken extends Token {
	
	public HealthToken(double x, double y) {
		this.x = x;
		this.y = y;
		
		StackPane shape = new StackPane();
		
		//Visual components of the token
		Circle background = new Circle(15);
		background.setFill(Color.PINK);
		background.setStroke(Color.BLACK);
		background.setStrokeWidth(2);
		
		Rectangle vertical = new Rectangle(6, 18, Color.RED);
		Rectangle horizontal = new Rectangle(18, 6, Color.RED);
		
		//Combine all the parts of the token in the pane
		shape.getChildren().addAll(background, vertical, horizontal);
		
		//Set the visual position of the shape pane
		shape.setTranslateX(this.x);
		shape.setTranslateY(this.y);
		
		this.sPane = shape;
	}
	
	@Override
	//Increase the health of the hunter
	public void applyEffect(Hunter hunter) {
		//Get the heal amount from the config manager
		int heal_amount = ConfigManager.getHealthTokenIncrease();
		
		hunter.heal(heal_amount);
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
		return this.sPane;
	}

	@Override
	//Health token is a static item so we didn't use this method in here.
	public void move(double dt) {}



}
