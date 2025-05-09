package main;

import container.ContainerPlate;
import container.ContainerTray;
import gui.*;
import ingredient.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.*;
import util.SoundPlayer;

public class GameScene {

	public static Scene createGameScene(Stage stage) {
		Pane root = new Pane();
		Scene scene = new Scene(root, 960, 540, Color.web("0xFFC6A0"));
		root.setStyle("-fx-background-color: #FFC6A0;");

		Kata kata = new Kata();
		kata.setLayoutX(500);
		kata.setLayoutY(70);

		Plate plate = new Plate();
		plate.setLayoutX(380);
		plate.setLayoutY(350);

		Bin bin = new Bin();
		bin.setLayoutX(865);
		bin.setLayoutY(8);
		
		Tray tray = new Tray();
		tray.setTranslateX(340);
		tray.setTranslateY(120);
		
		ScoreSystem scoreSystem = new ScoreSystem();
		PlateSnapLogic.init(scoreSystem, root);
		BinSnapLogic.init(root);

		Label scoreLabel = new Label();
		scoreLabel.setFont(new Font("Arial", 24));
		scoreLabel.setTextFill(Color.BLACK);
		scoreLabel.setLayoutX(750);
		scoreLabel.setLayoutY(20);
		scoreLabel.textProperty().bind(scoreSystem.scoreProperty().asString("Score: %d"));

		root.getChildren().addAll(kata, plate, bin, scoreLabel, tray);
		
		OrderButton button1 = new OrderButton(root, 20, 50);
        OrderButton button2 = new OrderButton(root, 20, 270);
        OrderButton button3 = new OrderButton(root, 180, 50);
        OrderButton button4 = new OrderButton(root, 180, 270);
        
        root.getChildren().addAll(button1,button2,button3,button4);
		
		scene.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			case ESCAPE:
				stage.setScene(Main.createMainMenuScene(stage));
				stage.setTitle("Main Menu");
				SoundPlayer.stopBGM();
				SoundPlayer.startBGM();
				SoundPlayer.stopSFX(SoundPlayer.getBoilingSFX());
				break;
			default:
				break;
			}
		});

		return scene;
	}
}
