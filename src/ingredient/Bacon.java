package ingredient;

import base.Edible;
import base.RawMeat;
import javafx.scene.image.Image;

public class Bacon extends RawMeat implements Edible {

	public Bacon() {
		super("bacon", new Image(ClassLoader.getSystemResource("bacon0.png").toString()),
				new Image(ClassLoader.getSystemResource("bacon1.png").toString()));
	}

	@Override
	public void eat() {
		System.out.println(this.getName() + " eaten");
		// TODO Auto-generated method stub

	}

}
