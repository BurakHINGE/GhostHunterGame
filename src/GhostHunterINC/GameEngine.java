package GhostHunterINC;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameEngine {
	
	private ArrayList<GameObject> allEntities;
	private Pane gameObjects;
	private Scene gameScene;
	private Timeline gameLoop;
	private LevelManager levelManager;
	private Hunter hunter;
	private int currentLevel;
	private HUDManager hudManager;
	private int remainingTime;
	private Timeline countdown;
	private SceneManager sceneManager;
	private javafx.scene.shape.Rectangle borderArea;
	
	//Letter direction keys
	private boolean isWPressed = false;
	private boolean isAPressed = false;
	private boolean isSPressed = false;
	private boolean isDPressed = false;
	
	//Cheat keys
	private boolean visibilityCheat = false;
	
	//Mouse Coordinates
	private double mouseX = 0;
	private double mouseY = 0;
	
	public GameEngine() {
		this.allEntities = new ArrayList<>();
	}
	
	public GameEngine (Pane gameObjects, Scene gameScene, LevelManager levelManager, Hunter hunter, ArrayList<GameObject> allEntities) {
		this.gameObjects = gameObjects;
		this.gameScene = gameScene;
		this.hunter = hunter;
		gameObjects = new Pane();
		allEntities = new ArrayList<>();
		levelManager = new LevelManager(gameObjects, allEntities);
		
		handleInput();
	}
	
	public void startGame(int level, Stage stage, SceneManager sceneManager) {
		this.currentLevel = level;
		
		this.allEntities = new ArrayList<>();
		
		this.gameObjects = new Pane();
		
		this.sceneManager = sceneManager;
		
		double areaX;
		double areaY;
		double areaW;
		double areaH;
		
		//Get special area informations
		if (level == 1) {
			areaX = ConfigManager.getLevel1PlayableAreaX();
			areaY = ConfigManager.getLevel1PlayableAreaY();
			areaW = ConfigManager.getLevel1PlayableAreaWidth();
			areaH = ConfigManager.getLevel1PlayableAreaHeight();
			remainingTime = ConfigManager.getLevel1Time();
		}
		else if (level == 2) {
			areaX = ConfigManager.getLevel2PlayableAreaX();
			areaY = ConfigManager.getLevel2PlayableAreaY();
			areaW = ConfigManager.getLevel2PlayableAreaWidth();
			areaH = ConfigManager.getLevel2PlayableAreaHeight();
			remainingTime = ConfigManager.getLevel2Time();
		}
		else { //Level 3
			areaX = ConfigManager.getLevel3PlayableAreaX();
			areaY = ConfigManager.getLevel3PlayableAreaY();
			areaW = ConfigManager.getLevel3PlayableAreaWidth();
			areaH = ConfigManager.getLevel3PlayableAreaHeight();
			remainingTime = ConfigManager.getLevel3Time();
		}
		
		//Set hunter's start position
		double startX = areaX + (areaW / 2);
		double startY = areaY + (areaH / 2);
		
		this.borderArea = new javafx.scene.shape.Rectangle(areaX, areaY, areaW, areaH);
		this.borderArea.setFill(Color.TRANSPARENT);
		this.borderArea.setStroke(Color.WHITE);
		this.borderArea.setStrokeWidth(4);
		this.borderArea.setVisible(false);
		
		this.gameObjects.getChildren().add(borderArea);
		
		this.hunter = new Hunter(startX, startY, areaX, areaY, areaW, areaH);
		
		this.gameObjects.getChildren().add(this.hunter.getShape());
		
		this.levelManager = new LevelManager(this.gameObjects, this.allEntities);
		this.hudManager = new HUDManager(level);
		
		this.levelManager.loadEntities(level);
		
		this.levelManager.startTokenSpawn(level);
		
		Pane hudPane = this.hudManager.getHudPane();
		hudPane.prefWidthProperty().bind(stage.widthProperty());
		hudPane.prefHeightProperty().bind(stage.heightProperty());
		
		StackPane levelPane = new StackPane();
		levelPane.getChildren().addAll(this.gameObjects, hudPane);
		StackPane.setAlignment(this.gameObjects, Pos.TOP_LEFT);
		
		this.gameScene = new Scene(levelPane, stage.getWidth(), stage.getHeight());
		startCountdown();
		handleInput();
		stage.setScene(this.gameScene);
		
		levelPane.requestFocus();
		
		startTimelineLoop();
	}
	
	public void startTimelineLoop() {
		
		if (gameLoop != null) {
			gameLoop.stop();
		}
		
		gameLoop = new Timeline(new KeyFrame(Duration.millis(16), e -> {
			hunterMovement();
			hudManager.updatedHealthBar(hunter.getHealth(), 100);
			hudManager.updateTime(remainingTime);
			boolean isTouching = false;
			
			java.util.Iterator<GameObject> iterator = allEntities.iterator();
			
		    while (iterator.hasNext()) {
		        GameObject obj = iterator.next();
		        obj.update(0); 

		        if (obj instanceof Token) {
		            if (hunter.checkCollision(obj)) {
		            	((Token)obj).applyEffect(hunter);
		                gameObjects.getChildren().remove(obj.getShape());
		                iterator.remove(); 
		            }
		        }
		        
		        else if (obj instanceof ParanormalEntity) {
		            if (hunter.checkCollision(obj)) {
		                isTouching = true; 
		                hunter.takeDamage(2); 
		            }
		        }
		        
		        if (isTouching) {
		            hunter.getBody().setFill(Color.RED);
		        } else {
		            hunter.resetColor();
		        }
		        
		        if(hunter.getScanner().isActive() && hunter.getScanner().intersects(obj)) {
            		((ParanormalEntity) obj).setBeingVacuumed(true);
            		((ParanormalEntity) obj).vacuumEffect(0.016, hunter.getX() + 20, hunter.getY() + 20);
            	} else {
            		((ParanormalEntity) obj).setBeingVacuumed(false);
            		((ParanormalEntity) obj).inverseVacuuumEffect(0.016);
            	}
		    }
	        
		}));

		gameLoop.setCycleCount(Timeline.INDEFINITE); 
		
		gameLoop.play(); 
	}
	
	public void handleInput() {
		
		gameScene.setOnKeyPressed(e -> { //Activate key when pressed
			switch (e.getCode()) {
				case W: {
					isWPressed = true;
					break;
				}
				case A: {
					isAPressed = true;
					break;
				}
				case S: {
					isSPressed = true;
					break;
				}
				case D: {
					isDPressed = true;
					break;
				}
				case UP: {
					isWPressed = true;
					break;
				}
				case LEFT: {
					isAPressed = true;
					break;
				}
				case DOWN: {
					isSPressed = true;
					break;
				}
				case RIGHT: {
					isDPressed = true;
					break;
				}
				case ESCAPE: {
					if (gameLoop != null) {
						gameLoop.stop();
						
						Stage stage = (Stage) gameScene.getWindow();
						stage.setScene(sceneManager.stopGamePanel());
					}
				}
				case G: { 
					
					visibilityCheat = !visibilityCheat;
					
					for (GameObject obj : allEntities) {
						if (obj instanceof ParanormalEntity) { 
							obj.getShape().setVisible(visibilityCheat);
						}
					}
					System.out.println("Hile Durumu: " + (visibilityCheat ? "AÇIK" : "KAPALI"));
					break;
				}
				//Add cheat key
			}			
		});
		
		gameScene.setOnKeyReleased(e -> { //Deactivate key when pressed
			switch (e.getCode()) {
				case W: {
					isWPressed = false;
					break;
				}
				case A: {
					isAPressed = false;
					break;
				}
				case S: {
					isSPressed = false;
					break;
				}
				case D: {
					isDPressed = false;
					break;
				}
				case UP: {
					isWPressed = false;
					break;
				}
				case LEFT: {
					isAPressed = false;
					break;
				}
				case DOWN: {
					isSPressed = false;
					break;
				}
				case RIGHT: {
					isDPressed = false;
					break;
				}
				//Add cheat key
			}			
		});
		
		gameScene.setOnMouseMoved(e -> {
			mouseX = e.getX();
			mouseY = e.getY();
		});

		gameScene.setOnMouseDragged(e -> {
			mouseX = e.getX();
			mouseY = e.getY();
		});
	}
	
	public void pauseGame() {
		if (gameLoop != null) {
			gameLoop.pause(); 
		}
		if (countdown != null) {
			countdown.pause();
		}
		if (levelManager != null) {
			levelManager.pauseTokenSpawn(); 
		}
		
		if (sceneManager != null) {
			Stage stage = (Stage) gameScene.getWindow();
			stage.setScene(sceneManager.stopGamePanel());
		}
	}

	public void resumeGame() {
		if (gameLoop != null) {
			gameLoop.play();
		}
		if (countdown != null) {
			countdown.play();
		}
		if (levelManager != null) {
			levelManager.resumeTokenSpawn();
		}
	}
	
	public void hunterMovement() {
		
		double speed = 5.0;
		
		//Dynamic coordinates
		double newX = hunter.getX();
		double newY = hunter.getY();
		
		//Movement
		if (isWPressed) {
			if (isAPressed) {
				newX -= speed / Math.sqrt(2);
				newY -= speed / Math.sqrt(2);
			}
			else if (isDPressed) {
				newX += speed / Math.sqrt(2);
				newY -= speed / Math.sqrt(2);
			}
			else if (isSPressed) {
				newY += 0;
			}
			else {
				newY -= speed;
			}
		}
		else if (isAPressed) {
			if (isWPressed) {
				newX -= speed / Math.sqrt(2);
				newY -= speed / Math.sqrt(2);
			}
			else if (isSPressed) {
				newX -= speed / Math.sqrt(2);
				newY += speed / Math.sqrt(2);
			}
			else if (isDPressed) {
				newX += 0;
			}
			else {
				newX -= speed;
			}
		}
		else if (isSPressed) {
			if (isDPressed) {
				newX += speed / Math.sqrt(2);
				newY += speed / Math.sqrt(2);
			}
			else {
				newY += speed;
			}
		}
		else if (isDPressed) {
			newX += speed;
		}
		
		//Fixed coordinates
		double minX = hunter.getAreaX();
		double minY = hunter.getAreaY();
		double maxX = hunter.getAreaX() + hunter.getAreaW();
		double maxY = hunter.getAreaY() + hunter.getAreaH();
		
		double hunterSize = 40.0;
		
		double hunterWidth = 40.0;
		double hunterHeight = 40.0;
		
		//Check the borders
		if (newX < minX) { 
			newX = minX;
		}
		if (newX + hunterSize > maxX) {
			newX = maxX - hunterSize;
		}
		if (newY < minY) {
			newY = minY;
		}
		if (newY + hunterSize > maxY) {
			newY = maxY - hunterSize;
		}
		
		//Set coordinates for hunter
		hunter.setX(newX);
		hunter.setY(newY);
		
		hunter.getShape().setLayoutX(newX);
	    hunter.getShape().setLayoutY(newY);
		
	}
	
	private void startCountdown() {
		//Clean the previous timers.
		if(countdown != null) {
			countdown.stop();
		}
		
		countdown = new Timeline(new KeyFrame(Duration.seconds(1), e-> {
			remainingTime -= 1;
			hudManager.updateTime(remainingTime);
			
			if(remainingTime <= 0) {
				countdown.stop();
				stopGame();
				System.out.println("SÜRE BİTTİ !!!");			}
		}));
		
		countdown.setCycleCount(Timeline.INDEFINITE);
		countdown.play();
	}
	
	public void stopGame() { //Stop the game
		gameLoop.stop();
		countdown.stop();
		levelManager.stopTokenSpawn();
	}

	public ArrayList<GameObject> getEnemies() {
		return allEntities;
	}

	public Pane getGameObjects() {
		return gameObjects;
	}

	public Scene getGameScene() {
		return gameScene;
	}

	public Timeline getGameLoop() {
		return gameLoop;
	}
	
	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setEnemies(ArrayList<GameObject> allEntities) {
		this.allEntities = allEntities;
	}

	public void setGameObjects(Pane gameObjects) {
		this.gameObjects = gameObjects;
	}

	public void setGameScene(Scene gameScene) {
		this.gameScene = gameScene;
	}

	public void setGameLoop(Timeline gameLoop) {
		this.gameLoop = gameLoop;
	}
	
	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}
}
