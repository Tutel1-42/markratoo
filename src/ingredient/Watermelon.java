package ingredient;

import base.Edible;
import base.Ingredient;
import javafx.scene.image.Image;

public class Watermelon extends Ingredient implements Edible {

	public Watermelon() {
		super("watermelon", new Image(ClassLoader.getSystemResource("watermelon0.png").toString()));
		this.setEdible(true);

	}

	@Override
	public void eat() {
		System.out.println(this.getName() + " eaten");
		// TODO Auto-generated method stub

	}

}
