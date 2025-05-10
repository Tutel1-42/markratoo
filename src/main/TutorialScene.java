package main;

import gui.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TutorialScene {

	public static Scene createTutorialScene(Stage stage) {
		Pane root = new Pane();
		Scene scene = new Scene(root, 960, 540, Color.web("0xFFC6A0"));
		root.setStyle("-fx-background-color: #FFC6A0;");

		ImageView imageView = new ImageView(new Image(ClassLoader.getSystemResource("howToPlay.png").toString()));
		root.getChildren().add(imageView);

		HomeButton homeButton = new HomeButton(stage);
		homeButton.setLayoutX(10);
		homeButton.setLayoutY(10);
		root.getChildren().add(homeButton);

		return scene;
	}
}
