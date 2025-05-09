package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Pan extends StackPane {

	public Pan() {
		Image image = new Image(ClassLoader.getSystemResource("kata.png").toString());
		ImageView imageView = new ImageView(image);
		this.getChildren().add(imageView);
	}
}
