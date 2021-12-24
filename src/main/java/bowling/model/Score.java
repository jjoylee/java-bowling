package bowling.model;

import java.util.Objects;

public class Score {

    private static final int NOT_LEFT = 0;
    private static final int ONE_TIME_LEFT = 1;
    private static final int TWO_TIME_LEFT = 2;
    private static final int ALL_KNOCKED_DOWN = 10;
    private int score;
    private int left;

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public static Score ofStrike() {
        return new Score(ALL_KNOCKED_DOWN, TWO_TIME_LEFT);
    }

    public static Score ofSpare() {
        return new Score(ALL_KNOCKED_DOWN, ONE_TIME_LEFT);
    }

    public static Score ofMiss(int knockedDownPin) {
        return new Score(knockedDownPin, NOT_LEFT);
    }

    public Score add(int knockedDownPin) {
        return new Score(score += knockedDownPin, left-1);
    }

    public int getScore() {
        if(!canCalculateScore()) {
            throw new CannotCalculateException();
        }
        return this.score;
    }

    public boolean canCalculateScore() {
        return left == NOT_LEFT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score1 = (Score) o;
        return score == score1.score && left == score1.left;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, left);
    }
}
