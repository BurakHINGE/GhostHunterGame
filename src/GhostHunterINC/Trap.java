package GhostHunterINC;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Trap extends GameObject{
	private boolean isTriggered = false;
    private boolean isExpired = false;
    private StackPane shape = new StackPane();
    
    public Trap(double x, double y) {
    	this.x = x;
    	this.y = y;
    	
    	createTrap();
    }
    
    private void createTrap() {
    	Rectangle base = new Rectangle(30, 30, Color.DARKGRAY);
    	base.setStroke(Color.BLACK);
    	base.setStrokeWidth(2);
    	
    	Circle core = new Circle(6, Color.CYAN);
    	shape.getChildren().addAll(base, core);
    	
    	shape.setLayoutX(this.x + 5);
    	shape.setLayoutY(this.y + 5);
    }
    
    public void trigger() {
    	this.isTriggered = true;
    	
    	((Circle)shape.getChildren().get(1)).setFill(Color.RED);
    }
    
    public boolean isTriggered() {
    	return isTriggered;
    }
    
    public boolean isExpired() {
    	return isExpired;
    }
    
    public void setExpired(boolean expired) {
    	this.isExpired = expired;
    }

	@Override
	public void move(double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Node getShape() {
		// TODO Auto-generated method stub
		return shape;
	}
	
}
