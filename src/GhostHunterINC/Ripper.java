package GhostHunterINC;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class Ripper extends ParanormalEntity {

    private Group ripperShape;
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
        int numSpikes = 12;
        double outerRadius = 30;
        double innerRadius = 18; 

        for (int i = 0; i < numSpikes * 2; i++) {
            double angle = Math.PI / numSpikes * i - Math.PI / 2;
            double radius = (i % 2 == 0) ? outerRadius : innerRadius;
            spikes.getPoints().addAll(
                Math.cos(angle) * radius,
                Math.sin(angle) * radius
            );
        }
        spikes.setFill(Color.PURPLE);

      
        Circle body = new Circle(15, Color.web("#6A0DAD")); 

       
        Circle leftEye  = new Circle(-5, -2, 5, Color.BLACK);
        Circle rightEye = new Circle( 5, -2, 5, Color.BLACK);

        
        Circle leftPupil  = new Circle(-5, -2, 2, Color.RED);
        Circle rightPupil = new Circle( 5, -2, 2, Color.RED);

        ripperShape = new Group(spikes, body, leftEye, rightEye, leftPupil, rightPupil);
        this.shape = body; 
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
    public boolean checkCollision() {
        return false;
    }
}