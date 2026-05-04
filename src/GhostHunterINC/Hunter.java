package GhostHunterINC;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Hunter extends GameObject {

    private int health,maxHealth,score;
    private double areaX, areaY, areaW, areaH,speed;
    private boolean movingUp, movingDown, movingLeft, movingRight;
    private Scanner scanner;
    private Pane hunterShape;
    private Circle body;

    public Hunter(double x, double y, double areaX, double areaY, double areaW, double areaH) {
        this.x = x;
        this.y = y;
        this.areaX = areaX;
        this.areaY = areaY;
        this.areaW = areaW;
        this.areaH = areaH;
        this.maxHealth = ConfigManager.getMaxHealth();
        this.health = maxHealth;
        this.score = 0;
        this.speed = 200; 
        this.visible = true;
        this.scanner = new Scanner(this);
        createHunter();
    }

    private void createHunter() {
        body = new Circle(20, 20, 20, Color.ORANGE);
        hunterShape = new Pane(body);
        hunterShape.setPrefSize(40, 40);
        this.shape = body;
    }

    @Override
    public void update(double dt) {
		// TODO Auto-generated method stub
    }

    @Override
    public void render(Pane pane) {
		// TODO Auto-generated method stub
    }

    @Override
	public void move(double dt) {
		// TODO Auto-generated method stub
		
	}
    
    public void takeDamage(int amount) {
       
    }

    public void resetColor() {
        
    }

    public void heal(int amount) {
       
    }

    public void addScore(int points) {
        score += points;
    }
    
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public Pane getHunterShape() {
		return hunterShape;
	}

	public void setHunterShape(Pane hunterShape) {
		this.hunterShape = hunterShape;
	}

	public Circle getBody() {
		return body;
	}

	public void setBody(Circle body) {
		this.body = body;
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

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public boolean isMovingUp() {
		return movingUp;
	}

	public void setMovingUp(boolean movingUp) {
		this.movingUp = movingUp;
	}

	public boolean isMovingDown() {
		return movingDown;
	}

	public void setMovingDown(boolean movingDown) {
		this.movingDown = movingDown;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}
	

}