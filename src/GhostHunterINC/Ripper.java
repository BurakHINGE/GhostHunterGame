package GhostHunterINC;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class Ripper extends ParanormalEntity {

    private Pane ripperShape;
    private double areaX, areaY, areaW, areaH;

    public Ripper(double x, double y, double areaX, double areaY, double areaW, double areaH) {
        super((Math.random() * 150 + 75) * (Math.random() < 0.5 ? 1 : -1), (Math.random() * 150 + 75) * (Math.random() < 0.5 ? 1 : -1), 20 );
        this.x = x;
        this.y = y;
        this.areaX = areaX;
        this.areaY = areaY;
        this.areaW = areaW;
        this.areaH = areaH;
        
        createRipper();
    }

    private void createRipper() {
    	 Polygon spikes = new Polygon();
    	 
    	 int numOfSpikes = 12;
    	 double outerRadius = 30;
    	 double innerRadius = 18;
    	 double cx = 35;
    	 double cy = 35;
    	 double radius = 0;
    	 
    	 for (int i = 0; i < numOfSpikes * 2; i++) {
    		 double angle = Math.PI / numOfSpikes * i - Math.PI / 2;
    		 
    		 if (i % 2 == 0)
    			 radius = outerRadius;
    		 else
    			 radius = innerRadius;
    		 
    	     spikes.getPoints().addAll(cx + Math.cos(angle) * radius,cy + Math.sin(angle) * radius);
    	 }
    	 spikes.setFill(Color.PURPLE);

    	 Circle body = new Circle(cx, cy, 15, Color.web("#6A0DAD"));
    	    
    	 Circle leftEye = new Circle(cx - 5, cy - 2, 5, Color.BLACK);
    	 Circle rightEye = new Circle(cx + 5, cy - 2, 5, Color.BLACK);
    	    
    	 Circle leftPupil = new Circle(cx - 5, cy - 2, 2, Color.RED);
    	 Circle rightPupil = new Circle(cx + 5, cy - 2, 2, Color.RED);


    	 Pane ripperPane = new Pane(spikes, body, leftEye, rightEye, leftPupil, rightPupil);
    	 ripperPane.setPrefSize(70, 70);

    	 ripperShape = ripperPane;
    	 shape = new StackPane(ripperPane);
    	 shape.setPrefSize(70, 70);
    	    
    }

    @Override
    public void update(double dt) {
        move(dt);
        bounceOffWalls(areaX, areaY, areaW, areaH);

        if (isBeingVacuumed()) {
            vacuumEffect(dt);
        }

        ripperShape.setLayoutX(x);
        ripperShape.setLayoutY(y);
    }

    @Override
    public void render(Pane pane) {
        this.currentPane = pane;
        if (visible) {
            if (!pane.getChildren().contains(ripperShape)) {
                pane.getChildren().add(ripperShape);
            }
        } else {
            pane.getChildren().remove(ripperShape);
        }
    }


	@Override
	public Node getShape() {
		return ripperShape;
	}

	public Pane getRipperShape() {
		return ripperShape;
	}

	public void setRipperShape(Pane ripperShape) {
		this.ripperShape = ripperShape;
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