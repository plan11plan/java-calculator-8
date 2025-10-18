package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) throws IllegalArgumentException {
        String input = input();
        InputInfo inputInfo = publishInputInfo(input);
        validateNumeric(inputInfo.numbers(), inputInfo.delimiter());
        validateNegative(inputInfo.numbers(), inputInfo.delimiter());
        int sum = splitByDelimiterAndSum(inputInfo.numbers(), inputInfo.delimiter());
        output(sum);
    }

    private static InputInfo publishInputInfo(final String input) {
        Pattern pattern = Pattern.compile("//(.)\\\\n(.*)");
        Matcher matcher = pattern.matcher(input);
        boolean hasCustomDelimiter = matcher.find();
        if (hasCustomDelimiter) {
            String delimiter = matcher.group(1);
            String numbers = matcher.group(2);
            return new InputInfo(numbers, delimiter);
        } else {
            return new InputInfo(input, ",|:");
        }

    }

    private static void validateNumeric(String input, String delimiter) throws IllegalArgumentException {
        String[] split = input.split(delimiter);
        boolean isAllNumeric = Arrays.stream(split)
                .allMatch(n -> n.trim().matches("[0-9]+"));
        if (!isAllNumeric) {
            throw new IllegalArgumentException("[ERROR] 숫자가 아닌 값은 입력할 수 없습니다.");
        }
    }

    private static void validateNegative(String input, String delimiter) throws IllegalArgumentException {
        String[] split = input.split(delimiter);
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

    private static int splitByDelimiterAndSum(String input, String delimiter) {
        String[] split = input.split(delimiter);
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
