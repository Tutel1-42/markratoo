package ingredient;

import base.Ingredient;
import base.RawMeat;
import javafx.scene.image.Image;
import logic.BoilSnapLogic;

public class Shrimp extends RawMeat{

	public Shrimp() {
		super("shrimp", new Image(ClassLoader.getSystemResource("shrimp0.png").toString()),
				new Image(ClassLoader.getSystemResource("shrimp0.png").toString()), 100);
	}

}
