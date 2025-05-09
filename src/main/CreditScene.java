package main;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.SoundPlayer;

public class CreditScene {

	public static Scene createCreditScene(Stage stage) {
		Pane root = new Pane();
		Scene scene = new Scene(root, 960, 600, Color.web("0xFFC6A0"));
		root.setStyle("-fx-background-color: #FFC6A0;");
		
		Text creditIce = new Text("Sajakorn Hiranwipas");
		creditIce.setFont(Font.font("Arial", 28));
		creditIce.setX(150);
		creditIce.setY(400);
		
		Image profIce = new Image(ClassLoader.getSystemResource("iceprof.png").toString());
		ImageView ImageIce = new ImageView(profIce);
		ImageIce.setX(170);
		ImageIce.setY(90);
		ImageIce.setFitHeight(250);
		ImageIce.setFitWidth(250);
		
		Text creditAoo = new Text("Rahut Nopnirapath");
		creditAoo.setFont(Font.font("Arial", 28));
		creditAoo.setX(500);
		creditAoo.setY(400);
		
		Image profAoo = new Image(ClassLoader.getSystemResource("aooprof.png").toString());
		ImageView ImageAoo = new ImageView(profAoo);
		ImageAoo.setX(490);
		ImageAoo.setY(90);
		ImageAoo.setFitHeight(250);
		ImageAoo.setFitWidth(250);
		
		scene.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			case ESCAPE:
				stage.setScene(Main.createMainMenuScene(stage));
				stage.setTitle("Main Menu");
				SoundPlayer.stopBGM();
				SoundPlayer.startBGM();
				break;
			default:
				break;
			}
		});
		
		root.getChildren().addAll(creditIce,creditAoo,ImageIce,ImageAoo);
		return scene;

		
	}

}
