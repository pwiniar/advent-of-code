package aoc.year2022.days;

import aoc.year2022.Year2022;
import org.assertj.core.api.Assertions;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day3 extends Year2022 {
    public Day3() {
        super(3);
    }

    private final String input = """
            vJrwpWtwJgWrhcsFMMfFFhFp
            jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
            PmmdzqPrVvPwwTWBwg
            wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
            ttgJtRGJQctTZtZT
            CrZsJsPPZsGzwwsLwLmpwMDw
            """;

    @Override
    public Object example1() {
        var sum = solution1(Stream.of(input.split("\n")));
        Assertions.assertThat(sum).isEqualTo(157);
        return sum;
    }

    @Override
    public Object part1() {
        return solution1(dayStream());
    }

    private int solution1(Stream<String> val) {
        return val.map(String::trim)
                .map(e -> new String[]{e.substring(0, e.length() / 2), e.substring(e.length() / 2)})
                .mapToInt(e -> Day3.this.getPriorities(e[0]).filter(i -> Day3.this.getPriorities(e[1]).anyMatch(j -> j == i)).findFirst().getAsInt())
                .sum();
    }

    @Override
    public Object example2() {
        String[] s = Stream.of(input.split("\n"))
                .map(String::trim)
                .toArray(String[]::new);

        var sum = solution2(s);

        Assertions.assertThat(sum).isEqualTo(70);
        return sum;
    }

    @Override
    public Object part2() {
        String[] s = dayStream().map(String::trim).toArray(String[]::new);
        return solution2(s);
    }

    private int solution2(String[] s) {
        return IntStream.range(0, s.length / 3)
                .map(x -> x * 3)
                .map(x -> getPriorities(s[x]).filter(i -> getPriorities(s[x + 1]).anyMatch(j -> j == i) && getPriorities(s[x + 2]).anyMatch(j -> j == i)).findFirst().getAsInt())
                .sum();
    }

    private IntStream getPriorities(String s) {
        return s.chars()
                .map(i -> {
                    if (i >= 'a' && i <= 'z') {
                        return i - 'a' + 1;
                    }
                    return i - 'A' + 1 + 26;
                });
    }
}
