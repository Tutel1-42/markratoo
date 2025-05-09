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
		Text titleText = new Text("Mookata Sim");
		titleText.setFont(Font.font(STYLESHEET_CASPIAN, 32));

		Button startButton = new Button("start game");
		startButton.setFont(Font.font(STYLESHEET_CASPIAN, 20));
		startButton.setStyle("-fx-background-color: #FF7F50;" + "-fx-text-fill: white;" + "-fx-font-weight: bold;"
				+ "-fx-background-radius: 10;" + "-fx-padding: 10 20;" + "-fx-pref-width: 200;"
				+ "-fx-pref-height: 40;");
		startButton.setOnAction(e -> {
			Scene gameScene = GameScene.createGameScene(primaryStage);
			primaryStage.setScene(gameScene);
			primaryStage.setTitle("Mookata Sim");
		});

		Button tutorialButton = new Button("tutorial");
		tutorialButton.setFont(Font.font(STYLESHEET_CASPIAN, 20));
		tutorialButton.setStyle("-fx-background-color: #20B2AA;" + "-fx-text-fill: white;" + "-fx-font-weight: bold;"
				+ "-fx-background-radius: 10;" + "-fx-padding: 10 20;" + "-fx-pref-width: 200;"
				+ "-fx-pref-height: 40;");
		tutorialButton.setOnAction(e -> {
			Scene tutorialScene = TutorialScene.createTutorialScene(primaryStage);
			primaryStage.setScene(tutorialScene);
			primaryStage.setTitle("Tutorial");
		});
		
		Button creditButton = new Button("credits");
		creditButton.setFont(Font.font(STYLESHEET_CASPIAN, 20));
		creditButton.setStyle("-fx-background-color: #45fc03;" + "-fx-text-fill: white;" + "-fx-font-weight: bold;"
				+ "-fx-background-radius: 10;" + "-fx-padding: 10 20;" + "-fx-pref-width: 200;"
				+ "-fx-pref-height: 40;");
		creditButton.setOnAction(e -> {
			Scene creditScene = CreditScene.createCreditScene(primaryStage);
			primaryStage.setScene(creditScene);
			primaryStage.setTitle("Credit");
		});

		Button exitButton = new Button("exit");
		exitButton.setFont(Font.font(STYLESHEET_CASPIAN, 20));
		exitButton.setStyle("-fx-background-color: #DC143C;" + "-fx-text-fill: white;" + "-fx-font-weight: bold;"
				+ "-fx-background-radius: 10;" + "-fx-padding: 10 20;" + "-fx-pref-width: 200;"
				+ "-fx-pref-height: 40;");
		exitButton.setOnAction(e -> {
			primaryStage.close();
		});

		VBox layout = new VBox(20, titleText, startButton, tutorialButton, creditButton, exitButton); // layout for button
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-background-color: #FFDAB9;");

		return new Scene(layout, 800, 600);
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