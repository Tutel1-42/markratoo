package ingredient;

import base.RawMeat;
import javafx.scene.image.Image;

public class Tongue extends RawMeat {

	public Tongue() {
		super("tongue", new Image(ClassLoader.getSystemResource("tongue0.png").toString()),
				new Image(ClassLoader.getSystemResource("tongue0.png").toString()), 120);
	}

}
