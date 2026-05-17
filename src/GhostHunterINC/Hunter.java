package GhostHunterINC;
//The whole class is implemented by 150124014_Mehmet_Veli_Kayikli.
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;


public class Hunter extends GameObject {
 
    private int score,trapCount;
    private double areaX, areaY, areaW, areaH, speed, health, maxHealth;
    private boolean movingUp, movingDown, movingLeft, movingRight;
    private Scanner scanner;
    private Circle body;
    private Circle hat;
    private Polygon badge;
    private Pane hunterPane;
    //Hunter constructor. Takes Hunter's and corners of the playable area's coordinates as parameter.
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
    
    //This method creates hunter.
    private void createHunter() {
        body = new Circle(20.0, 20.0, 20.0, Color.ORANGE);
        hat = new Circle(20.0, 20.0, 17.0);
        hat.setFill(Color.BLACK);
        badge = new Polygon();
        
        //Since badge is a hexagon, we can easily use trigonometry.
        double centerX = 20.0,centerY = 20.0,radius = 12.0,angle,cornerX,cornerY;
        for (int i = 0; i < 6; i++) {
            angle = Math.toRadians(i * 60); 
            cornerX = centerX + radius * Math.cos(angle);
            cornerY = centerY + radius * Math.sin(angle);
            
            badge.getPoints().addAll(cornerX, cornerY);
        }
        
        badge.setFill(Color.GOLD);
        sPane = new StackPane(body,hat,badge);
        setHunterPane(new Pane(sPane));
        sPane.setPrefSize(40, 40);
    }
    //We have to update scanner since it is connected to Hunter.
    @Override
    public void update(double dt) {
        scanner.update(dt);
    }
    //If hunter takes a TrapToken, we increment the trapCount by amount.
    public void addTraps(int amount) {
    	this.trapCount += amount;
    }
    //If trapCount is grater than 0, this method place a trap and decrement the the trapCount by one.
    public Trap placeTrap() {
    	if(trapCount > 0) {
    		trapCount--;
    		return new Trap(this.x, this.y);
    	}
    	return null;
    }
    //If there is no Hunter in the game, this method adds A hunter and render the scanner.
    @Override
    public void render(Pane pane) {
        this.currentPane = pane;
        if (!pane.getChildren().contains(sPane))
            pane.getChildren().add(sPane);
        scanner.render(pane);
    }
 
    @Override
    public void move(double dt) {}
    
    //This method reduce the Hunter's health by given amount and turn it's color to red. If it's health is less than 0, it equalizes it 0.
    public void takeDamage(double amount) {
        health -= amount;
        body.setFill(Color.RED);
        hat.setFill(Color.RED);
        badge.setFill(Color.RED);
        if (health <= 0) 
        	health = 0;
    }
    //This method turn Hunter's color back.
    public void resetColor() {
        body.setFill(Color.ORANGE);
        hat.setFill(Color.BLACK);
        badge.setFill(Color.GOLD);
    }
    //This method enhance the Hunter's health by given amount.
    public void heal(double amount) {
        health += amount;
        if (health > maxHealth) health = maxHealth;
    }
    //Returns Hunter's Pane.
    @Override
    public Node getShape() { 
    	return sPane; 
    }
    //Getters & Setters.
    public void addScore(int points) {
        score += points;
    }
 
    public double getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public double getMaxHealth() {
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

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public Circle getBody() {
		return body;
	}

	public void setBody(Circle body) {
		this.body = body;
	}

	public Pane getHunterPane() {
		return hunterPane;
	}

	public void setHunterPane(Pane hunterPane) {
		this.hunterPane = hunterPane;
	}

	public Circle getHat() {
		return hat;
	}

	public void setHat(Circle hat) {
		this.hat = hat;
	}

	public Polygon getBadge() {
		return badge;
	}

	public void setBadge(Polygon badge) {
		this.badge = badge;
	}
	
	public int getTrapCount() {
		return trapCount;
	}
	
	
}
 