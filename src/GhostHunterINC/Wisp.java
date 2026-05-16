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
    private Circle membrane,plazma,core,leftOuterEye,rightOuterEye,leftInnerEye,rightInnerEye;
    private Rectangle rect;
 
    public Wisp(double x, double y, double areaX, double areaY, double areaW, double areaH) {
        super((Math.random() * 200 + 100) * (Math.random() < 0.5 ? 1 : -1),(Math.random() * 200 + 100) * (Math.random() < 0.5 ? 1 : -1));
        this.x = x;
        this.y = y;
        this.areaX = areaX;
        this.areaY = areaY;
        this.areaW = areaW;
        this.areaH = areaH;
        
        createWisp();
    }
 
    private void createWisp() {
    	
    	double centerX = 50, centerY = 50, scale = 0.75;
        centerX *= scale;
        centerY *= scale;
        
        Pane wispShape = new Pane();
        wispShape.setPrefSize(100 * scale, 100 * scale);
        
        membrane = new Circle(centerX, centerY, 26 * scale, Color.RED);
        membrane.setOpacity(0.4);
        plazma  = new Circle(centerX, centerY, 18 * scale, Color.RED);
        plazma.setOpacity(0.7);
        core = new Circle(centerX, centerY, 10 * scale, Color.RED);
        leftOuterEye = new Circle(centerX - 4 * scale, centerY - 2 * scale, 4 * scale, Color.YELLOW);
        leftOuterEye.setOpacity(0.6);
        rightOuterEye = new Circle(centerX + 4 * scale, centerY - 2 * scale, 4 * scale, Color.YELLOW);
        rightOuterEye.setOpacity(0.6);
        leftInnerEye = new Circle(centerX - 4 * scale, centerY - 2 * scale, 2 * scale, Color.YELLOW);
        rightInnerEye = new Circle(centerX + 4 * scale, centerY - 2 * scale, 2 * scale, Color.YELLOW);
        
 
        rotatingRects = new Group();
        int rectCount = 6;
        double angle = 0, radius = 0;
        for (int i = 0; i < rectCount; i++) {
            rect = new Rectangle(8 * scale, 20 * scale, Color.RED);
            rect.setArcWidth(3 * scale);
            rect.setArcHeight(3 * scale);
            angle = (2 * Math.PI / rectCount) * i;
            radius = 35 * scale;
            rect.setX(centerX + Math.cos(angle) * radius - 4 * scale);
            rect.setY(centerY + Math.sin(angle) * radius - 10 * scale);
            rect.setRotate(Math.toDegrees(angle) + 90);
            rotatingRects.getChildren().add(rect);
        }
 
        wispShape.getChildren().addAll(core, plazma, membrane, leftOuterEye, rightOuterEye, leftInnerEye, rightInnerEye, rotatingRects);
        
        sPane = new StackPane(wispShape);
        sPane.setPrefSize(100 * scale, 100 * scale);
        
    }
 
    @Override
    public void update(double dt) {
    	if(this.isStunned()) {
    		return;
    	}
    	
        move(dt);
        bounceOffWalls(areaX, areaY, areaW, areaH);
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
	void applyWithering() {
    	
        membrane.setFill(Color.LIGHTGRAY); 
        plazma.setFill(Color.LIGHTGRAY);
//        core.setFill(Color.DARKRED);
//        leftInnerEye.setFill(Color.LIGHTYELLOW);
//        rightInnerEye.setFill(Color.LIGHTYELLOW);
//        leftOuterEye.setFill(Color.DARKRED);
//        rightOuterEye.setFill(Color.DARKRED);
        
        for (Node node:rotatingRects.getChildren()) 
        	if (node instanceof Rectangle)
        		((Rectangle) node).setFill(Color.LIGHTGRAY);
        
	}

	@Override
	void revertWithering() {
        membrane.setFill(Color.RED); 
        plazma.setFill(Color.RED);
//        core.setFill(Color.RED);
//        leftInnerEye.setFill(Color.YELLOW);
//        rightInnerEye.setFill(Color.YELLOW);
//        leftOuterEye.setFill(Color.YELLOW);
//        rightOuterEye.setFill(Color.YELLOW);
        
        for (Node node:rotatingRects.getChildren()) 
        	if (node instanceof Rectangle)
        		((Rectangle) node).setFill(Color.RED);
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

