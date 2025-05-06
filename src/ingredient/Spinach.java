package ingredient;

import base.Boilable;
import base.Ingredient;
import logic.BoilSnapLogic;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Spinach extends Ingredient implements Boilable {

	public Spinach() {
		super("spinach", new Image(ClassLoader.getSystemResource("spinach0.png").toString()), 50);
	}

	@Override
	public void boil() {
		this.setBoiled(true);
		changeImageTo(new Image(ClassLoader.getSystemResource("spinach1.png").toString()));

	}

}
