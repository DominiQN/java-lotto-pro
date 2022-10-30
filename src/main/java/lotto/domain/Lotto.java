package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    private static final int LOTTO_NUMBERS_SIZE = 6;
    private final Set<LottoNumber> numbers;

    public Lotto(Integer... numbers) {
        this(Arrays.asList(numbers));
    }

    public Lotto(final List<Integer> numbers) {
        requireNotNull(Objects.isNull(numbers));

        final Set<LottoNumber> distinctNumbers = numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet());

        requireDistinctSizeIsSix(distinctNumbers);

        this.numbers = distinctNumbers;
    }

    private static void requireNotNull(boolean numbers) {
        if (numbers) {
            throw new IllegalArgumentException("null이 아니어야 합니다.");
        }
    }

    private static void requireDistinctSizeIsSix(Set<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException("서로 다른 숫자 6개여야 합니다. numbers=" + numbers);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lotto lotto = (Lotto) o;
        return numbers.equals(lotto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }
}