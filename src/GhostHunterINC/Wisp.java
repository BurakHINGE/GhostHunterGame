package GhostHunterINC;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Wisp extends ParanormalEntity {

    private Pane wispShape,rotatingRects;
    private double areaX, areaY, areaW, areaH,rotationAngle;

    public Wisp(double x, double y, double areaX, double areaY, double areaW, double areaH) {
        super((Math.random() * 200 + 100) * (Math.random() < 0.5 ? 1 : -1),(Math.random() * 200 + 100) * (Math.random() < 0.5 ? 1 : -1),30);
        this.x = x;
        this.y = y;
        this.areaX = areaX;
        this.areaY = areaY;
        this.areaW = areaW;
        this.areaH = areaH;

        createWisp();
    }

    private void createWisp() {
    	double cx = 50, cy = 50;

        Circle outerGlow = new Circle(cx, cy, 40, Color.web("#FF4500", 0.2));
        Circle middleGlow = new Circle(cx, cy, 28, Color.web("#FF4500", 0.4));
        Circle innerGlow = new Circle(cx, cy, 18, Color.web("#FF6347", 0.7));
        Circle core = new Circle(cx, cy, 10, Color.web("#FF0000", 1.0));

        Circle leftEye = new Circle(cx - 4, cy - 2, 4, Color.BLACK);
        Circle rightEye = new Circle(cx + 4, cy - 2, 4, Color.BLACK);

        rotatingRects = new Pane();
        int rectCount = 6;
        for (int i = 0; i < rectCount; i++) {
        	
            Rectangle rect = new Rectangle(8, 20, Color.web("#FF4500", 0.8));
            
            rect.setArcWidth(3);
            rect.setArcHeight(3);

            double angle = (2 * Math.PI / rectCount) * i;
            double radius = 50;
            
            rect.setLayoutX(cx + Math.cos(angle) * radius - 4);
            rect.setLayoutY(cy + Math.sin(angle) * radius - 10);
            rect.setRotate(Math.toDegrees(angle));

            rotatingRects.getChildren().add(rect);
        }

        Pane wispPane = new Pane(outerGlow, middleGlow, innerGlow, rotatingRects, core, leftEye, rightEye);
        wispPane.setPrefSize(100, 100);

        shape = new StackPane(wispPane);
        shape.setPrefSize(100, 100);
    }

    @Override
    public void update(double dt) {
    	move(dt);
        bounceOffWalls(areaX, areaY, areaW, areaH);

        if (isBeingVacuumed()) {
            vacuumEffect(dt);
        }

        rotationAngle += 100 * dt;
        if (rotationAngle >= 360)
        	rotationAngle = 0;
        rotatingRects.setRotate(rotationAngle);
        
        wispShape.setLayoutX(x);
        wispShape.setLayoutY(y);
    }

    @Override
    public void render(Pane pane) {
       this.currentPane = pane;
       if(visible) {
    	   if(!pane.getChildren().contains(wispShape)) 
    		   pane.getChildren().add(wispShape);
    	   else
    		   pane.getChildren().remove(wispShape);
       }
    }

    @Override
    public boolean checkCollision() {
        return false;
    }
    @Override
	public Node getShape() {

    	return wispShape;
	}

	public Pane getWispShape() {
		return wispShape;
	}

	public void setWispShape(Pane wispShape) {
		this.wispShape = wispShape;
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
    
}