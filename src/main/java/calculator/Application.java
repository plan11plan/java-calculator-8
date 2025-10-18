package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;

public class Application {
    public static void main(String[] args) throws IllegalArgumentException {
        String input = input();
        validateNegative(input);
        int sum = splitByDefaultDelimiterAndSum(input);
        output(sum);
    }

    private static void validateNegative(String input) throws IllegalArgumentException {
        String[] split = input.split(",|:");
        boolean hasNegative = Arrays.stream(split)
                .mapToInt(Integer::parseInt)
                .anyMatch(n -> n < 0);
        if (hasNegative) {
            throw new IllegalArgumentException("[ERROR] 음수는 입력할 수 없습니다.");
        }
    }

    private static void output(Object sum) {
        System.out.println(
                String.format("결과 : %d", sum)
        );
    }

    private static int splitByDefaultDelimiterAndSum(String input) {
        String[] split = input.split(",|:");
        int sum = Arrays.stream(split)
                .mapToInt(Integer::parseInt)
                .sum();
        return sum;
    }

    private static String input() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        return Console.readLine();
    }


}
