package logic;

import base.Ingredient;

public class BoilSnapLogic {
	
    private static final double[][] BOIL_POSITIONS = {
    		{480, 220},
            {740, 220}
    };
    
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
        double centerX = ingredient.getBoundsInParent().getMinX() + ingredient.getBoundsInParent().getWidth() / 2;
        double centerY = ingredient.getBoundsInParent().getMinY() + ingredient.getBoundsInParent().getHeight() / 2;

        double minDistance = Double.MAX_VALUE;
        int nearestIndex = -1;

        for (int i = 0; i < BOIL_POSITIONS.length; i++) {
            if (occupants[i] == null || occupants[i] == ingredient) {
                double distance = Math.sqrt(
                    Math.pow(centerX - BOIL_POSITIONS[i][0], 2) +
                    Math.pow(centerY - BOIL_POSITIONS[i][1], 2)
                );
                if (distance < minDistance && distance < 50) {
                    minDistance = distance;
                    nearestIndex = i;
                }
            }
        }

        if (nearestIndex != -1) {
        	double targetX = BOIL_POSITIONS[nearestIndex][0] - ingredient.getLayoutX() - ingredient.getBoundsInLocal().getWidth() / 2;
            double targetY = BOIL_POSITIONS[nearestIndex][1] - ingredient.getLayoutY() - ingredient.getBoundsInLocal().getHeight() / 2;
            ingredient.setTranslateX(targetX);
            ingredient.setTranslateY(targetY);
            occupants[nearestIndex] = ingredient;
            return true;
        }
        return false;
    }

    public void clearOccupant(Ingredient ingredient) {
        for (int i = 0; i < occupants.length; i++) {
            if (occupants[i] == ingredient) {
                occupants[i] = null;
            }
        }
    }
}