package logic;

import base.Ingredient;
import ingredient.ChineseCabbage;
import ingredient.FrenchFries;
import ingredient.Ribeye;
import ingredient.Salmon;
import ingredient.Shrimp;
import ingredient.Spinach;
import ingredient.Watermelon;
import javafx.scene.layout.Pane;

public class BinSnapLogic {
	private static BinSnapLogic instance;
	private static final double PLATE_CENTER_X = 900;
	private static final double PLATE_CENTER_Y = 35;
	private static final double SNAP_RADIUS = 70;

	private final Pane rootPane;

	public BinSnapLogic(Pane rootPane) {
		this.rootPane = rootPane;
	}

	public boolean snapAndConsume(Ingredient ingredient) {
		double centerX = ingredient.getBoundsInParent().getMinX() + ingredient.getBoundsInParent().getWidth() / 2;
		double centerY = ingredient.getBoundsInParent().getMinY() + ingredient.getBoundsInParent().getHeight() / 2;

		double distance = Math.sqrt(Math.pow(centerX - PLATE_CENTER_X, 2) + Math.pow(centerY - PLATE_CENTER_Y, 2));

		if (distance < SNAP_RADIUS) {
			rootPane.getChildren().remove(ingredient); // destroy food
			BoilSnapLogic.getInstance().clearOccupant(ingredient);
			
			return true;
		}
		return false;
	}

	public static void init(Pane rootPane) {
		instance = new BinSnapLogic(rootPane);
	}

	public static BinSnapLogic getInstance() {
		return instance;
	}

}
