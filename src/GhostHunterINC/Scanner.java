package GhostHunterINC;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class Scanner {

    private Polygon triangle;
    private Hunter hunter;
    private double energy,maxEnergy,decreaseRate,increaseRate;
    private boolean active;
    
    public Scanner(Hunter hunter) {
        this.hunter = hunter;
        this.maxEnergy = ConfigManager.getMaxVacuum();
        this.energy = maxEnergy;
        this.active = false;
        this.decreaseRate = ConfigManager.getVacuumDecrease();
        this.increaseRate = ConfigManager.getVacuumIncrease();
        createScanner();
    }

    private void createScanner() {
        triangle = new Polygon();
        updatePosition(); 
        triangle.setFill(Color.ORANGE);
        triangle.setOpacity(0.5);
        triangle.setVisible(false);
    }

    public void activate() {
        if (energy > 0) {
            active = true;
            triangle.setVisible(true);
        }
    }

    public void deactivate() {
        active = false;
        triangle.setVisible(false);
    }

    public void updatePosition() {
    	double hx = hunter.getX();
        double hy = hunter.getY();
        double range = getRange();
        triangle.getPoints().setAll(hx, hy,hx + range, hy - range / 2, hx + range, hy + range / 2  );
    }
    public void update(double dt) {
    	if (active) {
            energy -= decreaseRate * dt;
            if (energy <= 0) {
                energy = 0;
                deactivate();
            }
        } else {
            energy += increaseRate * dt;
            if (energy > maxEnergy) 
            	energy = maxEnergy;
        }
    }

    public boolean intersects(GameObject entity) {
    	Shape intersection = Shape.intersect(triangle, (Shape) entity.getShape());
        return intersection.getBoundsInLocal().getWidth() > 0;
        
    }

    public void expandRange(int pixels) {
    	double currentRange = getRange();
        double newRange = currentRange + pixels;

        double hx = hunter.getX();
        double hy = hunter.getY();

        triangle.getPoints().setAll(hx, hy,hx + newRange, hy - newRange / 2,hx + newRange, hy + newRange / 2);
    }

    private double getRange() {
    	if (triangle.getPoints().size() < 6) 
    		return 150; 
        return triangle.getPoints().get(2) - triangle.getPoints().get(0);
        
    }

    public void render(Pane pane) {
        if (!pane.getChildren().contains(triangle)) {
            pane.getChildren().add(triangle);
        }
    }

    public void remove(Pane pane) {
        pane.getChildren().remove(triangle);
    }

	public Polygon getTriangle() {
		return triangle;
	}

	public void setTriangle(Polygon triangle) {
		this.triangle = triangle;
	}

	public Hunter getHunter() {
		return hunter;
	}

	public void setHunter(Hunter hunter) {
		this.hunter = hunter;
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}

	public double getMaxEnergy() {
		return maxEnergy;
	}

	public void setMaxEnergy(double maxEnergy) {
		this.maxEnergy = maxEnergy;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public double getDecreaseRate() {
		return decreaseRate;
	}

	public void setDecreaseRate(double decreaseRate) {
		this.decreaseRate = decreaseRate;
	}

	public double getIncreaseRate() {
		return increaseRate;
	}

	public void setIncreaseRate(double increaseRate) {
		this.increaseRate = increaseRate;
	}

    
}