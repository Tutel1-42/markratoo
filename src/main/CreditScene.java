package main;

import gui.HomeButton;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreditScene {

	public static void addCredit(Pane root, String name, String imgName, double Xtext, double Ytext, double Ximg,
			double Yimg) {
		Image profIMG = new Image(ClassLoader.getSystemResource(imgName).toString());
		ImageView profView = new ImageView(profIMG);
		profView.setX(Ximg);
		profView.setY(Yimg);
		profView.setFitHeight(250);
		profView.setFitWidth(250);

		Text Fname = new Text(name);
		Fname.setFont(Font.font("Verdana", 28));
		Fname.setX(Xtext);
		Fname.setY(Ytext);

		root.getChildren().addAll(profView, Fname);

	}

	public static Scene createCreditScene(Stage stage) {
		Pane root = new Pane();
		Scene scene = new Scene(root, 960, 540, Color.web("0xFFC6A0"));
		root.setStyle("-fx-background-color: #FFC6A0;");

		addCredit(root, "Sajakorn Hiranwipas\n6731352921", "iceprof.png", 150, 90, 150, 180);
		addCredit(root, "Rahut Nopnirapath\n6731345521", "oohprof.png", 520, 90, 520, 180);

		HomeButton homeButton = new HomeButton(stage);
		homeButton.setLayoutX(10);
		homeButton.setLayoutY(10);
		root.getChildren().add(homeButton);

		return scene;

	}

}
