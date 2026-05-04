package GhostHunterINC;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

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
    	
    }
    public void update(double dt) {
       
    }

    public boolean intersects(GameObject entity) {
		return false;
        
    }

    public void expandRange(int pixels) {
        
    }

    private double getRange() {
		return 0.0;
        
    }

    public void render(Pane pane) {
        if (!pane.getChildren().contains(triangle)) {
            pane.getChildren().add(triangle);
        }
    }

    public void removeFromPane(Pane pane) {
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