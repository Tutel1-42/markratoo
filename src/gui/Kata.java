package gui;

import base.Ingredient;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Kata extends StackPane {
	
	public Kata() {
		Image image = new Image("file:res/kata.png");
		ImageView imageView = new ImageView(image);
	    this.getChildren().add(imageView);
	}
}
