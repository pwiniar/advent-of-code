package aoc.year2022.days;

import aoc.year2022.Year2022;
import org.assertj.core.api.Assertions;

import java.util.stream.IntStream;

public class Day6 extends Year2022 {
    public Day6() {
        super(6);
    }


    /*
     * sequence of four characters that are all different
     *
     * */

    @Override
    public Object example1() {
        var input = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";

        int res = findMarker(4, input);

        Assertions.assertThat(res).isEqualTo(7);
        return res;
    }

    @Override
    public Object part1() {
        return findMarker(4, day());
    }

    @Override
    public Object example2() {
        var input = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";

        int res = findMarker(14, input);

        Assertions.assertThat(res).isEqualTo(19);
        return res;
    }

    @Override
    public Object part2() {
        return findMarker(14, day());
    }

    private int findMarker(int size, String input) {
        return IntStream.range(0, input.length())
                .filter(i -> input.substring(i, i + size)
                        .chars().distinct().count() == size)
                .findFirst().getAsInt() + size;
    }
}
