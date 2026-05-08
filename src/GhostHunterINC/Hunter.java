package GhostHunterINC;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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

    	 Pane hunterPane = new Pane(body);
    	 hunterPane.setPrefSize(40, 40);

    	 hunterShape = hunterPane;
    	 
    	 shape = new StackPane(hunterPane);
    	 shape.setPrefSize(40, 40);
    }

    @Override
    public void update(double dt) {
    	
    	double newX = x;
    	double newY = y;
    	
    	if(movingUp)
    		newY -= speed * dt;
    	if(movingDown)
    		newY += speed * dt;
    	if(movingRight)
    		newX += speed * dt;
    	if(movingLeft)
    		newX -= speed * dt;
    	
    	if (newX - 20 >= areaX && newX + 20 <= areaX + areaW)
    		x = newX;
    	if (newY - 20 >= areaY && newY + 20 <= areaY + areaH)
    		y = newY;
    	
    	hunterShape.setLayoutX(newX);
    	hunterShape.setLayoutY(newY);
    	
    	scanner.updatePosition();
    	scanner.update(dt);
    }

    @Override
    public void render(Pane pane) {
    	this.currentPane = pane;
    	if(!pane.getChildren().contains(hunterShape)) 
    		pane.getChildren().add(hunterShape);
    	scanner.render(pane);
    }

    @Override
	public void move(double dt) {
    	
	}
    
    public void takeDamage(int amount) {
       health -= amount;
       body.setFill(Color.RED);
       if (health <= 0)
    	   health = 0;
    }

    public void resetColor() {
        body.setFill(Color.ORANGE);
    }

    public void heal(int amount) {
       health += amount;
       if(health > maxHealth)
    	   health = maxHealth;
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

	@Override
	public Node getShape() {
		return hunterShape;
	}
	

}