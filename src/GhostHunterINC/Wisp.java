package GhostHunterINC;


import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.animation.KeyFrame;

public class Wisp extends ParanormalEntity {

    private Pane wispShape;
    private double areaX, areaY, areaW, areaH;

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

        Circle outerGlow  = new Circle(cx, cy, 40, Color.web("#FF4500", 0.2));
        Circle middleGlow = new Circle(cx, cy, 28, Color.web("#FF4500", 0.4));
        Circle innerGlow  = new Circle(cx, cy, 18, Color.web("#FF6347", 0.7));
        Circle core       = new Circle(cx, cy, 10, Color.web("#FF0000", 1.0));

        Circle leftEye  = new Circle(cx - 4, cy - 2, 4, Color.BLACK);
        Circle rightEye = new Circle(cx + 4, cy - 2, 4, Color.BLACK);

        Pane rotatingRects = new Pane();
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

        final double[] currentAngle = {0};
        Timeline rotation = new Timeline(new KeyFrame(Duration.millis(16), e -> {currentAngle[0] += 2; 
                if (currentAngle[0] >= 360) 
                	currentAngle[0] = 0;
                rotatingRects.setRotate(currentAngle[0]);
            })
        );
        rotation.setCycleCount(Timeline.INDEFINITE);
        rotation.play();

        wispShape = new Pane(outerGlow, middleGlow, innerGlow, rotatingRects, core, leftEye, rightEye);
        wispShape.setPrefSize(100, 100);
        this.shape = core;
    }

    @Override
    public void update(double dt) {
    	move(dt);
        bounceOffWalls(areaX, areaY, areaW, areaH);

        if (isBeingVacuumed()) {
            vacuumEffect(dt);
        }

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