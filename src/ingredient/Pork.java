package ingredient;

import base.Edible;
import base.RawMeat;
import javafx.scene.image.Image;

public class Pork extends RawMeat implements Edible {

	public Pork() {
		super("pork", new Image(ClassLoader.getSystemResource("pork0.png").toString()),
				new Image(ClassLoader.getSystemResource("pork0.png").toString()));
	}

	@Override
	public void eat() {
		System.out.println(this.getName() + " eaten");
		// TODO Auto-generated method stub

	}

}
