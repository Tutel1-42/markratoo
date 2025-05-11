package base;

import container.Container;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import logic.BinSnapLogic;
import logic.BoilSnapLogic;
import logic.PlateSnapLogic;
import util.SoundPlayer;

/**
 * Abstract base class for all ingredients in this game. Provides drag and drop
 * functionality, state of ingredients , and image handling.
 */

public class Ingredient extends Pane {
	private String name;
	private int value;
	private boolean isEdible = false;
	private boolean isBoiled = false;
	private boolean isBurnt = false;
	private boolean isGrilled = false;
	protected static final double DEFAULT_POS_X = 370;
	protected static final double DEFAULT_POS_Y = 140;
	private static final long BOIL_DURATION_MS = 4000;

	private Image frontImage;
	private ImageView imageView;

	protected double mouseX, mouseY;
	protected double lastPosX, lastPosY = 0;

	private Thread boilingThread;
	private boolean isBoiling;

	public Ingredient(String name, Image frontImage, int value) {
		this.name = name;
		this.value = value;

		this.frontImage = frontImage;
		imageView = new ImageView(this.frontImage);
		this.getChildren().add(imageView);

		enableDragAndDrop();
	}

	private void enableDragAndDrop() {
		setupMouseEnterHandler();
		setupMousePressHandler();
		setupMouseDragHandler();
		setupMouseReleaseHandler();
	}

	private void setupMouseEnterHandler() {
		this.setOnMouseEntered(event -> {
			if (this.getCursor() != Cursor.CLOSED_HAND) {
				this.setCursor(Cursor.HAND);
			}
		});
	}

	private void setupMousePressHandler() {
		this.setOnMousePressed(event -> {
			this.setCursor(Cursor.CLOSED_HAND);

			if (event.getButton() == MouseButton.PRIMARY) {
				mouseX = event.getSceneX() - this.getTranslateX();
				mouseY = event.getSceneY() - this.getTranslateY();
				this.toFront();

				stopBoiling();
				event.consume();
			}
		});

	}

	private void setupMouseDragHandler() {
		this.setOnMouseDragged(event -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				this.setTranslateX(event.getSceneX() - mouseX);
				this.setTranslateY(event.getSceneY() - mouseY);
				event.consume();
			}
		});
	}

	private void setupMouseReleaseHandler() {
		this.setOnMouseReleased(event -> {
			this.setCursor(Cursor.DEFAULT);

			if (event.getButton() == MouseButton.PRIMARY) {
				handleContainerTransfer();
				if (trySnapToBinOrPlate(event))
					return;
				if (!trySnapToBoil())
					resetPosition();
				event.consume();
			}
		});
	}

	protected void handleContainerTransfer() {
		if (this.getParent() instanceof Container) {
			Container container = (Container) this.getParent();
			container.getChildren().remove(this);
			((Pane) container.getScene().getRoot()).getChildren().add(this);

			var scenePos = container.localToScene(this.getTranslateX(), this.getTranslateY());
			this.setTranslateX(scenePos.getX());
			this.setTranslateY(scenePos.getY());
		}
	}

	protected boolean trySnapToBinOrPlate(MouseEvent event) {
		if (PlateSnapLogic.getInstance().snapAndConsume(this)) {
			event.consume();
			return true;
		}

		if (BinSnapLogic.getInstance().snapAndConsume(this)) {
			event.consume();
			return true;
		}
		return false;
	}

	private boolean trySnapToBoil() {
		if (this instanceof Boilable && BoilSnapLogic.getInstance().snapToNearest(this)) {
			startBoiling();
			lastPosX = this.getTranslateX();
			lastPosY = this.getTranslateY();
			return true;
		} else {
			resetPosition();
			return false;
		}
	}

	public void resetPosition() {
		if (isBoiled()) {
			this.setTranslateX(lastPosX);
			this.setTranslateY(lastPosY);
		} else {
			BoilSnapLogic.getInstance().clearOccupant(this);
			this.setTranslateX(DEFAULT_POS_X);
			this.setTranslateY(DEFAULT_POS_Y);
		}
	}

	public void changeImageTo(Image image) {
//		this.frontImage = image;
		this.imageView.setImage(image);
	}

	public void startBoiling() {
		if (isBoiled) {
			return;
		}

		isBoiling = true;
		boilingThread = new Thread(() -> {
			try {
				Thread.sleep(BOIL_DURATION_MS);
				if (isBoiling) {
					Platform.runLater(() -> {
						((Boilable) this).boil();
						setBoiled(true);
						if (!isBurnt()) {
							setEdible(true);
						}

						SoundPlayer.startSFX(SoundPlayer.getBoilingSFX());
					});
				}
			} catch (InterruptedException e) {

			}
		});

		boilingThread.setDaemon(true);
		boilingThread.start();
	}

	public void stopBoiling() {
		isBoiling = false;
		if (boilingThread != null) {
			boilingThread.interrupt();
			boilingThread = null;
		}
	}

	public boolean isBoiled() {
		return isBoiled;
	}

	public void setBoiled(boolean isBoiled) {
		this.isBoiled = isBoiled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEdible() {
		return isEdible;
	}

	public void setEdible(boolean isEdible) {
		this.isEdible = isEdible;
	}

	public Image getFrontImage() {
		return frontImage;
	}

	public void setFrontImage(Image frontImage) {
		this.frontImage = frontImage;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isBurnt() {
		return isBurnt;
	}

	public void setBurnt(boolean isBurnt) {
		this.isBurnt = isBurnt;
	}

	public boolean isGrilled() {
		return isGrilled;
	}

	public void setGrilled(boolean isGrilled) {
		this.isGrilled = isGrilled;
	}

}
