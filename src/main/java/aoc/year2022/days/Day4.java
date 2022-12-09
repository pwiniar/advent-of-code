package aoc.year2022.days;

import aoc.year2022.Year2022;
import org.assertj.core.api.Assertions;

import java.util.Arrays;

public class Day4 extends Year2022 {
    public Day4() {
        super(4);
    }

    private final String input = """
            2-4,6-8
            2-3,4-5
            5-7,7-9
            2-8,3-7
            6-6,4-6
            2-6,4-8
            """;

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
        for (String s : strings) {
            String[] split = s.split(",");
            var left = split[0].split("-");
            var right = split[1].split("-");

            int[] arrLeft = Arrays.stream(left)
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int[] arrRight = Arrays.stream(right)
                    .mapToInt(Integer::parseInt)
                    .toArray();

            if (arrLeft[0] <= arrRight[0] && arrRight[1] <= arrLeft[1] ||
                    arrRight[0] <= arrLeft[0] && arrLeft[1] <= arrRight[1]) {
                ++res;

            }
        }
        return res;
    }

    @Override
    public Object example2() {
        return null;
    }

    @Override
    public Object part2() {
        return null;
    }
}
