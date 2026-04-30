package GhostHunterINC;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public abstract class GameObject implements Collidable{
	protected double x,y;
	protected boolean visible;
	protected Shape shape;
	
	abstract void update(double dt);
	abstract void render(Pane pane);
	
	Shape getShape() {
		return shape;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	public boolean isVisibility() {
		return visible;
	}
	public void setVisibility(boolean visibility) {
		this.visible = visibility;
	}
	
}
