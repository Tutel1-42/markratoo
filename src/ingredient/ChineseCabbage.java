package ingredient;

import base.Boilable;
import base.Ingredient;
import javafx.scene.image.Image;

public class ChineseCabbage extends Ingredient implements Boilable {

	public ChineseCabbage() {
		super("chinese cabbage", new Image(ClassLoader.getSystemResource("cc0.png").toString()), 60);
	}

	@Override
	public void boil() {
		this.setBoiled(true);
		changeImageTo(new Image(ClassLoader.getSystemResource("cc1.png").toString()));

	}

}
