package util;

import base.Ingredient;
import container.*;
import javafx.scene.layout.Pane;

public class ContainerRemover {
	
	public static void FindContainerThenRemove(Pane rootPane, Ingredient ingredient) {
		for (Object node : rootPane.getChildren()) {
	        if (node instanceof Container) {
	            Container container = (Container) node;
	            if (container.getDuplicationList().contains(ingredient)) {
	            	container.removeIngredient(ingredient);

	                if (container.getDuplicationList().isEmpty()) {
	                    rootPane.getChildren().remove(container);
	                }
	                
	                break;
	            }
	        }
	    }
	}
}
