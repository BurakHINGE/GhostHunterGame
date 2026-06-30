package GhostHunterINC;

//The whole class is implemented by 150124012_Eyup_Turhan.

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

//A trap that hunter can place on the ground
//When a paranormal entity steps on it the trap triggers and stuns the entity for a given time
public class Trap extends GameObject{
	private boolean isTriggered = false;
    private boolean isExpired = false;
    private StackPane shape = new StackPane();
    
    public Trap(double x, double y) {
    	this.x = x;
    	this.y = y;
    	
    	//Visual components of the trap
    	Rectangle base = new Rectangle(30, 30, Color.DARKGRAY);
    	base.setStroke(Color.BLACK);
    	base.setStrokeWidth(2);
    	
    	Circle core = new Circle(6, Color.CYAN);
    	//Combine all the parts of the trap in the pane
    	shape.getChildren().addAll(base, core);
    	//Set the visual position of the shape pane
    	shape.setTranslateX(this.x + 5);
    	shape.setTranslateY(this.y + 5);
    }
    
    //Activates the trap and change it color to show an entity has been captured
    public void trigger() {
    	this.isTriggered = true;
    	
    	((Circle)shape.getChildren().get(1)).setFill(Color.RED);
    }
    
    //Checks if an entity is caught
    public boolean isTriggered() {
    	return isTriggered;
    }
    
    //Checks if the traps duration is finished
    public boolean isExpired() {
    	return isExpired;
    }
    
    //Makes the trap expired so we can remove it from the game
    public void setExpired(boolean expired) {
    	this.isExpired = expired;
    }

	@Override
	//Traps are stationary so we didn't use this method
	public void move(double dt) {}

	@Override
	public void update(double dt) {}

	@Override
	public Node getShape() {
		// TODO Auto-generated method stub
		return shape;
	}
	
}
