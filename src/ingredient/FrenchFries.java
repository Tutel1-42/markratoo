package ingredient;

import base.Ingredient;
import javafx.scene.image.Image;

public class FrenchFries extends Ingredient {

	public FrenchFries() {
		super("french fries", new Image(ClassLoader.getSystemResource("frenchfries0.png").toString()), 20);
		this.setEdible(true);
	}

}
