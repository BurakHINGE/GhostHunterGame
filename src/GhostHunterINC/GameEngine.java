package GhostHunterINC;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.PauseTransition;

/*
 * General Purpose: Represent the core mechanics of the game.
 * It handles the main loops.
 * Including: start game loop, start timeline loop, start-stop-resume game loops, handling users input and hunter movements functions.
 */

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
	private Rectangle borderArea;
	private ImageView levelView;
	private VBox currentStopOverlay;
	
	//Letter direction keys
	private boolean isWPressed = false;
	private boolean isAPressed = false;
	private boolean isSPressed = false;
	private boolean isDPressed = false;
	
	//Cheat keys
	private boolean visibilityCheat = false;
	private boolean borderCheat = false;
	
	//Mouse Coordinates for scanner
	private double mouseX = 0;
	private double mouseY = 0;
	
	public GameEngine() {
		this.allEntities = new ArrayList<>();
        this.gameObjects = new Pane();
	}
	
	public void startGame(int level, Stage stage, SceneManager sceneManager) { //Starts the levels
		
		//Reset cheats before the new level
		this.visibilityCheat = false;
		this.borderCheat = false;
		
		this.currentLevel = level;
		this.allEntities = new ArrayList<>();
		this.gameObjects = new Pane();
		this.sceneManager = sceneManager;
		
		//Load background images
		Image level1Image = new Image("file:level1Picture.png");
		Image level2Image = new Image("file:level2Picture.png");
		Image level3Image = new Image("file:level3Picture.png");
		
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
			this.levelView = new ImageView(level1Image);
		}
		else if (level == 2) {
			areaX = ConfigManager.getLevel2PlayableAreaX();
			areaY = ConfigManager.getLevel2PlayableAreaY();
			areaW = ConfigManager.getLevel2PlayableAreaWidth();
			areaH = ConfigManager.getLevel2PlayableAreaHeight();
			remainingTime = ConfigManager.getLevel2Time();
			this.levelView = new ImageView(level2Image);
		}
		else { //Level 3
			areaX = ConfigManager.getLevel3PlayableAreaX();
			areaY = ConfigManager.getLevel3PlayableAreaY();
			areaW = ConfigManager.getLevel3PlayableAreaWidth();
			areaH = ConfigManager.getLevel3PlayableAreaHeight();
			remainingTime = ConfigManager.getLevel3Time();
			this.levelView = new ImageView(level3Image);
		}
		
		//Bind the image according to screen size
		levelView.fitWidthProperty().bind(stage.widthProperty());
		levelView.fitHeightProperty().bind(stage.heightProperty());
		
		//Set hunter's start position
		double startX = areaX + (areaW / 2);
		double startY = areaY + (areaH / 2);
		
		//Create playable area
		this.borderArea = new Rectangle();
		this.borderArea.setWidth(areaW);
		this.borderArea.setHeight(areaH);
		this.borderArea.setFill(Color.TRANSPARENT);
		this.borderArea.setStroke(Color.WHITE);
		this.borderArea.setStrokeWidth(4);
		this.borderArea.setVisible(false);
		
		this.gameObjects.getChildren().add(borderArea);
		
		//Instantiate the hunter
		this.hunter = new Hunter(startX, startY, areaX, areaY, areaW, areaH);
		this.hunter.getScanner().render(this.gameObjects);
		this.gameObjects.getChildren().add(this.hunter.getShape());
		this.hunter.getScanner().render(this.gameObjects);
		
		//Initialize the managers
		this.levelManager = new LevelManager(this.gameObjects, this.allEntities);
		this.hudManager = new HUDManager(level);
		
		//Load enemies and tokens
		this.levelManager.loadEntities(level);
		this.levelManager.startTokenSpawn(level);
		
		//Bind HUD elements according to screen size
		Pane hudPane = this.hudManager.getHudPane();
		hudPane.prefWidthProperty().bind(stage.widthProperty());
		hudPane.prefHeightProperty().bind(stage.heightProperty());
		
		//Objects cannot exit from the playable area
		this.gameObjects.setMaxSize(areaW, areaH);
		this.gameObjects.setPrefSize(areaW, areaH);
		
		StackPane levelPane = new StackPane();
		levelPane.getChildren().addAll(levelView, this.gameObjects, hudPane);
		StackPane.setAlignment(this.gameObjects, Pos.CENTER);
		
		this.gameScene = new Scene(levelPane, stage.getWidth(), stage.getHeight());
		
		startCountdown();
		handleInput();
		stage.setScene(this.gameScene);
		stage.setFullScreen(true);
		
		levelPane.requestFocus();
		
		startTimelineLoop();
	}
	
	private void startTimelineLoop() { //Handle physics, updates and collisions. Runs 60 times per second

		//Prevent multiple loop
		if (gameLoop != null) {
			gameLoop.stop();
		}
		
		gameLoop = new Timeline(new KeyFrame(Duration.millis(16), e -> { 
			
			//Update hunter
			hunterMovement();
			hunter.getScanner().updatePosition(); 
		    hunter.getScanner().update(0.016);
		    
		    //Update HUD manager
			hudManager.updateHealthBar(hunter.getHealth(), 100);
			hudManager.updateVacuumBar(hunter.getScanner().getEnergy(), hunter.getScanner().getMaxEnergy());
			hudManager.updateTime(remainingTime);
			hudManager.updateTrapCount(hunter.getTrapCount());
			boolean isTouching = false;
			
			//Safely remove objects
			Iterator<GameObject> iterator = allEntities.iterator();
			
			double areaW = borderArea.getWidth();
	        double areaH = borderArea.getHeight();
			
	        //Process entities and keep them within bounds
		    while (iterator.hasNext()) {
		        GameObject obj = iterator.next();
		        
		        if (obj instanceof Ripper) {
		            ((Ripper) obj).setAreaH(areaH - 70);
		            ((Ripper) obj).setAreaW(areaW - 70);
		        } else if (obj instanceof Ghost) {
		            ((Ghost) obj).setAreaH(areaH - 30);
		            ((Ghost) obj).setAreaW(areaW - 30);
		        } else if (obj instanceof Wisp) {
		            ((Wisp) obj).setAreaH(areaH - 70);
		            ((Wisp) obj).setAreaW(areaW - 70);
		        }
		            		     	        
		        obj.update(0.016); 
		        
		        //Trap
		        if(obj instanceof Trap) {
		        	if(((Trap)obj).isExpired()) {
		        		gameObjects.getChildren().remove(obj.getShape());
		        		iterator.remove();
		        		continue;
		        	}
		        }
		        //Other tokens
		        else if (obj instanceof Token) {
		            if (hunter.checkCollision(obj)) {
		            	((Token)obj).applyEffect(hunter);
		                gameObjects.getChildren().remove(obj.getShape());
		                iterator.remove(); 
		            }
		        }
		        //Entities
		        else if (obj instanceof ParanormalEntity) {
		            ParanormalEntity entity = (ParanormalEntity) obj;
		            
		            //If entities shrink %30 they die
		            if(obj.getShape().getScaleX() <= 0.30) {
		                gameObjects.getChildren().remove(obj.getShape());
		                iterator.remove();
		                
		                if(obj instanceof Ghost) {
		                	hunter.addScore(ConfigManager.ghostPoints);
		                }
		                else if(obj instanceof Ripper) {
		                	hunter.addScore(ConfigManager.ripperPoints);
		                }
		                else if(obj instanceof Wisp) {
		                	hunter.addScore(ConfigManager.wispPoints);
		                }
		                
		                hudManager.updateScore(hunter.getScore());
		                continue; 
		            }
		            
		            //Trap stun mechanics
		            if(!entity.isStunned()) {
		                for(GameObject x: allEntities) {
		                    if(x instanceof Trap) {
		                        Trap trap = (Trap) x;
		                        if(!trap.isTriggered() && entity.getShape().getBoundsInParent().intersects(trap.getShape().getBoundsInParent())) {
		                            trap.trigger();
		                            entity.setStunned(true);
		                            
		                            PauseTransition stunTimer = new PauseTransition(Duration.seconds(ConfigManager.trapTokenDuration));
		                            stunTimer.setOnFinished(ey -> {
		                                entity.setStunned(false);
		                                trap.setExpired(true);
		                            });
		                            stunTimer.play();
		                        }
		                    }
		                }
		            }
		            
		            //Damage mechanics
		            if(hunter.checkCollision(obj)) {
		                isTouching = true; 
		                hunter.takeDamage(0.5); 
		            }
		            
		            //150124014_Mehmet_Veli_Kayikli_ Vacuum mechanics
		            boolean isInsideScanner = hunter.getScanner().isActive() && hunter.getScanner().intersects(obj);
		           
		            if(isInsideScanner) {
		            	entity.setBeingVacuumed(true);
                        entity.vacuumEffect(0.016, hunter.getX() + 20, hunter.getY() + 20);
                        entity.getShape().toFront();
		            }
		            else {
		            	entity.setBeingVacuumed(false);
		            	entity.inverseVacuuumEffect();
		            }
		            
		            //Visibility mechanics
		            boolean shouldBeVisible = false;
                    
                    if (visibilityCheat) shouldBeVisible = true;
                    if (entity.isRevealedByToken()) shouldBeVisible = true;
                    if (entity.isStunned()) shouldBeVisible = true;
                    if (isInsideScanner) shouldBeVisible = true;
                    
                    entity.getShape().setVisible(shouldBeVisible);
		        }
		        
		        //Update hunter damage situation
		        if (isTouching) {
		            hunter.getBody().setFill(Color.RED);
		            hunter.getHat().setFill(Color.RED);
		            hunter.getBadge().setFill(Color.RED);
		        } else {
		            hunter.resetColor();
		        }
		    }
		    //Always keep the hunter rendered on top of other objects
		    hunter.getShape().toFront();
		    
		    //Health mechanics
		    if(hunter.getHealth() <= 0) {
		    	stopGame();
		    	StackPane root = (StackPane) gameScene.getRoot();
		    	
		    	if (!root.getChildren().contains(sceneManager.losePanel(1, hunter.getScore()))) {
		            root.getChildren().add(sceneManager.losePanel(1, hunter.getScore()));
		        }
		        return;
		    }
		    
		    //Check win conditions
		    boolean isGhostLeft = false;
		    
		    for(GameObject obj: allEntities) {
		    	if(obj instanceof ParanormalEntity) {
		    		isGhostLeft = true;
		    		break;
		    	}
		    }
		    
		    if(!isGhostLeft) {
		    	stopGame();
		    	StackPane root = (StackPane) gameScene.getRoot();
		    	
		    	if (!root.getChildren().contains(sceneManager.winPanel(hunter.getScore()))) {
		            root.getChildren().add(sceneManager.winPanel(hunter.getScore()));
		        }	
		    }  
		}));

		gameLoop.setCycleCount(Timeline.INDEFINITE); 
		gameLoop.play(); 
	}
	
	private void handleInput() { //Handle users inputs
		
		gameScene.setOnKeyPressed(e -> { //Activate key when pressed
			
			StackPane root = (StackPane) gameScene.getRoot();
			
			//Escape logics
			if (e.getCode() == KeyCode.ESCAPE) {
				e.consume();

				//If the pause menu is active, resume the game
				if (currentStopOverlay != null && root.getChildren().contains(currentStopOverlay)) {
					resumeGame();
					return;
				}
				
				//If another menu enforce the full screen
				if (root.getChildren().size() > 3) {
					if (sceneManager != null && sceneManager.getStage() != null) {
						sceneManager.getStage().setFullScreen(true);
					}
					return;
				}
			}
			
			switch (e.getCode()) {
			
				//Movement keys
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
				
				//Action Keys
				case SPACE: {
					if (gameLoop != null && gameLoop.getStatus() == Status.RUNNING) {
				        hunter.getScanner().activate();
				    }
		            break;
		        }
				case ESCAPE: {
					pauseGame();
					break;
				}
				case R: {
					if (gameLoop != null && gameLoop.getStatus() == Status.RUNNING) {
				        Trap newTrap = hunter.placeTrap();
				        if(newTrap != null) {
				            allEntities.add(newTrap);
				            gameObjects.getChildren().add(newTrap.getShape());
				            
				            newTrap.getShape().toBack();
				            borderArea.toBack();
				            
				            hunter.getShape().toFront();
				            hunter.getScanner().getScanner().toFront();
				        }
				    }
					break;
				}
				
				//Cheat Keys
				case G: { 
					visibilityCheat = !visibilityCheat;
					break;
				}
				case B: {
					borderCheat = !borderCheat;
					borderArea.setVisible(borderCheat);
					break;
				}
			}			
		});
		
		gameScene.setOnKeyReleased(e -> { //Deactivate key when released
			switch (e.getCode()) {
				
				//Movement keys
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
				
				//Action keys
				case SPACE: {
		            hunter.getScanner().deactivate(); 
		            break;
		        }
			}			
		});
		
		//150124014_Mehmet_Veli_Kayikli_ Mouse listener
		gameScene.setOnMouseMoved(e -> {
			javafx.geometry.Point2D localMouse = gameObjects.sceneToLocal(e.getX(), e.getY());
		    
		    if (localMouse != null) {
		        mouseX = localMouse.getX();
		        mouseY = localMouse.getY();
		        hunter.getScanner().pointToMouse(mouseX, mouseY);
		    }
		});

		//150124014_Mehmet_Veli_Kayikli_ Scanner rotate to mouse even if user clicks and drags
		gameScene.setOnMouseDragged(e -> {
			javafx.geometry.Point2D localMouse = gameObjects.sceneToLocal(e.getX(), e.getY());
		    
		    if (localMouse != null) {
		        mouseX = localMouse.getX();
		        mouseY = localMouse.getY();
		        hunter.getScanner().pointToMouse(mouseX, mouseY);
		    }
		});
	}
	
	public void pauseGame() { //Pause the game loop
		
		//Prevents stacking multiple panels
		if (sceneManager != null && gameScene != null) {
			StackPane root = (StackPane) gameScene.getRoot();

			if (currentStopOverlay != null && root.getChildren().contains(currentStopOverlay)) {
				return; 
			}

			//Freeze game logic
			if (gameLoop != null) gameLoop.pause(); 
			if (countdown != null) countdown.pause();
			if (levelManager != null) levelManager.pauseTokenSpawn(); 
			
			//Reset inputs the hunter when game paused
			isWPressed = false;
			isAPressed = false;
			isSPressed = false;
			isDPressed = false;
			
			if (hunter != null && hunter.getScanner() != null) {
				hunter.getScanner().deactivate();
			}

			currentStopOverlay = sceneManager.stopGamePanel();
			root.getChildren().add(currentStopOverlay);	
		}
		
		//Enforce full screen
		Stage stage = (Stage) gameScene.getWindow();
		if (stage != null) {
			stage.setFullScreen(true);
		}
	}

	public void resumeGame() { //Remove pause overlay and resumes the game loops
		
		if (gameScene != null && currentStopOverlay != null) {
			StackPane root = (StackPane) gameScene.getRoot();

			root.getChildren().remove(currentStopOverlay);
			currentStopOverlay = null;
			
			root.requestFocus();
		}
		
		//Enforce full screen
		if (gameScene != null && gameScene.getWindow() != null) {
			Stage stage = (Stage) gameScene.getWindow();
			stage.setFullScreen(true);
		}

		//Resume game play loops
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
	
	private void hunterMovement() { //Calculate hunter's movement
		
		//Base movement speed
		double speed = 5.0;
		
		//Dynamic coordinates
		double newX = hunter.getX();
		double newY = hunter.getY();
		
		//Movement
		//Prevents extra speed when hunter is moving diagonal
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
		double minX = borderArea.getX();
		double minY = borderArea.getY();
		double maxX = borderArea.getX() + borderArea.getWidth();
		double maxY = borderArea.getY() + borderArea.getHeight();
		
		double hunterSize = 40.0;
		
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
	
	private void startCountdown() { //Manage levels timers
		
		//Clean the previous timers.
		if(countdown != null) {
			countdown.stop();
		}
		
		countdown = new Timeline(new KeyFrame(Duration.seconds(1), e-> {
			remainingTime -= 1;
			hudManager.updateTime(remainingTime);
			
			//Check timeout
			if(remainingTime <= 0) {
				countdown.stop();
				stopGame();
				
				StackPane root = (StackPane) gameScene.getRoot();
	            root.getChildren().add(sceneManager.losePanel(2, hunter.getScore()));
			}
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
	
	public int getRemainingTime() {
		return remainingTime;
	}
	
	public int getCurrentLevel() {
		return currentLevel;
	}
	
	public void setRemainingTime(int remainingTime) {
		this.remainingTime = remainingTime;
	}
	
	public void setCurrentlevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}
}