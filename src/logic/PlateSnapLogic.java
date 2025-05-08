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

public class PlateSnapLogic {
	private static PlateSnapLogic instance;
	private static final double PLATE_CENTER_X = 460;
	private static final double PLATE_CENTER_Y = 430;
	private static final double SNAP_RADIUS = 80;

	private final ScoreSystem scoreSystem;
	private final Pane rootPane;

	public PlateSnapLogic(ScoreSystem scoreSystem, Pane rootPane) {
		this.scoreSystem = scoreSystem;
		this.rootPane = rootPane;
	}

	public boolean snapAndConsume(Ingredient ingredient) {
		double centerX = ingredient.getBoundsInParent().getMinX() + ingredient.getBoundsInParent().getWidth() / 2;
		double centerY = ingredient.getBoundsInParent().getMinY() + ingredient.getBoundsInParent().getHeight() / 2;

		double distance = Math.sqrt(Math.pow(centerX - PLATE_CENTER_X, 2) + Math.pow(centerY - PLATE_CENTER_Y, 2));

		if (distance < SNAP_RADIUS) {
			if (ingredient.isEdible()) {
				scoreSystem.addScore(ingredient.getValue());
				rootPane.getChildren().remove(ingredient); // destroy food
				BoilSnapLogic.getInstance().clearOccupant(ingredient);
			} else {
				ingredient.goBack();
				System.out.println("you can't eat this");
			}

			return true;
		}
		return false;
	}

	public static void init(ScoreSystem scoreSystem, Pane rootPane) {
		instance = new PlateSnapLogic(scoreSystem, rootPane);
	}

	public static PlateSnapLogic getInstance() {
		return instance;
	}

}
