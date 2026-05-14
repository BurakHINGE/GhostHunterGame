package GhostHunterINC;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class Ripper extends ParanormalEntity {
	
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
    	
    	 Pane ripperShape = new Pane();
    	 ripperShape.setPrefSize(56, 56);
    	 
    	 Polygon spikes = new Polygon();
    	 
    	 int numOfSpikes = 12;
    	 double outerRadius = 24;
    	 double innerRadius = 14.4;
    	 double centerX = 28;
    	 double centerY = 28;
    	 
    	 for (int i = 0; i < numOfSpikes * 2; i++) {
    		 double angle = Math.PI / numOfSpikes * i - Math.PI / 2;
    		 double radius = 0;
    		 
    		 if (i % 2 == 0)
    			 radius = outerRadius;
    		 else
    			 radius = innerRadius;
    		 
    	     spikes.getPoints().addAll(centerX + Math.cos(angle) * radius,centerY + Math.sin(angle) * radius);
    	 }
    	 spikes.setFill(Color.PURPLE);

    	 Circle body = new Circle(centerX, centerY, 12, Color.DARKVIOLET);
    	    
    	 Circle leftEye = new Circle(centerX - 4, centerY - 1.6, 4, Color.BLACK);
    	 Circle rightEye = new Circle(centerX + 4, centerY - 1.6, 4, Color.BLACK);
    	    
    	 Circle leftInnerEye = new Circle(centerX - 4, centerY - 1.6, 1.6, Color.RED);
    	 Circle rightInnerEye = new Circle(centerX + 4, centerY - 1.6, 1.6, Color.RED);


    	 ripperShape.getChildren().addAll(spikes, body, leftEye, rightEye, leftInnerEye, rightInnerEye);
    	 
    	 sPane = new StackPane(ripperShape);
    	 sPane.setPrefSize(56, 56);
    	    
    }

    @Override
    public void update(double dt) {
        move(dt);
        bounceOffWalls(areaX, areaY, areaW, areaH);

        if (isBeingVacuumed()) {
            vacuumEffect(dt);
        }
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
	
}