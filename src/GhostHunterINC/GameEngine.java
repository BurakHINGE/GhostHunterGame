package GhostHunterINC;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.PauseTransition;

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
	private ImageView levelView;
	
	//Letter direction keys
	private boolean isWPressed = false;
	private boolean isAPressed = false;
	private boolean isSPressed = false;
	private boolean isDPressed = false;
	
	//Cheat keys
	private boolean visibilityCheat = false;
	private boolean borderCheat = false;
	
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
		
		
		levelView.fitWidthProperty().bind(stage.widthProperty());
		levelView.fitHeightProperty().bind(stage.heightProperty());
		
		//Set hunter's start position
		double startX = areaX + (areaW / 2);
		double startY = areaY + (areaH / 2);
		
		this.borderArea = new javafx.scene.shape.Rectangle();
		this.borderArea.setWidth(areaW);
		this.borderArea.setHeight(areaH);
		
		this.borderArea.setFill(Color.TRANSPARENT);
		this.borderArea.setStroke(Color.WHITE);
		this.borderArea.setStrokeWidth(4);
		
		this.gameObjects.getChildren().add(borderArea);
		
		this.hunter = new Hunter(startX, startY, areaX, areaY, areaW, areaH);
		
		this.hunter.getScanner().render(this.gameObjects);
		
		this.gameObjects.getChildren().add(this.hunter.getShape());
		this.hunter.getScanner().render(this.gameObjects);
		this.levelManager = new LevelManager(this.gameObjects, this.allEntities);
		this.hudManager = new HUDManager(level);
		
		this.levelManager.loadEntities(level);
		
		this.levelManager.startTokenSpawn(level);
		
		Pane hudPane = this.hudManager.getHudPane();
		hudPane.prefWidthProperty().bind(stage.widthProperty());
		hudPane.prefHeightProperty().bind(stage.heightProperty());
		
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
	
	public void startTimelineLoop() {
		Stage stage = new Stage();
		if (gameLoop != null) {
			gameLoop.stop();
		}
		
		gameLoop = new Timeline(new KeyFrame(Duration.millis(16), e -> {
			hunterMovement();
			hunter.getScanner().updatePosition(); 
		    hunter.getScanner().update(0.016);
			hudManager.updateHealthBar(hunter.getHealth(), 100);
			hudManager.updateVacuumBar(hunter.getScanner().getEnergy(), hunter.getScanner().getMaxEnergy());
			hudManager.updateTime(remainingTime);
			boolean isTouching = false;
			
			java.util.Iterator<GameObject> iterator = allEntities.iterator();
			
			double areaW = borderArea.getWidth();
	        double areaH = borderArea.getHeight();
			
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
		        
		        if(obj instanceof Trap) {
		        	if(((Trap)obj).isExpired()) {
		        		gameObjects.getChildren().remove(obj.getShape());
		        		iterator.remove();
		        		continue;
		        	}
		        }

		        else if (obj instanceof Token) {
		            if (hunter.checkCollision(obj)) {
		            	((Token)obj).applyEffect(hunter);
		                gameObjects.getChildren().remove(obj.getShape());
		                iterator.remove(); 
		            }
		        }
		        
		        else if (obj instanceof ParanormalEntity) {
		            ParanormalEntity entity = (ParanormalEntity) obj;
		            
		            if(obj.getShape().getScaleX() <= 0.30) {
		                gameObjects.getChildren().remove(obj.getShape());
		                iterator.remove();
		                hunter.addScore(100);
		                hudManager.updateScore(hunter.getScore());
		                continue; 
		            }

		            if(!entity.isStunned()) {
		                for(GameObject x: allEntities) {
		                    if(x instanceof Trap) {
		                        Trap trap = (Trap) x;
		                        if(!trap.isTriggered() && entity.getShape().getBoundsInParent().intersects(trap.getShape().getBoundsInParent())) {
		                            trap.trigger();
		                            entity.setStunned(true);
		                            
		                            entity.setRevealedByToken(true);
		                            entity.getShape().setVisible(true);
		                            
		                            PauseTransition stunTimer = new PauseTransition(Duration.seconds(ConfigManager.trapTokenDuration));
		                            stunTimer.setOnFinished(ey -> {
		                                entity.setStunned(false);
		                                entity.setRevealedByToken(false);
		                                trap.setExpired(true);
		                            });
		                            stunTimer.play();
		                        }
		                    }
		                }
		            }
		            if(hunter.checkCollision(obj)) {
		                isTouching = true; 
		                hunter.takeDamage(0.5); 
		            }
		            if(hunter.getScanner().isActive() && hunter.getScanner().intersects(obj)) {
		                entity.setBeingVacuumed(true);
		                entity.getShape().setVisible(true);
		                entity.getShape().toFront();
		                entity.vacuumEffect(0.016, hunter.getX() + 20, hunter.getY() + 20);
		            } else {
		                if(!visibilityCheat && !entity.isRevealedByToken()) {
		                    obj.getShape().setVisible(false);
		                }
		                entity.setBeingVacuumed(false);
		                entity.inverseVacuuumEffect(0.016);
		            }
		        }
		        
		        if (isTouching) {
		            hunter.getBody().setFill(Color.RED);
		            hunter.getHat().setFill(Color.RED);
		            hunter.getBadge().setFill(Color.RED);
		        } else {
		            hunter.resetColor();
		        }
		    }
		    hunter.getShape().toFront();
		    
		    if(hunter.getHealth() <= 0) {
		    	stopGame();
		    	StackPane root = (StackPane) gameScene.getRoot();
		    	
		    	if (!root.getChildren().contains(sceneManager.losePanel(1, hunter.getScore()))) {
		            root.getChildren().add(sceneManager.losePanel(1, hunter.getScore()));
		        }
		        return;
		    }
		    
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
				case SPACE: {
		            hunter.getScanner().activate();
		            break;
		        }
				case ESCAPE: {
					pauseGame();
					break;
				}
				case G: { 
					
					visibilityCheat = !visibilityCheat;
					
					for (GameObject obj : allEntities) {
						if (obj instanceof ParanormalEntity) { 
							obj.getShape().setVisible(visibilityCheat);
						}
					}
					break;
				}
				case B: {
					
					borderCheat = !borderCheat;
					
					borderArea.setVisible(borderCheat);
					break;
				}
				case R: {
					Trap newTrap = hunter.placeTrap();
					if(newTrap != null) {
						allEntities.add(newTrap);
						gameObjects.getChildren().add(newTrap.getShape());
						
						newTrap.getShape().toBack();
						borderArea.toBack();
						
						hunter.getShape().toFront();
						hunter.getScanner().getScanner().toFront();
					}
					break;
				}
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
				case SPACE: {
		            hunter.getScanner().deactivate(); 
		            break;
		        }
				//Add cheat key
			}			
		});
		
		gameScene.setOnMouseMoved(e -> {
			javafx.geometry.Point2D localMouse = gameObjects.sceneToLocal(e.getX(), e.getY());
		    
		    if (localMouse != null) {
		        mouseX = localMouse.getX();
		        mouseY = localMouse.getY();
		        hunter.getScanner().pointToMouse(mouseX, mouseY);
		    }
		});

		gameScene.setOnMouseDragged(e -> {
			javafx.geometry.Point2D localMouse = gameObjects.sceneToLocal(e.getX(), e.getY());
		    
		    if (localMouse != null) {
		        mouseX = localMouse.getX();
		        mouseY = localMouse.getY();
		        hunter.getScanner().pointToMouse(mouseX, mouseY);
		    }
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
		double minX = borderArea.getX();
		double minY = borderArea.getY();
		double maxX = borderArea.getX() + borderArea.getWidth();
		double maxY = borderArea.getY() + borderArea.getHeight();
		
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
