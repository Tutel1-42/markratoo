package ingredient;

import base.RawMeat;
import javafx.scene.image.Image;

public class Bacon extends RawMeat {

	public Bacon() {
		super("bacon", new Image(ClassLoader.getSystemResource("bacon0.png").toString()),
				new Image(ClassLoader.getSystemResource("bacon0.png").toString()), 100);
	}

}
