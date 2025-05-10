package container;

import java.util.ArrayList;
import java.util.List;

import base.Ingredient;
import ingredient.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Container extends Pane {

	private Image image;
	private String ingredientName;

	protected static final int DUPLICATE_COUNT = 3;
	protected List<Ingredient> duplicationList = new ArrayList<Ingredient>();

	public Container(Image image, String ingredientName) {
		this.image = image;
		this.ingredientName = ingredientName;

		ImageView imageView = new ImageView(this.image);
		this.getChildren().add(imageView);

		addIngredient();
		arraign();
	}

	private Ingredient createIngredientByName(String name) {
		return switch (name) {
		case "bacon" -> new Bacon();
		case "chinese cabbage" -> new ChineseCabbage();
		case "french fries" -> new FrenchFries();
		case "pork" -> new Pork();
		case "ribeye" -> new Ribeye();
		case "salmon" -> new Salmon();
		case "shrimp" -> new Shrimp();
		case "spinach" -> new Spinach();
		case "tongue" -> new Tongue();
		case "watermelon" -> new Watermelon();
		default -> throw new IllegalArgumentException("Unknown ingredient: " + name);
		};
	}

	private void addIngredient() {
		for (int i = 0; i < DUPLICATE_COUNT; i++) {
			Ingredient ing = createIngredientByName(ingredientName);
			duplicationList.add(ing);
		}
	}

	public abstract void arraign();

	public void removeIngredient(Ingredient ingredient) {
		duplicationList.remove(ingredient);
		this.getChildren().remove(ingredient);
	}

	public List<Ingredient> getDuplicationList() {
		return duplicationList;
	}

	public void setDuplicationList(List<Ingredient> duplicationList) {
		this.duplicationList = duplicationList;
	}
}
