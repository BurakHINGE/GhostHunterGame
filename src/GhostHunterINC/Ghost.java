package GhostHunterINC;
//The whole class is implemented by 150124014_Mehmet_Veli_Kayikli.
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
    
    //Ghost Constructor. Takes Ghost's and corners of the playable area's coordinates as parameter.
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
    //This method creates Ghost.
    private void createGhost() {
    	
    	Pane ghostShape = new Pane();
    	ghostShape.setPrefSize(30, 30);
    	
    	//Ghost's head.
    	head = new Arc(15, 15, 15, 15, 0, 180);
        head.setType(ArcType.OPEN);
        head.setFill(Color.WHITE);
        head.setStroke(Color.DARKGRAY);
        head.setStrokeWidth(1);
        head.setOpacity(0.7);
        //Ghost's bottom.
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
        //Ghost's Eyes.
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
    //This method update Ghost's position if it is not stunted.
    @Override
    public void update(double dt) {
    	if(this.isStunned())
    		return;
        move(dt);
        bounceOffWalls(areaX, areaY, areaW, areaH);  
        sPane.setLayoutX(x);
        sPane.setLayoutY(y);
    }
    
    //Withers Ghost's color.
    @Override
    public void applyWithering() {
        head.setFill(Color.LIGHTGRAY);
        bottom.setFill(Color.LIGHTGRAY);
        
        leftOuterEye.setFill(Color.LIGHTGRAY);
        rightOuterEye.setFill(Color.LIGHTGRAY);
        
        leftInnerEye.setFill(Color.DARKGRAY);
        rightInnerEye.setFill(Color.DARKGRAY);
    }
    //Makes Ghost's color vivid again.
    @Override
    public void revertWithering() {
        head.setFill(Color.WHITE);
        bottom.setFill(Color.WHITE);
        
        leftOuterEye.setFill(Color.WHITE);
        rightOuterEye.setFill(Color.WHITE);
        
        leftInnerEye.setFill(Color.BLACK);
        rightInnerEye.setFill(Color.BLACK);
    }
    //Returns Ghost's Pane.
    @Override
    public Node getShape() { 
    	return sPane; 
    }
    
    //Getters & Setters.
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