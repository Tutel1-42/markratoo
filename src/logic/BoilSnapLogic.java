package logic;

import base.Ingredient;

public class BoilSnapLogic {

	private static final double[][] BOIL_POSITIONS = { { 590, 240 }, { 850, 240 } };
	private static final double SNAP_RADIUS = 50;

	private final Ingredient[] occupants;
	private static BoilSnapLogic instance;

	private BoilSnapLogic() {
		occupants = new Ingredient[BOIL_POSITIONS.length];
	}

	public static BoilSnapLogic getInstance() {
		if (instance == null) {
			instance = new BoilSnapLogic();
		}
		return instance;
	}

	public boolean snapToNearest(Ingredient ingredient) {
		var bounds = ingredient.localToScene(ingredient.getBoundsInLocal());
		double centerX = bounds.getMinX() + bounds.getWidth() / 2;
		double centerY = bounds.getMinY() + bounds.getHeight() / 2;

		int index = findNearestAvailableSlot(centerX, centerY, ingredient);
		if (index != -1) {
			snapToSlot(index, ingredient);
			return true;
		}
		return false;
	}

	// find nearest boil position
	private int findNearestAvailableSlot(double x, double y, Ingredient ingredient) {
		double minDistance = Double.MAX_VALUE;
		int nearestIndex = -1;

		for (int i = 0; i < BOIL_POSITIONS.length; i++) {
			if (occupants[i] == null || occupants[i] == ingredient) {
				double distance = distance(x, y, BOIL_POSITIONS[i][0], BOIL_POSITIONS[i][1]);
				if (distance < minDistance && distance < SNAP_RADIUS) {
					minDistance = distance;
					nearestIndex = i;
				}
			}
		}
		return nearestIndex;
	}

	// move ingredient to boil position
	private void snapToSlot(int index, Ingredient ingredient) {
		double targetX = BOIL_POSITIONS[index][0] - ingredient.getBoundsInLocal().getWidth() / 2;
		double targetY = BOIL_POSITIONS[index][1] - ingredient.getBoundsInLocal().getHeight() / 2;
		ingredient.setTranslateX(targetX);
		ingredient.setTranslateY(targetY);
		occupants[index] = ingredient;
	}

	private double distance(double x1, double y1, double x2, double y2) {
		return Math.hypot(x1 - x2, y1 - y2);
	}

	public void clearOccupant(Ingredient ingredient) {
		for (int i = 0; i < occupants.length; i++) {
			if (occupants[i] == ingredient) {
				occupants[i] = null;
			}
		}
	}
}