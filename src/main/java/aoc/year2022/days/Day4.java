package aoc.year2022.days;

import aoc.year2022.Year2022;
import org.assertj.core.api.Assertions;

import java.util.Arrays;

public class Day4 extends Year2022 {

    private static final String DELIMITER = "\n";
    private final String input = """
            2-4,6-8
            2-3,4-5
            5-7,7-9
            2-8,3-7
            6-6,4-6
            2-6,4-8
            """;

    public Day4() {
        super(4);
    }


    @Override
    public Object example1() {
        int res = solution1(input.split("\n"));
        Assertions.assertThat(res).isEqualTo(2);
        return res;
    }

    @Override
    public Object part1() {
        return solution1(day().split("\n"));
    }

    private int solution1(String[] strings) {
        int res = 0;
        for (String line : strings) {
            LineAsArray lineAsArray = lineAsArray(line);

            if (lineAsArray.leftArr[0] <= lineAsArray.rightArr[0] && lineAsArray.rightArr[1] <= lineAsArray.leftArr[1] ||
                    lineAsArray.rightArr[0] <= lineAsArray.leftArr[0] && lineAsArray.leftArr[1] <= lineAsArray.rightArr[1]) {
                ++res;

            }
        }
        return res;
    }

    @Override
    public Object example2() {

        int res = solution2(input.split(DELIMITER));
        Assertions.assertThat(res).isEqualTo(4);
        return res;
    }

    @Override
    public Object part2() {
        return solution2(day().split(DELIMITER));
    }

    private int solution2(String[] strings) {
        int res = 0;
        for (String line : strings) {
            LineAsArray lineAsArray = lineAsArray(line);
            if (lineAsArray.leftArr[0] <= lineAsArray.rightArr[1] && lineAsArray.rightArr[0] <= lineAsArray.leftArr[1]) {
                ++res;

            }
        }
        return res;
    }

    private static int[] parsAsInt(String[] left) {
        return Arrays.stream(left)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private LineAsArray lineAsArray(String input) {
        String[] split = input.split(",");
        String delimiter = "-";
        var left = split[0].split(delimiter);
        var right = split[1].split(delimiter);

        int[] arrLeft = parsAsInt(left);
        int[] arrRight = parsAsInt(right);
        return new LineAsArray(arrLeft, arrRight);
    }

    public record LineAsArray(int[] leftArr, int[] rightArr) {
    }


}
