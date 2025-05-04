package ingredient;

import base.Edible;
import base.Ingredient;
import javafx.scene.image.Image;
import logic.BoilSnapLogic;

public class FrenchFries extends Ingredient implements Edible{

	public FrenchFries() {
		super("french fries", new Image(ClassLoader.getSystemResource("frenchfries0.png").toString()));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void eat() {
		System.out.println(this.getName() + "eaten");
		// TODO Auto-generated method stub
		
	}

}
