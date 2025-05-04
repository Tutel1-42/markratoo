package ingredient;


import base.Boilable;
import base.Edible;
import base.Ingredient;
import javafx.scene.image.Image;
import logic.BoilSnapLogic;

public class ChineseCabbage extends Ingredient implements Boilable,Edible {

	public ChineseCabbage() {
		super("chinese Cabbage", new Image(ClassLoader.getSystemResource("cc0.png").toString()));
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void boil() {
		this.setBoiled(true);
		changeImageTo(new Image(ClassLoader.getSystemResource("cc1.png").toString()));
		
	}

	
	@Override
	public void eat() {
		System.out.println(this.getName() + "eaten");
		// TODO Auto-generated method stub
		
	}



}
