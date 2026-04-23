package GhostHunterINC;

public abstract class GameObject implements Collidable{
	double x,y;
	Shape body;
	
	abstract void update();
	
	Shape getShape() {
		return body;
	}
}
