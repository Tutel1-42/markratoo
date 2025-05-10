package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Tray extends StackPane {

	public Tray() {
		Image image = new Image(ClassLoader.getSystemResource("tray.png").toString());
		ImageView imageView = new ImageView(image);
		this.getChildren().add(imageView);

	}
}
