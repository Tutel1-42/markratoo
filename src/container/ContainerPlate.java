package container;

import base.Ingredient;
import javafx.scene.image.Image;

public class ContainerPlate extends Container {

	public ContainerPlate(String ingredientName) {
		super(new Image(ClassLoader.getSystemResource("container-plate.png").toString()), ingredientName);
	}
	
	@Override
	public void arraign() {
		for (int i = 0; i < 3; i++) {
			Ingredient ingre = duplicationList.get(i);
			ingre.setTranslateX(i*20+11);
			ingre.setTranslateY(i*15);
			this.getChildren().add(ingre);
		}
	}

}
