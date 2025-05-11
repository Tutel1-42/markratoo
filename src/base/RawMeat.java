package base;

import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import logic.BoilSnapLogic;
import util.SoundPlayer;

/**
 * Abstract base class for all meats in this game. Provide grilling
 * functionality.
 * 
 */
public class RawMeat extends Ingredient implements Grillable, Boilable {

	private CookingState frontState = CookingState.RAW;
	private CookingState backState = CookingState.RAW;
	private boolean isFrontDown = false;

	private Image backImage;

	private static final double GRILL_X_MIN = 645;
	private static final double GRILL_X_MAX = 800;
	private static final double GRILL_Y_MIN = 150;
	private static final double GRILL_Y_MAX = 300;
	private static final long GRILL_TICK_MS = 1000;

	private Thread grillingThread;
	private boolean isGrilling;

	private boolean isDragging = false;

	public RawMeat(String name, Image frontImage, Image backImage, int value) {
		super(name, frontImage, value);

		this.backImage = backImage;
		setupMouseEnter();
		setupMousePressed();
		setupMouseReleased();
	}

	// enter
	private void setupMouseEnter() {
		this.setOnMouseEntered(event -> {
			if (this.getCursor() != Cursor.CLOSED_HAND) {
				this.setCursor(Cursor.HAND);
			}
		});
	}

	private void setupMousePressed() {
		this.setOnMousePressed(event -> {
			this.setCursor(Cursor.CLOSED_HAND);

			if (event.getButton() == MouseButton.PRIMARY) {
				mouseX = event.getSceneX() - this.getTranslateX();
				mouseY = event.getSceneY() - this.getTranslateY();
				this.toFront();

				BoilSnapLogic.getInstance().clearOccupant(this);

				stopBoiling();
				stopGrilling();

				isDragging = true;
				this.getScene().setOnKeyPressed(keyEvent -> {
					if (keyEvent.getCode() == KeyCode.SPACE && isDragging) {
						flip();
						keyEvent.consume();
					}

				});
				event.consume();
			}
		});
	}

	private void setupMouseReleased() {
		this.setOnMouseReleased(event -> {
			this.setCursor(Cursor.DEFAULT);
			isDragging = false;

			if (event.getButton() == MouseButton.PRIMARY) {
				handleContainerTransfer();

				if (trySnapToBinOrPlate(event))
					return;

				if (trySnapToGrillOrBoil())
					return;

				resetPosition();

				event.consume();
			}
		});
	}

	private boolean trySnapToGrillOrBoil() {
		double centerX = getBoundsInParent().getMinX() + getBoundsInParent().getWidth() / 2;
		double centerY = getBoundsInParent().getMinY() + getBoundsInParent().getHeight() / 2;

		boolean isOnGrill = centerX >= GRILL_X_MIN && centerX <= GRILL_X_MAX && centerY >= GRILL_Y_MIN
				&& centerY <= GRILL_Y_MAX;

		if (isOnGrill && this instanceof Grillable) {
			lastPosX = this.getTranslateX();
			lastPosY = this.getTranslateY();
			startGrilling();
			return true;
		}

		if (this instanceof Boilable && BoilSnapLogic.getInstance().snapToNearest(this)) {
			lastPosX = this.getTranslateX();
			lastPosY = this.getTranslateY();
			startBoiling();
			return true;
		}
		return false;
	}

	@Override
	public void resetPosition() {
		if (isGrilled() || isBoiled()) {
			this.setTranslateX(lastPosX);
			this.setTranslateY(lastPosY);
		} else {
			this.setTranslateX(DEFAULT_POS_X);
			this.setTranslateY(DEFAULT_POS_Y);
		}
	}

	// start grilling thread
	public void startGrilling() {
		isGrilling = true;
		grillingThread = new Thread(this::grillOverTime);
		grillingThread.setDaemon(true);
		grillingThread.start();
	}

	// grilling
	private void grillOverTime() {
		try {
			if (isGrilling && !theDownSideIsGrilled() && !theDownSideIsBurnt()) {
				grillThisSide(4);
				Platform.runLater(this::grill);
			}
			if (isGrilling && !theDownSideIsBurnt()) {
				grillThisSide(4);
				Platform.runLater(this::grill);
			}
		} catch (InterruptedException e) {
		}
	}

	// every second print "tick"
	private void grillThisSide(int seconds) throws InterruptedException {
		for (int i = 0; i < seconds; i++) {
			Thread.sleep(GRILL_TICK_MS);
			System.out.println("tick");
		}
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
//		System.out.println(isFrontDown);
//		System.out.println(frontState);
//		System.out.println(backState);
	}

	public boolean theDownSideIsGrilled() {
		if (isFrontDown()) {
			return getFrontState().equals(CookingState.COOKED);
		}
		return getBackState().equals(CookingState.COOKED);
	}

	public boolean theDownSideIsBurnt() {
		if (isFrontDown()) {
			return getFrontState().equals(CookingState.BURNT);
		}
		return getBackState().equals(CookingState.BURNT);
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

		if (isFrontDown()) {
			if (getFrontState().equals(CookingState.RAW)) {
				setFrontState(CookingState.COOKED);
			} else {
				setFrontState(CookingState.BURNT);
			}
		} else {
			if (getBackState().equals(CookingState.RAW)) {
				setBackState(CookingState.COOKED);
			} else {
				setBackState(CookingState.BURNT);
			}
		}

		SoundPlayer.startSFX(SoundPlayer.getGrilingSFX());
		updateImage();
		updateActualState();
	}

	@Override
	public void boil() {
		setBoiled(true);
		if (getFrontState().equals(CookingState.RAW)) {
			setFrontState(CookingState.COOKED);
			updateImage();
			if (!isFrontDown) {
				changeImageTo(getFrontImage());
			}
		}
		if (getBackState().equals(CookingState.RAW)) {
			setBackState(CookingState.COOKED);
			updateImage();
			if (isFrontDown) {
				changeImageTo(getBackImage());
			}
		}

		// edited cuz the one-side-burnt one won't get cooked
		updateActualState();
	}

	private void updateImage() {
		setFrontImage(getImageForState(getFrontState()));
		setBackImage(getImageForState(getBackState()));
	}

	private Image getImageForState(CookingState state) {
		String suffix = switch (state) {
		case RAW -> "0.png";
		case COOKED -> "1.png";
		case BURNT -> "2.png";
		};
		return new Image(ClassLoader.getSystemResource(getName() + suffix).toString());
	}

	private void updateActualState() {
		if (getFrontState().equals(CookingState.COOKED) && getBackState().equals(CookingState.COOKED)) {
			setGrilled(true);
			setEdible(true);
			if (this.getName().equals("salmon")) {
				this.setValue(100);
			}
		}
		if (getFrontState().equals(CookingState.BURNT) || getBackState().equals(CookingState.BURNT)) {
			setBurnt(true);
			setEdible(false);
		}
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
