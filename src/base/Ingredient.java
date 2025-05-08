package base;

import container.Container;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import logic.BinSnapLogic;
import logic.BoilSnapLogic;
import logic.PlateSnapLogic;
import logic.SoundPlayer;

public abstract class Ingredient extends Pane {
	private String name;
	private int value;
	private boolean isEdible = false;
	private boolean isBoiled = false;
	private boolean isBurnt = false;
	private boolean isGrilled = false;

	private Image frontImage;
	private ImageView imageView;

	protected double mouseX, mouseY;
	protected double lastPosX, lastPosY = 0;
	

	protected Ingredient(String name, Image frontImage, int value) {
		this.name = name;
		this.value = value;

		this.frontImage = frontImage;
		imageView = new ImageView(this.frontImage);
		this.getChildren().add(imageView);

		enableDragAndDrop();
	}

	private void enableDragAndDrop() {
		
		// enter
		this.setOnMouseEntered(event -> {
			if (this.getCursor() != Cursor.CLOSED_HAND) {
				this.setCursor(Cursor.HAND);
			}
		});
		
		// click
		this.setOnMouseEntered(event -> {
			if (this.getCursor() != Cursor.CLOSED_HAND) {
				this.setCursor(Cursor.HAND);
			}
		});
		
		this.setOnMousePressed(event -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				mouseX = event.getSceneX() - this.getTranslateX();
				mouseY = event.getSceneY() - this.getTranslateY();
				this.toFront();
				
				stopBoiling();
				event.consume();
			}
		});

		// drag
		this.setOnMouseDragged(event -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				this.setTranslateX(event.getSceneX() - mouseX);
				this.setTranslateY(event.getSceneY() - mouseY);
				event.consume();
			}
		});

		this.setOnMouseReleased(event -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				if (this.getParent() instanceof Container) {
	                Container container = (Container) this.getParent();
	                container.getChildren().remove(this);
	                ((Pane) container.getScene().getRoot()).getChildren().add(this);

	                var scenePos = container.localToScene(this.getTranslateX(), this.getTranslateY());
	                this.setTranslateX(scenePos.getX());
	                this.setTranslateY(scenePos.getY());
	            }

				if (PlateSnapLogic.getInstance().snapAndConsume(this)) {
					event.consume();
					return;
				}

				if (BinSnapLogic.getInstance().snapAndConsume(this)) {
					event.consume();
					return;
				}

				if (this instanceof Boilable && BoilSnapLogic.getInstance().snapToNearest(this)) {
					startBoiling();
					lastPosX = this.getTranslateX();
					lastPosY = this.getTranslateY();
				} else {
					goBack();
				}
				event.consume();
			}
		});

	}

	public void goBack() {
		if (isBoiled()) {
			this.setTranslateX(lastPosX);
			this.setTranslateY(lastPosY);
		} else {
			BoilSnapLogic.getInstance().clearOccupant(this);
			this.setTranslateX(370);
			this.setTranslateY(140);
		}
	}

	public void changeImageTo(Image image) {
//		this.frontImage = image;
		this.imageView.setImage(image);
	}

	private Thread boilingThread;
	private boolean isBoiling;

	public void startBoiling() {
		if (isBoiled) {
			return;
		}

		isBoiling = true;
		boilingThread = new Thread(() -> {
			try {
				Thread.sleep(4000);
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
