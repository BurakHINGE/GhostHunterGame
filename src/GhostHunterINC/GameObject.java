package GhostHunterINC;
//150124014_Mehmet_Veli_Kayikli
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;

public abstract class GameObject implements Renderable, Movable, Collidable{
	protected double x,y;
	protected boolean visible;
	protected StackPane sPane;
	protected Pane currentPane;
	
	@Override
	public abstract void move(double dt);
	
	public abstract void update(double dt);
	
	@Override
	public void render(Pane pane) {
	    this.currentPane = pane;
	    if (visible) {
	        if (!pane.getChildren().contains(sPane)) 
	            pane.getChildren().add(sPane);
	        sPane.setLayoutX(x);
	        sPane.setLayoutY(y);
	    } else 
	        pane.getChildren().remove(sPane);
	}
	public void removeFromPane() {
	    if (currentPane != null) 
	        currentPane.getChildren().remove(sPane);
	}
	
	public abstract Node getShape();

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
	
	@Override
	public boolean checkCollision(GameObject other) {
		return this.getShape().getBoundsInParent().intersects(other.getShape().getBoundsInParent());
	}
	
}

