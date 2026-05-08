package GhostHunterINC;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class Ghost extends ParanormalEntity {

    private StackPane ghostShape;
    private double areaX, areaY, areaW, areaH;

    public Ghost(double x, double y, double areaX, double areaY, double areaW, double areaH) {
        super((Math.random() * 100 + 50) * (Math.random() < 0.5 ? 1 : -1),(Math.random() * 100 + 50) * (Math.random() < 0.5 ? 1 : -1),10);
        this.x = x;
        this.y = y;
        this.areaX = areaX;
        this.areaY = areaY;
        this.areaW = areaW;
        this.areaH = areaH;
        createGhost();
    }

    private void createGhost() {
    	Circle head = new Circle(20, Color.WHITE);
        head.setOpacity(0.75);
        head.setCenterX(20);
        head.setCenterY(20);

        Polygon bottom = new Polygon(0.0, 20.0,0.0, 40.0,10.0, 30.0,20.0, 40.0,30.0, 30.0,40.0, 40.0,40.0, 20.0);
        bottom.setFill(Color.WHITE);
        bottom.setOpacity(0.75);
        
        Circle leftEye  = new Circle(13, 15, 4, Color.BLACK);
        Circle rightEye = new Circle(27, 15, 4, Color.BLACK);

        ghostShape = new StackPane(head, bottom, leftEye, rightEye);
        ghostShape.setPrefSize(40, 50);
        this.shape = ghostShape;
    }
    
    @Override
    public void update(double dt) {
        move(dt);
        bounceOffWalls(areaX, areaY, areaW, areaH);

        if (isBeingVacuumed()) 
            vacuumEffect(dt);
        ghostShape.setLayoutX(x);
        ghostShape.setLayoutY(y);
    }

    @Override
    public void render(Pane pane) {
        this.currentPane = pane;
        if (visible)
            if (!pane.getChildren().contains(ghostShape)) 
                pane.getChildren().add(ghostShape);
        else 
            pane.getChildren().remove(ghostShape);
    }

    @Override
    public boolean checkCollision() {
    	// TODO Auto-generated method stub
        return false;
    }

	public Pane getGhostShape() {
		return ghostShape;
	}

	public void setGhostShape(StackPane ghostShape) {
		this.ghostShape = ghostShape;
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

	@Override
	public Node getShape() {
		return ghostShape;
	}
    
}