package GhostHunterINC;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Wisp extends ParanormalEntity {
    
    private double areaX, areaY, areaW, areaH, rotationAngle;
    private Group rotatingRects;
 
    public Wisp(double x, double y, double areaX, double areaY, double areaW, double areaH) {
        super((Math.random() * 200 + 100) * (Math.random() < 0.5 ? 1 : -1),(Math.random() * 200 + 100) * (Math.random() < 0.5 ? 1 : -1), 30);
        this.x = x;
        this.y = y;
        this.areaX = areaX;
        this.areaY = areaY;
        this.areaW = areaW;
        this.areaH = areaH;
        
        createWisp();
    }
 
    private void createWisp() {
        double centerX = 50, centerY = 50;
        Circle glow  = new Circle(centerX, centerY, 18, Color.RED);
        glow.setOpacity(0.7);
        Circle core = new Circle(centerX, centerY, 10, Color.RED);
        Circle leftEye = new Circle(centerX - 4, centerY - 2, 4, Color.YELLOW);
        Circle rightEye = new Circle(centerX + 4, centerY - 2, 4, Color.YELLOW);
 
        rotatingRects = new Group();
        int rectCount = 6;
        for (int i = 0; i < rectCount; i++) {
            Rectangle rect = new Rectangle(8, 20, Color.RED);
            rect.setArcWidth(3);
            rect.setArcHeight(3);
            double angle = (2 * Math.PI / rectCount) * i;
            double radius = 35;
            rect.setX(centerX + Math.cos(angle) * radius - 4);
            rect.setY(centerY + Math.sin(angle) * radius - 10);
            rect.setRotate(Math.toDegrees(angle) + 90);
            rotatingRects.getChildren().add(rect);
        }
 
        sPane = new StackPane(glow, rotatingRects, core, leftEye, rightEye);
        sPane.setPrefSize(100, 100);
        
    }
 
    @Override
    public void update(double dt) {
        move(dt);
        bounceOffWalls(areaX, areaY, areaW, areaH);
        if (isBeingVacuumed())
            vacuumEffect(dt);
        rotationAngle += 100 * dt;
        if (rotationAngle >= 360) rotationAngle = 0;
        rotatingRects.setRotate(rotationAngle);
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

	public double getRotationAngle() {
		return rotationAngle;
	}

	public void setRotationAngle(double rotationAngle) {
		this.rotationAngle = rotationAngle;
	}

	public Group getRotatingRects() {
		return rotatingRects;
	}

	public void setRotatingRects(Group rotatingRects) {
		this.rotatingRects = rotatingRects;
	}
    
}

