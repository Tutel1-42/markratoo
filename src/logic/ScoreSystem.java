package logic;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ScoreSystem {
	private final IntegerProperty score = new SimpleIntegerProperty(0);

	public void addScore(int value) {
		score.set(score.get() + value);
	}

	public IntegerProperty scoreProperty() {
		return score;
	}

	public int getScore() {
		return score.get();
	}
}
