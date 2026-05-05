package GhostHunterINC;


import javafx.scene.layout.Pane;

public class Wisp extends ParanormalEntity {

    private Pane wispShape;
    private double areaX, areaY, areaW, areaH;

    public Wisp(double x, double y, double areaX, double areaY, double areaW, double areaH) {
        super((Math.random() * 200 + 100) * (Math.random() < 0.5 ? 1 : -1),(Math.random() * 200 + 100) * (Math.random() < 0.5 ? 1 : -1),30);
        this.x = x;
        this.y = y;
        this.areaX = areaX;
        this.areaY = areaY;
        this.areaW = areaW;
        this.areaH = areaH;

        createWisp();
    }

    private void createWisp() {
        
    }

    @Override
    public void update(double dt) {
        
    }

    @Override
    public void render(Pane pane) {
       
    }

    @Override
    public boolean checkCollision() {
        return false;
    }

	public Pane getWispShape() {
		return wispShape;
	}

	public void setWispShape(Pane wispShape) {
		this.wispShape = wispShape;
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