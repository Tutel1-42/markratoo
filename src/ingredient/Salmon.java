package ingredient;

import base.Edible;
import base.RawMeat;
import javafx.scene.image.Image;
import logic.BoilSnapLogic;

public class Salmon extends RawMeat implements Edible {

	public Salmon() {
		super("salmon", new Image(ClassLoader.getSystemResource("salmon0.png").toString()),
				new Image(ClassLoader.getSystemResource("salmon0.png").toString()));
	}

	@Override
	public void eat() {
		System.out.println(this.getName() + " eaten");
		// TODO Auto-generated method stub

	}

}
