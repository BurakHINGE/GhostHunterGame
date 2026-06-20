package GhostHunterINC;
//The whole class is implemented by 150124014_Mehmet_Veli_Kayikli.
public abstract class ParanormalEntity extends GameObject {

    private double speedX,speedY;
    private double scale;
    private boolean beingVacuumed,captured;
    private static final double DEFAULT_RANGE =1.0;
    private boolean revealedByToken = false;
    private boolean isStunned = false;

    //ParanormalEntity constructor.
    public ParanormalEntity(double speedX, double speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
        this.scale = DEFAULT_RANGE;
        this.beingVacuumed = false;
        this.visible = false; 
    }
    //The aim of this method is update the ParanormalEntities' position by their speed times delta time of game iff they are not in scanner's range.
    @Override
    public void move(double dt) {
    	if (!beingVacuumed) {
            x += speedX * dt;
            y += speedY * dt;
        }
    }
    //The aim of this method is revert the direction of the ParanormalEntities' speed if they touch the playable area's border.
    public void bounceOffWalls(double areaX, double areaY, double areaW, double areaH) {
        if (x <= areaX || x >= areaX + areaW) 
            speedX = -speedX;
        if (y <= areaY || y >= areaY + areaH) 
            speedY = -speedY;
    }
	//The following abstract methods will be explained in subclasses where they are overriding.
    abstract void applyWithering();
    
    abstract void revertWithering();
    
    //The usage of this method is when a ParanormalEntity enters scanner's range, it should become visible and it's color must wither.
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
            
            //In order to pull ParanormalEntities to Hunter, we have to know what is the distance between Hunter and ParanormalEntities.
            double dx = hunterX - this.x;
            double dy = hunterY - this.y;
            double distance = Math.sqrt(dx * dx + dy * dy);
            
            //If distance is greater than 0, we can change the speed of ParanormalEntities.
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
    //The usage of this method is when a ParanormalEntity exits scanner's range, it should become invisible and it's color must be vivid again.
    public void inverseVacuuumEffect() {
    	if (sPane.getOpacity() != 1.0) {
            sPane.setOpacity(1.0);
            revertWithering(); 
        }
    }
	//The following 2 methods set ParanormalEntities visibility by change their opacity.
	public void makeVisible() {
        visible = true;
        sPane.setOpacity(1.0);
    }

    public void makeInvisible() {
        visible = false;
        sPane.setOpacity(0.0);
    }
    //Getters & Setters.
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