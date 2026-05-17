package GhostHunterINC;
//150124014_Mehmet_Veli_Kayikli
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

    private void createRipper() {
    	
    	Pane ripperShape = new Pane();
    	ripperShape.setPrefSize(70, 70);
    	
        spikes = new Polygon();
        int numOfSpikes = 12;
        double outerRadius = 30, innerRadius = 18, angle = 0, radius = 0;

        for (int i = 0; i < numOfSpikes * 2; i++) {
            angle = Math.PI / numOfSpikes * i - Math.PI / 2;
            radius = (i % 2 == 0) ? outerRadius : innerRadius;
            spikes.getPoints().addAll(35 + Math.cos(angle) * radius, 35 + Math.sin(angle) * radius);
        }
        spikes.setFill(Color.PURPLE);

        body = new Circle(35, 35, 15, Color.BLACK);
        
        leftEye = new Circle(28, 35, 4, Color.RED);
        rightEye = new Circle(42, 35, 4, Color.RED);
        
        ripperShape.getChildren().addAll(spikes, body, leftEye, rightEye);
        
        sPane = new StackPane(ripperShape);
        sPane.setPrefSize(70, 70);
    	    
    }

    @Override
    public void update(double dt) { 
    	if(this.isStunned()) {
    		return;
    	}
    	
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
	void applyWithering() {
    	spikes.setFill(Color.WHITE);
        body.setFill(Color.LIGHTGRAY); 
//        leftEye.setFill(Color.DARKRED);
//        rightEye.setFill(Color.DARKRED);
	}

	@Override
	void revertWithering() {
		spikes.setFill(Color.PURPLE);;
        body.setFill(Color.BLACK);
//        leftEye.setFill(Color.RED);
//        rightEye.setFill(Color.RED);
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