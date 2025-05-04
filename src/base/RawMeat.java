package base;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import logic.BoilSnapLogic;

public abstract class RawMeat extends Ingredient implements Grillable,Boilable {
	private boolean isGrilled = false;
	private boolean isBurnt = false;
	
	private CookingState frontState = CookingState.RAW;
	private CookingState backState = CookingState.RAW;
	private boolean isFrontDown = false; 
	
	private Image backImage;
	
    private static final double GRILL_X_MIN = 535;
    private static final double GRILL_X_MAX = 690;
    private static final double GRILL_Y_MIN = 130;
    private static final double GRILL_Y_MAX = 280;
    		
	private Thread grillingThread;
    private boolean isGrilling;
    
	public RawMeat(String name, Image frontImage, Image backImage) {
		super(name, frontImage);
		
		this.backImage = backImage;
		
		this.setOnMousePressed(event -> {
			mouseX = event.getSceneX() - this.getTranslateX();
            mouseY = event.getSceneY() - this.getTranslateY();
            this.toFront();
            
            BoilSnapLogic.getInstance().clearOccupant(this);
            
            stopBoiling();
            if (grillingThread != null) {
            	grillingThread.interrupt();
            }
            
            this.getScene().setOnKeyPressed(keyEvent -> {
                if (keyEvent.getCode() == KeyCode.SPACE) {
                    flip();
                    keyEvent.consume();
                }
            });
            event.consume();
		});
		
		this.setOnMouseReleased(event -> {
			
			double centerX = getBoundsInParent().getMinX()
					+ getBoundsInParent().getWidth() / 2;
            double centerY = getBoundsInParent().getMinY()
            		+ getBoundsInParent().getHeight() / 2;
            boolean isOnGrill = centerX >= GRILL_X_MIN
            		&& centerX <= GRILL_X_MAX
            		&& centerY >= GRILL_Y_MIN
            		&& centerY <= GRILL_Y_MAX;
            
            if (isOnGrill && this instanceof Grillable) {
            	lastPosX = this.getTranslateX();
                lastPosY = this.getTranslateY();
            	startGrilling();
            } else {
                stopGrilling();
                
                if (this instanceof Boilable && BoilSnapLogic.getInstance().snapToNearest(this)) {
                	lastPosX = this.getTranslateX();
                    lastPosY = this.getTranslateY();
                	startBoiling();
	            } else {
	            	goBack();
	            }
            }
            event.consume();
        });
	}
	
	public void goBack() {
		if (isGrilled() || isBoiled()) {
			this.setTranslateX(lastPosX);
            this.setTranslateY(lastPosY);
		} else {
			this.setTranslateX(0);
            this.setTranslateY(0);
		}
	}

    
	public void startGrilling() {
        isGrilling = true;
        grillingThread = new Thread(() -> {
            try {
            	if (isGrilling && !theDownSideIsGrilled()) {
	                Thread.sleep(1000);
	                System.out.println("tick");
	                Thread.sleep(1000);
	                System.out.println("tick");
	                Thread.sleep(1000);
	                System.out.println("tick");
	                Thread.sleep(1000);
	                System.out.println("tick");
	
	                Platform.runLater(() -> {
	                	grill();
	                });
            	}
            	
                if (isGrilling && !isBurnt()) {
                	Thread.sleep(1000);
                    System.out.println("tick");
                    Thread.sleep(1000);
                    System.out.println("tick");
                    Thread.sleep(1000);
                    System.out.println("tick");
                    Thread.sleep(1000);
                    System.out.println("tick");
                    
                    Platform.runLater(() -> {
                    	grill();
                    });

                }
            } catch (InterruptedException e) {
            	
            }
        });
        
        grillingThread.setDaemon(true);
        grillingThread.start();
    }
	
	public void flip() {
		setFrontDown(!isFrontDown());
		if (grillingThread != null) {
			grillingThread.interrupt();
		}
		
		if (isFrontDown()) {
			changeImageTo(getBackImage());
		} else {
			changeImageTo(getFrontImage());
		}
		
		System.out.println(isFrontDown);
		System.out.println(frontState);
		System.out.println(backState);
	}
	
	public boolean theDownSideIsGrilled() {
		if (isFrontDown()) {
			if (getFrontState() == CookingState.COOKED) {
				return true;
			} return false;
		} else {
			if (getBackState() == CookingState.COOKED) {
				return true;
			} return false;
		} 
	}
	
    public void stopGrilling() {
        isGrilling = false;
        if (grillingThread != null) {
            grillingThread.interrupt();
            grillingThread = null;
        }
    }

	
	@Override
	public void grill() {
		
		setGrilled(true);
		
		if (isFrontDown()) {
			if (getFrontState() == CookingState.RAW) {
				setFrontState(CookingState.COOKED);
			} else {
				setFrontState(CookingState.BURNT);
			}
		} else {
			if (getBackState() == CookingState.RAW) {
				setBackState(CookingState.COOKED);
			} else {
				setBackState(CookingState.BURNT);
			}
		}
		
		updateImage();
		updateActualState();
	}
	
	@Override
	public void boil() {
		setBoiled(true);
		if (frontState == CookingState.RAW) {
			setFrontState(CookingState.COOKED);
			updateImage();
			if (!isFrontDown) {changeImageTo(getFrontImage());}
		} if (backState == CookingState.RAW) {
			setBackState(CookingState.COOKED);
			updateImage();
			if (isFrontDown) {changeImageTo(getBackImage());}
		}
		
        //edited cuz the one-side-burnt one won't get cooked
        updateActualState();
	}
	
	private void updateImage() {
		
		if (getFrontState() == CookingState.RAW) {
			setFrontImage(new Image(ClassLoader.getSystemResource(getName() + "0.png").toString()));
		} else if (getFrontState() == CookingState.COOKED) {
			setFrontImage(new Image(ClassLoader.getSystemResource(getName() + "1.png").toString()));
		} else {
			setFrontImage(new Image(ClassLoader.getSystemResource(getName() + "2.png").toString()));
		}
		
		if (getBackState() == CookingState.RAW) {
			setBackImage(new Image(ClassLoader.getSystemResource(getName() + "0.png").toString()));
		} else if (getBackState() == CookingState.COOKED) {
			setBackImage(new Image(ClassLoader.getSystemResource(getName() + "1.png").toString()));
		} else {
			setBackImage(new Image(ClassLoader.getSystemResource(getName() + "2.png").toString()));
		}
		
	}
	
	private void updateActualState() {
		if (getFrontState() == CookingState.COOKED && getBackState() == CookingState.COOKED) {
			setGrilled(true);
		} if (getFrontState() == CookingState.BURNT || getBackState() == CookingState.BURNT) {
			setBurnt(true);
		}
	}

	public boolean isGrilled() {
		return isGrilled;
	}

	public void setGrilled(boolean isGrilled) {
		this.isGrilled = isGrilled;
	}

	public boolean isBurnt() {
		return isBurnt;
	}

	public void setBurnt(boolean isBurnt) {
		this.isBurnt = isBurnt;
	}

	public CookingState getFrontState() {
		return frontState;
	}

	public void setFrontState(CookingState frontState) {
		this.frontState = frontState;
	}

	public CookingState getBackState() {
		return backState;
	}

	public void setBackState(CookingState backState) {
		this.backState = backState;
	}

	public boolean isFrontDown() {
		return isFrontDown;
	}

	public void setFrontDown(boolean isFrontDown) {
		this.isFrontDown = isFrontDown;
	}

	public Image getBackImage() {
		return backImage;
	}

	public void setBackImage(Image backImage) {
		this.backImage = backImage;
	}
	
}
