package ingredient;

import base.Edible;
import base.RawMeat;
import javafx.scene.image.Image;

public class Tongue extends RawMeat implements Edible {

	public Tongue() {
		super("tongue", new Image(ClassLoader.getSystemResource("tongue0.png").toString()),
				new Image(ClassLoader.getSystemResource("tongue1.png").toString()));
	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println(this.getName() + " eaten");

	}

}
