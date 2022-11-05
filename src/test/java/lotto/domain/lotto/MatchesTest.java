package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class MatchesTest {
    @ParameterizedTest(name = "로또 길이를 벗어날 수 없다. [{0}]")
    @ValueSource(longs = {-1, 7})
    void 범위를_벗어난_값(final long matchCount) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Matches.of(matchCount))
                .withMessageContaining("일치 개수는 로또 길이 이내여야 합니다.");
    }

    @ParameterizedTest(name = "{0}개 일치하면, {1}을 반환한다.")
    @CsvSource({
            "6, SIX",
            "5, FIVE",
            "4, FOUR",
            "3, THREE",
            "2, BLANK",
            "1, BLANK",
            "0, BLANK"
    })
    void 일치개수(final long matchCount, final Matches expected) {
        final Matches actual = Matches.of(matchCount);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}에 당첨되면 당첨금 단위는 {1}원이다.")
    @CsvSource({
            "SIX, 2000000000",
            "FIVE, 1500000",
            "FOUR, 50000",
            "THREE, 5000",
            "BLANK, 0"
    })
    void 당첨금(final Matches match, final long expectedMoney) {
        final Money expected = new Money(expectedMoney);

        final Money actual = match.getUnitPrize();

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}에 2개 당첨되면 당첨금은 {1}원이다.")
    @CsvSource({
            "SIX, 4000000000",
            "FIVE, 3000000",
            "FOUR, 100000",
            "THREE, 10000",
            "BLANK, 0"
    })
    void 당첨금_계산(final Matches match, final long expectedMoney) {
        final Money expected = new Money(expectedMoney);

        final Money actual = match.calculatePrize(2L);

        assertThat(actual).isEqualTo(expected);
    }
}
