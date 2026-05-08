package GhostHunterINC;

public abstract class ParanormalEntity extends GameObject {

    private double speedX,speedY;
    private int pointValue;
    private double scale;
    private boolean beingVacuumed;

    public ParanormalEntity(double speedX, double speedY, int pointValue) {
        this.speedX = speedX;
        this.speedY = speedY;
        this.pointValue = pointValue;
        this.scale = 1.0;
        this.beingVacuumed = false;
        this.visible = false; 
    }
    
    @Override
    public void move(double dt) {
        x += speedX * dt;
        y += speedY * dt;
    }

    public void bounceOffWalls(double areaX, double areaY, double areaW, double areaH) {
        if (x <= areaX || x >= areaX + areaW) 
            speedX = -speedX;
        if (y <= areaY || y >= areaY + areaH) 
            speedY = -speedY;
    }

    public void vacuumEffect(double dt) {
        if (scale > 0) {
            scale -= 0.5 * dt;
            shape.setScaleX(scale);
            shape.setScaleY(scale);
        }
    }

    public void makeVisible() {
        visible = true;
        shape.setOpacity(1.0);
        shape.setStyle("-fx-fill: rgba(200, 200, 200, 0.8);");
    }

    public void makeInvisible() {
        visible = false;
        shape.setOpacity(0.0);
    }

	public double getSpeedX() {
		return speedX;
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	public double getSpeedY() {
		return speedY;
	}

	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

	public int getPointValue() {
		return pointValue;
	}

	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public boolean isBeingVacuumed() {
		return beingVacuumed;
	}

	public void setBeingVacuumed(boolean beingVacuumed) {
		this.beingVacuumed = beingVacuumed;
	}
    
    
    
    
}