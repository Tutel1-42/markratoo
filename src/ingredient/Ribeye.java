package ingredient;

import base.Edible;
import base.Grillable;
import base.RawMeat;
import javafx.scene.image.Image;
import logic.BoilSnapLogic;

public class Ribeye extends RawMeat implements Edible{

	public Ribeye() {
		super("ribeye", new Image(ClassLoader.getSystemResource("ribeye0.png").toString()),
				new Image(ClassLoader.getSystemResource("ribeye0.png").toString()));
	}

	@Override
	public void eat() {
		System.out.println(this.getName() + "eaten");
		// TODO Auto-generated method stub
		
	}

	
	
	


}
