package logic;

import base.Ingredient;
import javafx.scene.layout.Pane;
import util.ContainerRemover;

public class BinSnapLogic {
	private static BinSnapLogic instance;
	private static final double PLATE_CENTER_X = 900;
	private static final double PLATE_CENTER_Y = 35;
	private static final double SNAP_RADIUS = 70;

	private final ScoreSystem scoreSystem;
	private final Pane rootPane;

	public BinSnapLogic(ScoreSystem scoreSystem, Pane rootPane) {
		this.scoreSystem = scoreSystem;
		this.rootPane = rootPane;
	}

	public boolean snapAndConsume(Ingredient ingredient) {
		double centerX = ingredient.getBoundsInParent().getMinX() + ingredient.getBoundsInParent().getWidth() / 2;
		double centerY = ingredient.getBoundsInParent().getMinY() + ingredient.getBoundsInParent().getHeight() / 2;

		double distance = Math.sqrt(Math.pow(centerX - PLATE_CENTER_X, 2) + Math.pow(centerY - PLATE_CENTER_Y, 2));

		if (distance < SNAP_RADIUS && ingredient.isBurnt()) {
				scoreSystem.reduceScore(ingredient.getValue());
				rootPane.getChildren().remove(ingredient);
				BoilSnapLogic.getInstance().clearOccupant(ingredient);
				
				ContainerRemover.FindContainerThenRemove(rootPane, ingredient);
				
				return true;
		}
		return false;
	}

	public static void init(ScoreSystem scoreSystem, Pane rootPane) {
		instance = new BinSnapLogic(scoreSystem, rootPane);
	}

	public static BinSnapLogic getInstance() {
		return instance;
	}

}
