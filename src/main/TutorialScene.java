package main;

import gui.*;
import ingredient.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.*;
import util.SoundPlayer;

public class TutorialScene {

	public static Scene createTutorialScene(Stage stage) {
		Pane root = new Pane();
		Scene scene = new Scene(root, 960, 600, Color.web("0xFFC6A0"));
		root.setStyle("-fx-background-color: #FFC6A0;");

		Kata kata = new Kata();
		kata.setLayoutX(250);
		kata.setLayoutY(50);

		Plate plate = new Plate();
		plate.setLayoutX(730);
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

		Text instruction = new Text("How to Play:\n" + "• Drag COOKABLE food to the kata.\n"
				+ "• Wait until it's done, then drag to the plate to score.\n"
				+ "• Or drag it to the trash bin to discard.\n" + "• Some food can be eaten immediately.");
		instruction.setFont(new Font("Arial", 18));
		instruction.setLayoutX(20);
		instruction.setLayoutY(480);

		Spinach spinach = new Spinach();
		spinach.setLayoutX(20);
		spinach.setLayoutY(20);
		Ribeye ribeye = new Ribeye();
		ribeye.setLayoutX(20);
		ribeye.setLayoutY(200);
		Watermelon watermelon = new Watermelon();
		watermelon.setLayoutX(20);
		watermelon.setLayoutY(300);
		FrenchFries fries = new FrenchFries();
		fries.setLayoutX(100);
		fries.setLayoutY(300);
		Shrimp shrimp = new Shrimp();
		shrimp.setLayoutX(120);
		shrimp.setLayoutY(200);
		ChineseCabbage cc = new ChineseCabbage();
		cc.setLayoutX(100);
		cc.setLayoutY(20);

		root.getChildren().addAll(kata, plate, bin, scoreLabel, spinach, ribeye, watermelon, fries, shrimp, cc,
				instruction);

		HomeButton homeButton = new HomeButton(stage);
		homeButton.setLayoutX(10);
		homeButton.setLayoutY(10);
        root.getChildren().add(homeButton);

		return scene;
	}
}
