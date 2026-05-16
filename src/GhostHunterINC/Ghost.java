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
    private Arc head;
    private Polygon bottom;
    Circle leftOuterEye,rightOuterEye,leftInnerEye,rightInnerEye;
    
    public Ghost(double x, double y, double areaX, double areaY, double areaW, double areaH) {
        super((Math.random() * 100 + 50) * (Math.random() < 0.5 ? 1 : -1),(Math.random() * 100 + 50) * (Math.random() < 0.5 ? 1 : -1));
        this.x = x;
        this.y = y;
        this.areaX = areaX;
        this.areaY = areaY;
        this.areaW = areaW;
        this.areaH = areaH;
        
        createGhost();
    }
 
    private void createGhost() {
    	
    	Pane ghostShape = new Pane();
    	ghostShape.setPrefSize(30, 30);

    	Arc head = new Arc(15, 15, 15, 15, 0, 180);
        head.setType(ArcType.OPEN);
        head.setFill(Color.WHITE);
        head.setStroke(Color.DARKGRAY);
        head.setStrokeWidth(1);
        head.setOpacity(0.7);
        
        bottom = new Polygon(
        		0.0,  15.0,
                0.0,  30.0,
                5.0,  23.5,
                10.0, 30.0,
                15.0, 23.5,
                20.0, 30.0,
                25.0, 23.5,
                30.0, 30.0,
                30.0, 15.0
        	);
        bottom.setFill(Color.WHITE);
        bottom.setStroke(Color.DARKGRAY);
        bottom.setStrokeWidth(1);
        bottom.setOpacity(0.7);

        leftOuterEye = new Circle(9, 14, 5, Color.WHITE);
        leftOuterEye.setStroke(Color.DARKGRAY);
        leftOuterEye.setStrokeWidth(1);
        leftOuterEye.setOpacity(0.7);
        
        leftInnerEye = new Circle(9, 14, 2.5, Color.BLACK);
        leftInnerEye.setOpacity(0.7);
        
        rightOuterEye = new Circle(21, 14, 5, Color.WHITE);
        rightOuterEye.setStroke(Color.DARKGRAY);
        rightOuterEye.setStrokeWidth(1);
        rightOuterEye.setOpacity(0.7);
        rightInnerEye = new Circle(21, 14, 2.5, Color.BLACK);
        rightInnerEye.setOpacity(0.7);

        ghostShape.getChildren().addAll(bottom, head, leftOuterEye, leftInnerEye, rightOuterEye, rightInnerEye);
        
        sPane = new StackPane(ghostShape);
        sPane.setPrefSize(30, 30);
        
    }
 
    @Override
    public void update(double dt) {
        move(dt);
        bounceOffWalls(areaX, areaY, areaW, areaH);  
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
    public void applyWithering() {
        head.setFill(Color.LIGHTGRAY);
        bottom.setFill(Color.LIGHTGRAY);
        
        leftOuterEye.setFill(Color.LIGHTGRAY);
        rightOuterEye.setFill(Color.LIGHTGRAY);
        
        leftInnerEye.setFill(Color.DARKGRAY);
        rightInnerEye.setFill(Color.DARKGRAY);
    }

    @Override
    public void revertWithering() {
        head.setFill(Color.WHITE);
        bottom.setFill(Color.WHITE);
        
        leftOuterEye.setFill(Color.WHITE);
        rightOuterEye.setFill(Color.WHITE);
        
        leftInnerEye.setFill(Color.BLACK);
        rightInnerEye.setFill(Color.BLACK);
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
    
}