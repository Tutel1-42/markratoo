package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Bin extends StackPane {

	public Bin() {
		Image image = new Image(ClassLoader.getSystemResource("bin.png").toString());
		ImageView imageView = new ImageView(image);
		this.getChildren().add(imageView);
	}
}
