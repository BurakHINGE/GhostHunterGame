package GhostHunterINC;
//The whole class is implemented by 150124014_Mehmet_Veli_Kayikli.
public abstract class ParanormalEntity extends GameObject {

    private double speedX,speedY;
    private double scale;
    private boolean beingVacuumed,captured;
    private static final double DEFAULT_RANGE =1.0;
    private boolean revealedByToken = false;
    private boolean isStunned = false;

    public ParanormalEntity(double speedX, double speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
        this.scale = DEFAULT_RANGE;
        this.beingVacuumed = false;
        this.visible = false; 
    }
    
    @Override
    public void move(double dt) {
    	if (!beingVacuumed) {
            x += speedX * dt;
            y += speedY * dt;
        }
    }

    public void bounceOffWalls(double areaX, double areaY, double areaW, double areaH) {
        if (x <= areaX || x >= areaX + areaW) 
            speedX = -speedX;
        if (y <= areaY || y >= areaY + areaH) 
            speedY = -speedY;
    }
    
    abstract void applyWithering();
    
    abstract void revertWithering();

    public void vacuumEffect(double dt,double hunterX,double hunterY) {
    	if (sPane.getOpacity() != 0.5) {
            sPane.setOpacity(0.5);
            applyWithering(); 
        }
    	if (scale > 0.3) {
            scale -= 0.25 * dt;
            
            if (scale <= 0.3) {
                scale = 0.3;
            }
            sPane.setScaleX(scale);
            sPane.setScaleY(scale);  
            
            double dx = hunterX - this.x;
            double dy = hunterY - this.y;
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance > 0) {
                double pullSpeed = 80.0; 
                this.x += (dx / distance) * pullSpeed * dt;
                this.y += (dy / distance) * pullSpeed * dt;
            }
        } else {
            captured = true;
            visible = false;			
        }
    }
    public void inverseVacuuumEffect() {
    	if (sPane.getOpacity() != 1.0) {
            sPane.setOpacity(1.0);
            revertWithering(); 
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
	
	public boolean isRevealedByToken() {
	    return revealedByToken;
	}

	public void setRevealedByToken(boolean revealedByToken) {
	    this.revealedByToken = revealedByToken;
	}
	
	public boolean isStunned() {
		return isStunned;
	}
	
	public void setStunned(boolean stunned) {
		this.isStunned = stunned;
	}
    
}