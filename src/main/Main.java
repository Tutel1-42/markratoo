package main;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.SoundPlayer;

public class Main extends Application {

	public static Scene createMainMenuScene(Stage primaryStage) {
		Text titleText = new Text("Virtual Maakratoo");
		titleText.setFont(Font.font("Verdana", 32));

		Button startButton = createStyledButton("start game", "#FF7F50", () -> {
			Scene gameScene = GameScene.createGameScene(primaryStage);
			primaryStage.setScene(gameScene);
			primaryStage.setTitle("Virtual Maakratoo");
		});

		Button tutorialButton = createStyledButton("tutorial", "#20B2AA", () -> {
			Scene tutorialScene = TutorialScene.createTutorialScene(primaryStage);
			primaryStage.setScene(tutorialScene);
			primaryStage.setTitle("Tutorial");
		});

		Button creditButton = createStyledButton("credits", "#9657D5", () -> {
			Scene creditScene = CreditScene.createCreditScene(primaryStage);
			primaryStage.setScene(creditScene);
			primaryStage.setTitle("Credit");
		});

		Button exitButton = createStyledButton("exit", "#DC143C", primaryStage::close);

		VBox layout = new VBox(20, titleText, startButton, tutorialButton, creditButton, exitButton); // layout for
																										// button
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-background-color: #FFDAB9;");

		return new Scene(layout, 800, 600);
	}

	private static Button createStyledButton(String text, String bgColor, Runnable action) {
		Button button = new Button(text);
		button.setFont(Font.font("Verdana", 20));
		button.setStyle("-fx-background-color: " + bgColor + ";" + "-fx-text-fill: white;" + "-fx-font-weight: bold;"
				+ "-fx-background-radius: 10;" + "-fx-padding: 10 20;" + "-fx-pref-width: 200;"
				+ "-fx-pref-height: 40;");
		button.setOnAction(e -> action.run());
		return button;
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setScene(createMainMenuScene(primaryStage));
		primaryStage.setTitle("Main Menu");
		SoundPlayer.startBGM();
		primaryStage.show();
		primaryStage.setResizable(false);
	}

	public static void main(String[] args) {
		launch(args);
	}
}