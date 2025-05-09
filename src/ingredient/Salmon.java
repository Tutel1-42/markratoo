package ingredient;

import base.RawMeat;
import javafx.scene.image.Image;

public class Salmon extends RawMeat {

	public Salmon() {
		super("salmon", new Image(ClassLoader.getSystemResource("salmon0.png").toString()),
				new Image(ClassLoader.getSystemResource("salmon0.png").toString()), 20);
		setEdible(true);
	}

}
