package GhostHunterINC;

import javafx.scene.Node;

import javafx.scene.shape.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.Node;

// A token that adds a trap to hunters inventory
public class TrapToken extends Token{
	//Pane for the visuals of the trap token
	StackPane shape = new StackPane();
	
	public TrapToken(double x, double y) {
		this.x = x;
		this.y = y;
		
		//Visual components of the token
		Circle tokenBackground = new Circle(18);
        tokenBackground.setFill(Color.web("#2b1a3a"));
        tokenBackground.setStroke(Color.web("#9b5de5"));
        tokenBackground.setStrokeWidth(2.5);
        
        Rectangle trapBase = new Rectangle(16, 16, Color.DARKGRAY);
        trapBase.setStroke(Color.BLACK);
        trapBase.setStrokeWidth(1.5);
        
        Circle trapCore = new Circle(3.5, Color.CYAN); 
        
        //Combine all the parts of the token in the pane
        this.shape.getChildren().addAll(tokenBackground, trapBase, trapCore);
        
        //Set the visual position of the shape pane
        this.shape.setTranslateX(this.x);
        this.shape.setTranslateY(this.y);
	}
	
	@Override
	//Add 1 trap to the hunters inventory
	void applyEffect(Hunter hunter) {
		hunter.addTraps(1);
		
	}

	@Override
	//Trap token is a static item so we didn't use this method in here.
	public void move(double dt) {}

	@Override
	//Smooth floating animation for the token
	public void update(double dt) {
		//Increase the time every frame
		tick++;
				
		//We used sin function to get a smooth curve
		double changeY = Math.sin(tick * 0.05) * 5;
			
		this.getShape().setTranslateY(this.y + changeY);
	}

	@Override
	public Node getShape() {
		return shape;
	}

}
