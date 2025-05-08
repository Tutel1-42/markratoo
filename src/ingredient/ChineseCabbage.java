package ingredient;

import base.Boilable;
import base.Ingredient;
import javafx.scene.image.Image;
import logic.BoilSnapLogic;

public class ChineseCabbage extends Ingredient implements Boilable {

	public ChineseCabbage() {
		super("chinese cabbage", new Image(ClassLoader.getSystemResource("cc0.png").toString()), 50);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void boil() {
		this.setBoiled(true);
		changeImageTo(new Image(ClassLoader.getSystemResource("cc1.png").toString()));

	}

}
