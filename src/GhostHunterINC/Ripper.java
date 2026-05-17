package GhostHunterINC;
//The whole class is implemented by 150124014_Mehmet_Veli_Kayikli.
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class Ripper extends ParanormalEntity {
	
    private double areaX, areaY, areaW, areaH;
    private Polygon spikes;
    private Circle body,leftEye,rightEye;

    //Ripper Constructor. Takes Ripper's and corners of the playable area's coordinates as parameter.
    public Ripper(double x, double y, double areaX, double areaY, double areaW, double areaH) {
        super((Math.random() * 150 + 75) * (Math.random() < 0.5 ? 1 : -1), (Math.random() * 150 + 75) * (Math.random() < 0.5 ? 1 : -1));
        this.x = x;
        this.y = y;
        this.areaX = areaX;
        this.areaY = areaY;
        this.areaW = areaW;
        this.areaH = areaH;
        
        createRipper();
    }
    //This method creates Ripper.
    private void createRipper() {
        
        double scale = 1.4;
        double centerX = 35 * scale, centerY = 35 * scale;

    	Pane ripperShape = new Pane();
    	ripperShape.setPrefSize(70 * scale, 70 * scale);
    	
        spikes = new Polygon();
        int numOfSpikes = 12;
        double outerRadius = 30 * scale, innerRadius = 18 * scale, angle = 0, radius = 0;

        for (int i = 0; i < numOfSpikes * 2; i++) {
            angle = Math.PI / numOfSpikes * i - Math.PI / 2;
            radius = (i % 2 == 0) ? outerRadius : innerRadius;
            spikes.getPoints().addAll(centerX + Math.cos(angle) * radius, centerY + Math.sin(angle) * radius);
        }
        spikes.setFill(Color.PURPLE);

        body = new Circle(centerX, centerY, 15 * scale, Color.BLACK);
        
        leftEye = new Circle(centerX - 7 * scale, centerY, 4 * scale, Color.RED);
        rightEye = new Circle(centerX + 7 * scale, centerY, 4 * scale, Color.RED);
        
        ripperShape.getChildren().addAll(spikes, body, leftEye, rightEye);
        
        sPane = new StackPane(ripperShape);
        sPane.setPrefSize(70 * scale, 70 * scale);
    	    
    }
    //This method update Ripper's position if it is not stunted.
    @Override
    public void update(double dt) { 
    	if(this.isStunned())
    		return;
        move(dt);
        bounceOffWalls(areaX, areaY, areaW, areaH);
        sPane.setLayoutX(x);
        sPane.setLayoutY(y);
    }
    
    //Withers Ripper's color.
    @Override
	void applyWithering() {
    	spikes.setFill(Color.WHITE);
        body.setFill(Color.LIGHTGRAY);
	}
    //Makes Ripper's color vivid again.
	@Override
	void revertWithering() {
		spikes.setFill(Color.PURPLE);;
        body.setFill(Color.BLACK);
	}

	//Returns Ripper's Pane.
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