package container;

import java.util.ArrayList;
import java.util.List;

import base.Ingredient;
import ingredient.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Container extends Pane  {
	
	private Image image;
	private String ingredientName;
	private Ingredient ingredient;
	protected List<Ingredient> duplicationList = new ArrayList<Ingredient>();
	
	public Container(Image image, String ingredientName) {
		this.image = image;
		this.ingredientName = ingredientName;
		
		ImageView imageView = new ImageView(this.image);
		this.getChildren().add(imageView);
		
		addIngredient();
		arraign();
	}
	
	private void addIngredient() {
		for (int i = 0; i < 3; i++) {
			
			switch (ingredientName) {
	        case "bacon":
	            ingredient = new Bacon();
	            break;
	        case "chinese cabbage":
	            ingredient = new ChineseCabbage();
	            break;
	        case "french fries":
	            ingredient = new FrenchFries();
	            break;
	        case "pork":
	            ingredient = new Pork();
	            break;
	        case "ribeye":
	            ingredient = new Ribeye();
	            break;
	        case "salmon":
	            ingredient = new Salmon();
	            break;
	        case "shrimp":
	            ingredient = new Shrimp();
	            break;
	        case "spinach":
	            ingredient = new Spinach();
	            break;
	        case "tongue":
	            ingredient = new Tongue();
	            break;
	        case "watermelon":
	            ingredient = new Watermelon();
	            break;
	        default:
	            throw new IllegalArgumentException("Unknown ingredient");
	    }
			duplicationList.add(ingredient);
		}
		
	}
	
	public abstract void arraign();

    public void removeIngredient(Ingredient ingredient) {
        duplicationList.remove(ingredient);
        this.getChildren().remove(ingredient);
    }
	
	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public List<Ingredient> getDuplicationList() {
		return duplicationList;
	}

	public void setDuplicationList(List<Ingredient> duplicationList) {
		this.duplicationList = duplicationList;
	}
}
