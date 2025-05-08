package container;

import base.Ingredient;
import javafx.scene.image.Image;

public class ContainerTray extends Container {

	public ContainerTray(String ingredientName) {
		super(new Image(ClassLoader.getSystemResource("container-tray.png").toString()), ingredientName);
	}

	@Override
	public void arraign() {
		for (int i = 0; i < 3; i++) {
			Ingredient ingre = duplicationList.get(i);
			ingre.setTranslateX(i*20);
			ingre.setTranslateY(i*15+10);
			this.getChildren().add(ingre);
		}
		
	}


	
}
