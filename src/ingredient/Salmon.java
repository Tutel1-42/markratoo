package ingredient;

import base.RawMeat;
import javafx.scene.image.Image;
import logic.BoilSnapLogic;

public class Salmon extends RawMeat{

	public Salmon() {
		super("salmon", new Image(ClassLoader.getSystemResource("salmon0.png").toString()),
				new Image(ClassLoader.getSystemResource("salmon0.png").toString()), 50);
		setEdible(true);
	}

}
