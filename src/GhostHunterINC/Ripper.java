package GhostHunterINC;

import javafx.scene.layout.Pane;

public class Ripper extends ParanormalEntity {
	
    private Pane ripperShape;
    private double areaX, areaY, areaW, areaH;

    public Ripper(double x, double y, double areaX, double areaY, double areaW, double areaH) {
        super((Math.random() * 150 + 75) * (Math.random() < 0.5 ? 1 : -1), (Math.random() * 150 + 75) * (Math.random() < 0.5 ? 1 : -1), 20);
        this.x = x;
        this.y = y;
        this.areaX = areaX;
        this.areaY = areaY;
        this.areaW = areaW;
        this.areaH = areaH;

        createRipper();
    }

    private void createRipper() {
        
    }

	@Override
	public boolean checkCollision() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	void update(double dt) {
		// TODO Auto-generated method stub
		
	}

	public Pane getRipperShape() {
		return ripperShape;
	}

	public void setRipperShape(Pane ripperShape) {
		this.ripperShape = ripperShape;
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