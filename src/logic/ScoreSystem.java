package logic;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ScoreSystem {
	private IntegerProperty score = new SimpleIntegerProperty(0);

	public void addScore(int value) {
		score.set(score.get() + value);
	}

	public void reduceScore(int value) {
		score.set(score.get() - value);
		if (getScore() < 0) {
			score.set(0);
		}
	}

	public IntegerProperty scoreProperty() {
		return score;
	}

	public int getScore() {
		return score.get();
	}
}
