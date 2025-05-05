package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.StackPane;

public class Plate extends StackPane {
	public static final DataFormat INGREDIENT_FORMAT = new DataFormat("base.Ingredient");

	public Plate() {
		Image image = new Image("file:res/plate.png");
		ImageView imageView = new ImageView(image);
		this.getChildren().add(imageView);

	}
}
