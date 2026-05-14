package GhostHunterINC;

public abstract class ParanormalEntity extends GameObject {

    private double speedX,speedY;
    private int pointValue;
    private double scale;
    private boolean beingVacuumed,captured;
    private static final double DEFAULT_RANGE =1.0;

    public ParanormalEntity(double speedX, double speedY, int pointValue) {
        this.speedX = speedX;
        this.speedY = speedY;
        this.pointValue = pointValue;
        this.scale = DEFAULT_RANGE;
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

    public void vacuumEffect(double dt,double hunterX,double hunterY) {
        if (scale > 0) {
            scale -= 0.5 * dt;
            if (scale < 0)
            	scale = 0;
            sPane.setScaleX(scale);
            sPane.setScaleY(scale);
            
            x += (hunterX - x) * dt;
            y += (hunterY - y) * dt;
        } else {
        	captured = true;
        	visible = false;			
        }
    }
    public void inverseVacuuumEffect(double dt) {
    	if (scale <1.0) {
    		scale += 0.5 * dt;
    		if (scale >1.0)
    			scale = 1.0;
    		sPane.setScaleX(scale);
    		sPane.setScaleY(scale);
    	}
    }
    
	public void makeVisible() {
        visible = true;
        sPane.setOpacity(1.0);
    }

    public void makeInvisible() {
        visible = false;
        sPane.setOpacity(0.0);
    }
    public boolean isCaptured() {
		return captured;
	}

	public void setCaptured(boolean captured) {
		this.captured = captured;
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