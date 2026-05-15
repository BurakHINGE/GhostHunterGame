package GhostHunterINC;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Scanner {

    private Polygon scanner;
    private Hunter hunter;
    private double energy,maxEnergy,decreaseRate,increaseRate,range;
    private boolean active;
    private double aimAngle = 0;
    public static final double DEFAULT_RANGE = 90;
    public static final double ANGLE = Math.toRadians(18);
    
    public Scanner(Hunter hunter) {
        this.hunter = hunter;
        this.maxEnergy = ConfigManager.getMaxVacuum();
        this.energy = maxEnergy;
        this.active = false;
        this.decreaseRate = ConfigManager.getVacuumDecrease();
        this.increaseRate = ConfigManager.getVacuumIncrease();
        this.range = DEFAULT_RANGE;
        createScanner();
    }

    private void createScanner() {
        scanner = new Polygon();
        scanner.setFill(Color.RED);
        scanner.setOpacity(0.5);
        scanner.setVisible(false);
    }

    public void activate() {
        if (energy > 0) {
            active = true;
            scanner.setVisible(true);
        }
    }

    public void deactivate() {
        active = false;
        scanner.setVisible(false);
    }

    public void updatePosition() {
    	
    	double firstPointX = hunter.getX() + 20;
        double firstPointY = hunter.getY() + 20;

        double secondPointX  = firstPointX + Math.cos(aimAngle) * range;
        double secondPointY  = firstPointY + Math.sin(aimAngle) * range;

        double thirdPointX = -Math.sin(aimAngle) * range * Math.tan(ANGLE);
        double thirdPointY =  Math.cos(aimAngle) * range * Math.tan(ANGLE);
        
        scanner.getPoints().setAll(firstPointX,firstPointY,secondPointX + thirdPointX, secondPointY + thirdPointY,secondPointX - thirdPointX, secondPointY - thirdPointY);      
    }
    
    public void pointToMouse(double mouseX,double mouseY) {
    	double centerX = hunter.getX() + 20;
    	double centerY = hunter.getY() + 20;
    	aimAngle = Math.atan2(mouseY - centerY, mouseX - centerX);
    	updatePosition();
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
            if (energy > maxEnergy) energy = maxEnergy;
        }
    }

    public boolean intersects(GameObject gameObject) {
    	return this.scanner.getBoundsInParent().intersects(gameObject.getShape().getBoundsInParent());
    	
    }

    public void expandRange(int extraRange) {
    	range += extraRange;
    }

    public void render(Pane pane) {
        if (!pane.getChildren().contains(scanner)) {
            pane.getChildren().add(scanner);
        }
    }

    public void remove(Pane pane) {
        pane.getChildren().remove(scanner);
    }

	public Polygon getScanner() {
		return scanner;
	}

	public void setScanner(Polygon scanner) {
		this.scanner = scanner;
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