package lotto.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.domain.lotto.Lotto;
import lotto.ui.dto.WinningNumbersInput;

public class WinningStatisticsView {
    private static final Scanner scanner = new Scanner(System.in);

    public static WinningNumbersInput receiveWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");

        final List<Integer> winningNumbers = readWinningNumbers();

        return new WinningNumbersInput(winningNumbers);
    }

    private static List<Integer> readWinningNumbers() {
        final List<Integer> ints = readIntegers();

        if (ints.size() == Lotto.LOTTO_NUMBERS_SIZE) {
            return ints;
        }

        System.out.println("숫자 " + Lotto.LOTTO_NUMBERS_SIZE + "개를 입력해주세요.");
        return readWinningNumbers();
    }

    private static List<Integer> readIntegers() {
        final String nextLine = scanner.nextLine();

        try {
            return parseIntegers(nextLine);
        } catch (NumberFormatException e) {
            System.out.println("정수를 입력해 주세요.");
            return readIntegers();
        }
    }

    private static List<Integer> parseIntegers(String nextLine) {
        return Arrays.stream(nextLine.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}