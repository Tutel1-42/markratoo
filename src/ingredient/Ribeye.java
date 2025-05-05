package ingredient;

import base.Grillable;
import base.RawMeat;
import javafx.scene.image.Image;
import logic.BoilSnapLogic;

public class Ribeye extends RawMeat {

	public Ribeye() {
		super("ribeye", new Image(ClassLoader.getSystemResource("ribeye0.png").toString()),
				new Image(ClassLoader.getSystemResource("ribeye0.png").toString()), 150);
	}


}
