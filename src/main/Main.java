package main;

import logic.PlateSnapLogic;
import logic.ScoreSystem;
import gui.Kata;
import gui.Plate;
import ingredient.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) {
		Pane root = new Pane();
		Scene scene = new Scene(root, 960, 540, Color.web("0xFFC6A0"));
		root.setStyle("-fx-background-color: #FFC6A0;");
		stage.setTitle("Mookata Simulator");
		stage.setScene(scene);
		stage.show();
		stage.setResizable(false);

		Kata kata = new Kata();
		kata.setLayoutX(390);
		kata.setLayoutY(50);

		Plate plate = new Plate();
		plate.setLayoutX(730);
		plate.setLayoutY(350);

		ScoreSystem scoreSystem = new ScoreSystem();
		PlateSnapLogic.init(scoreSystem, root);

		Label scoreLabel = new Label();
		scoreLabel.setFont(new Font("Arial", 24));
		scoreLabel.setTextFill(Color.BLACK);
		scoreLabel.setLayoutX(750);
		scoreLabel.setLayoutY(20);
		scoreLabel.textProperty().bind(scoreSystem.scoreProperty().asString("Score: %d"));

		root.getChildren().add(kata);
		root.getChildren().add(plate);
		root.getChildren().add(scoreLabel);

		for (int i = 0; i < 5; i++) {
			Spinach spinach = new Spinach();
			spinach.setLayoutX(20 + 30 * i);
			spinach.setLayoutY(20);
			Ribeye ribeye = new Ribeye();
			ribeye.setLayoutX(20 + 30 * i);
			ribeye.setLayoutY(110);
			Watermelon watermelon = new Watermelon();
			watermelon.setLayoutX(20 + 30 * i);
			watermelon.setLayoutY(200);
			FrenchFries frenchFries = new FrenchFries();
			frenchFries.setLayoutX(20 + 30 * i);
			frenchFries.setLayoutY(290);
			Shrimp shrimp = new Shrimp();
			shrimp.setLayoutX(20 + 30 * i);
			shrimp.setLayoutY(380);
			ChineseCabbage cc = new ChineseCabbage();
			cc.setLayoutX(20 + 30 * i);
			cc.setLayoutY(470);
			root.getChildren().addAll(spinach, ribeye, watermelon, frenchFries, shrimp, cc);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}