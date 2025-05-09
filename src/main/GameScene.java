package main;


import gui.*;
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

		Pan pan = new Pan();
		pan.setLayoutX(500);
		pan.setLayoutY(70);

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
		BinSnapLogic.init(scoreSystem, root);

		Label scoreLabel = new Label();
		scoreLabel.setFont(new Font("Impact", 30));
		scoreLabel.setTextFill(Color.BLACK);
		scoreLabel.setLayoutX(650);
		scoreLabel.setLayoutY(470);
		scoreLabel.textProperty().bind(scoreSystem.scoreProperty().asString("Score: %d"));

		root.getChildren().addAll(pan, plate, bin, tray, scoreLabel);
		
		OrderButton button1 = new OrderButton(root, 20, 50);
        OrderButton button2 = new OrderButton(root, 20, 270);
        OrderButton button3 = new OrderButton(root, 180, 50);
        OrderButton button4 = new OrderButton(root, 180, 270);
        
        root.getChildren().addAll(button1,button2,button3,button4);
		

		return scene;
	}
}
