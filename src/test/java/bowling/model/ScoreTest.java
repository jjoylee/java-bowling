package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class ScoreTest {

    @Test
    @DisplayName("Strike일 때의 Score 확인 테스트")
    void ofStrikeTest() {
        assertThat(Score.ofStrike()).isEqualTo(new Score(10,2));
    }

    @Test
    @DisplayName("Spare일 때의 Score 확인 테스트")
    void ofSpareTest() {
        assertThat(Score.ofSpare()).isEqualTo(new Score(10,1));
    }

    @Test
    @DisplayName("Miss일 때의 Score 확인 테스트")
    void ofMissTest() {
        assertThat(Score.ofMiss(9)).isEqualTo(new Score(9,0));
    }

    @ParameterizedTest
    @CsvSource(value = {"10,2,3","2,8,1", "4,6,1"})
    @DisplayName("score 더해지는 것 테스트")
    void addTest(int score, int left, int add) {
        assertThat(new Score(score,left).add(add)).isEqualTo(new Score(score + add,left - 1));
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    @DisplayName("left가 0면 score 반환하는 것 테스트")
    void getScoreLeftIsZeroReturnScoreTest(int score) {
        assertThat(new Score(score,0).getScore()).isEqualTo(score);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2})
    @DisplayName("left가 0이 아니면 CannotCalculateException 예외 발생 테스트")
    void getScoreLeftIsNotZeroThrowExceptionTest(int left) {
        assertThatExceptionOfType(CannotCalculateException.class)
                .isThrownBy(() -> new Score(15,left).getScore());
    }

    @Test
    @DisplayName("left가 0일 때 true값 반환하는 것 테스트")
    void canCalculateScoreLeftZeroReturnTrue() {
        assertTrue(new Score(10,0).canCalculateScore());
        assertFalse(new Score(10,1).canCalculateScore());
        assertFalse(new Score(10,2).canCalculateScore());
    }
}
