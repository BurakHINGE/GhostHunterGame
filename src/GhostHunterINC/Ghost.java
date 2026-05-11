package GhostHunterINC;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class Ghost extends ParanormalEntity {
 
    private double areaX, areaY, areaW, areaH;
	private StackPane ghostShape;
 
    public Ghost(double x, double y, double areaX, double areaY, double areaW, double areaH) {
        super((Math.random() * 100 + 50) * (Math.random() < 0.5 ? 1 : -1),(Math.random() * 100 + 50) * (Math.random() < 0.5 ? 1 : -1), 10);
        this.x = x;
        this.y = y;
        this.areaX = areaX;
        this.areaY = areaY;
        this.areaW = areaW;
        this.areaH = areaH;
        
        createGhost();
    }
 
    private void createGhost() {
    	
    	ghostShape = new StackPane();
    	ghostShape.setPrefSize(60, 60);

        Arc head = new Arc(30, 30, 30, 30, 0, 180);
        head.setType(ArcType.OPEN);
        head.setFill(Color.WHITE);
        head.setStroke(Color.DARKGRAY);
        head.setStrokeWidth(2);
        
        Polygon bottom = new Polygon(
        	    0.0, 30.0,
        	    0.0, 60.0,
        	    10.0, 47.0,
        	    20.0, 60.0,
        	    30.0, 47.0,
        	    40.0, 60.0,
        	    50.0, 47.0,
        	    60.0, 60.0,
        	    60.0, 30.0
        	);
        bottom.setFill(Color.WHITE);
        bottom.setStroke(Color.DARKGRAY);
        bottom.setStrokeWidth(2);

        
        Circle leftEyeOuter  = new Circle(18, 28, 10, Color.WHITE);
        leftEyeOuter.setStroke(Color.DARKGRAY);
        leftEyeOuter.setStrokeWidth(2);
        Circle leftEyeInner  = new Circle(18, 28, 5, Color.BLACK);
        
        Circle rightEyeOuter = new Circle(42, 28, 10, Color.WHITE);
        rightEyeOuter.setStroke(Color.DARKGRAY);
        rightEyeOuter.setStrokeWidth(2);
        Circle rightEyeInner = new Circle(42, 28, 5, Color.BLACK);

        ghostShape.getChildren().addAll(bottom, head, leftEyeOuter, leftEyeInner, rightEyeOuter, rightEyeInner);
        
        ghostShape.setPrefSize(60, 60);
        this.sPane = ghostShape;
    }
 
    @Override
    public void update(double dt) {
        move(dt);
        bounceOffWalls(areaX, areaY, areaW, areaH);
        if (isBeingVacuumed())
            vacuumEffect(dt);
        sPane.setLayoutX(x);
        sPane.setLayoutY(y);
    }
 
    @Override
    public void render(Pane pane) {
        this.currentPane = pane;
        if (visible) {
            if (!pane.getChildren().contains(sPane))
                pane.getChildren().add(sPane);
            sPane.setLayoutX(x);
            sPane.setLayoutY(y);
        } else {
            pane.getChildren().remove(sPane);
        }
    }
 
    @Override
    public Node getShape() { 
    	return sPane; 
    }

	public double getAreaX() {
		return areaX;
	}

	public void setAreaX(double areaX) {
		this.areaX = areaX;
	}

	public double getAreaY() {
		return areaY;
	}

	public void setAreaY(double areaY) {
		this.areaY = areaY;
	}

	public double getAreaW() {
		return areaW;
	}

	public void setAreaW(double areaW) {
		this.areaW = areaW;
	}

	public double getAreaH() {
		return areaH;
	}

	public void setAreaH(double areaH) {
		this.areaH = areaH;
	}

	public StackPane getGhostShape() {
		return ghostShape;
	}

	public void setGhostShape(StackPane ghostShape) {
		this.ghostShape = ghostShape;
	}
    
}