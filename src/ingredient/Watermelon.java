package ingredient;


import base.Ingredient;
import javafx.scene.image.Image;

public class Watermelon extends Ingredient{

	public Watermelon() {
		super("watermelon", new Image(ClassLoader.getSystemResource("watermelon0.png").toString()), 20);
		this.setEdible(true);

	}

}
