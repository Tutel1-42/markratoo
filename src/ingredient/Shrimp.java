package ingredient;

import base.Edible;
import base.Ingredient;
import base.RawMeat;
import javafx.scene.image.Image;
import logic.BoilSnapLogic;

public class Shrimp extends RawMeat implements Edible{

	public Shrimp() {
		super("shrimp", new Image(ClassLoader.getSystemResource("shrimp0.png").toString()),
				new Image(ClassLoader.getSystemResource("shrimp0.png").toString()));
	}

	@Override
	public void eat() {
		System.out.println(this.getName() + "eaten");
		// TODO Auto-generated method stub
		
	}

}
