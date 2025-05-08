package main;

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

		ScoreSystem scoreSystem = new ScoreSystem();
		PlateSnapLogic.init(scoreSystem, root);
		BinSnapLogic.init(root);

		Label scoreLabel = new Label();
		scoreLabel.setFont(new Font("Arial", 24));
		scoreLabel.setTextFill(Color.BLACK);
		scoreLabel.setLayoutX(750);
		scoreLabel.setLayoutY(20);
		scoreLabel.textProperty().bind(scoreSystem.scoreProperty().asString("Score: %d"));

		root.getChildren().addAll(kata, plate, bin, scoreLabel);


		ContainerTray tray1 = new ContainerTray("spinach");
		tray1.setTranslateX(20);
		tray1.setTranslateY(50);
		ContainerTray tray2 = new ContainerTray("ribeye");
		tray2.setTranslateX(20);
		tray2.setTranslateY(270);
		ContainerTray tray3 = new ContainerTray("bacon");
		tray3.setTranslateX(180);
		tray3.setTranslateY(50);
		ContainerTray tray4 = new ContainerTray("chinese cabbage");
		tray4.setTranslateX(180);
		tray4.setTranslateY(270);
		
		Tray tray0 = new Tray();
		tray0.setTranslateX(340);
		tray0.setTranslateY(120);
		
		root.getChildren().addAll(tray1, tray2, tray3, tray4, tray0);
		
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
