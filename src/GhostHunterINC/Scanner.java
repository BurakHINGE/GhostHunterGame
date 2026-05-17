package GhostHunterINC;
//The whole class is implemented by 150124014_Mehmet_Veli_Kayikli.
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;

public class Scanner extends GameObject {

	private Polygon scanner;
    private Hunter hunter;
    private double energy,maxEnergy,decreaseRate,increaseRate,range,currentScannerAngle;
    private boolean active;
    private double aimAngle = 0;
    public static final double RANGE = 100;
    public static final double ANGLE = Math.toRadians(18);
    public static final double MAX_ANGLE = Math.toRadians(28);
    private Timeline rangeTime;
    
    //Scanner Constructor. Takes Hunter as an argument.
    public Scanner(Hunter hunter) {
        this.hunter = hunter;
        this.maxEnergy = ConfigManager.getMaxVacuum();
        this.energy = maxEnergy;
        this.active = false;
        this.decreaseRate = ConfigManager.getVacuumDecrease();
        this.increaseRate = ConfigManager.getVacuumIncrease();
        this.range = RANGE;
        this.currentScannerAngle = ANGLE;
        createScanner();
    }
    //Creating scanner triangle.
    private void createScanner() {
        scanner = new Polygon();
        scanner.setFill(Color.RED);
        scanner.setStroke(Color.GOLD);
        scanner.setStrokeWidth(2);
        scanner.setOpacity(0.5);
        scanner.setVisible(false);
    }
    //Activate scanner.
    public void activate() {
        if (energy > 0) {
            active = true;
            scanner.setVisible(true);
        }
    }
    //Deactivate scanner.
    public void deactivate() {
        active = false;
        scanner.setVisible(false);
    }
    //The reason why we implement this method is the position of scanner must be updated with respect to Hunter's position constantly. 
    public void updatePosition() {
    	
    	double firstPointX = hunter.getX() + 20;
        double firstPointY = hunter.getY() + 20;

        double secondPointX  = firstPointX + Math.cos(aimAngle) * range;
        double secondPointY  = firstPointY + Math.sin(aimAngle) * range;

        double thirdPointX = -Math.sin(aimAngle) * range * Math.tan(currentScannerAngle);
        double thirdPointY =  Math.cos(aimAngle) * range * Math.tan(currentScannerAngle);
        
        scanner.getPoints().setAll(firstPointX,firstPointY,secondPointX + thirdPointX, secondPointY + thirdPointY,secondPointX - thirdPointX, secondPointY - thirdPointY);      
    }
    //Scanner's position must be updated with respect to mouse's coordinates to
    public void pointToMouse(double mouseX,double mouseY) {
    	double centerX = hunter.getX() + 20;
    	double centerY = hunter.getY() + 20;
    	aimAngle = Math.atan2(mouseY - centerY, mouseX - centerX);
    	updatePosition();
    }
    
    //Checks whether scanner is active or not. If it is, decrease energy of it by dt and when it comes 0, deactivates it. Else, increase energy until it's max.
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
    //Checks given GameObject intersects with scanner.
    public boolean intersects(GameObject gameObject) {
    	return this.scanner.getBoundsInParent().intersects(gameObject.getShape().getBoundsInParent());
    	
    }
    //Expand the scanner's range and angle with given parameters.
    public void expandRange(int extraRange,double extraAngleDegrees) {
    	range += extraRange;
        double newAngle = currentScannerAngle + Math.toRadians(extraAngleDegrees);
        currentScannerAngle = Math.min(newAngle, MAX_ANGLE);
        updatePosition();
    }
    //Narrows the scanner's range and angle with given parameters.
    public void revertExpand(int extraRange,double extraAngleDegrees) {
    	range -= extraRange;
        double newAngle = currentScannerAngle - Math.toRadians(extraAngleDegrees);
        currentScannerAngle = Math.max(newAngle, ANGLE);
        updatePosition();
    }
    //The aim of this method is handling rangeToken effect. Without this method, the effect of every taking of rangeToken becomes permanent.
    public void rangeTimer(double extraAngleDegrees, double durationSeconds) {
       
        if (rangeTime != null && rangeTime.getStatus() == Animation.Status.RUNNING) 
        	rangeTime.stop();
        else 
        	expandRange(ConfigManager.getRangeTokenIncrease()*2,extraAngleDegrees);
        //In order to provide rangeToken's effect temporary, we implement a chronometer.
        rangeTime = new Timeline(new KeyFrame(Duration.seconds(durationSeconds), e -> {
            revertExpand(ConfigManager.getRangeTokenIncrease()*2,extraAngleDegrees);
        }));
        rangeTime.setCycleCount(1);
        rangeTime.play();
    }
    //If there is no scanner in the game, this method render the scanner.
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
	@Override
	public void move(double dt) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Node getShape() {
		return scanner;
	}

    
}