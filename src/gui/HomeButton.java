package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Main;

public class HomeButton extends Pane {

	public HomeButton(Stage stage) {
		
		ImageView imageView = new ImageView(new Image(ClassLoader.getSystemResource("home.png").toString()));
        this.getChildren().add(imageView);
        
		this.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
            	stage.setScene(Main.createMainMenuScene(stage));
				stage.setTitle("Main Menu");
            }
        });
	}
}
