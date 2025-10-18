package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        String input = input();
        int sum = splitByDefaultDelimiterAndSum(input);
        output(sum);
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
