package GhostHunterINC;

import javafx.scene.Node;

import javafx.scene.shape.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.Node;

public class TrapToken extends Token{
	double x,y;
	StackPane shape = new StackPane();
	
	public TrapToken(double x, double y) {
		this.x = x;
		this.y = y;
		
		Polygon middle = new Polygon();
		middle.getPoints().addAll(0.0, -10.0, 10.0, 10.0, -10.0 ,10.0);
		middle.setFill(Color.PURPLE);
		middle.setStroke(Color.WHITE);
		
		shape.getChildren().add(middle);
		shape.setTranslateX(this.x);
		shape.setTranslateY(this.y);
	}
	
	@Override
	void applyEffect(Hunter hunter) {
		hunter.addTraps(1);
		
	}

	@Override
	public void move(double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(double dt) {
		//Increase the time every frame
		tick++;
				
		//Sin function gave us a smooth curve 
		double changeY = Math.sin(tick * 0.05) * 5;
			
		this.getShape().setTranslateY(this.y + changeY);
	}

	@Override
	public Node getShape() {
		// TODO Auto-generated method stub
		return shape;
	}

}
