package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Plate extends StackPane {

	public Plate() {
		Image image = new Image(ClassLoader.getSystemResource("plate.png").toString());
		ImageView imageView = new ImageView(image);
		this.getChildren().add(imageView);

	}
}
