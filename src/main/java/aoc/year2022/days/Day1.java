package aoc.year2022.days;

import aoc.year2022.Year2022;

import java.util.Arrays;
import java.util.stream.LongStream;

public class Day1 extends Year2022 {
    public Day1() {
        super(1);
    }

    @Override
    public Object part1() {
        return input().max().getAsLong();
    }

    @Override
    public Object part2() {
        long[] nums = input().sorted().toArray();
        return nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3];
    }

    private LongStream input() {
        return Arrays.stream(day().split("\n\n"))
                .mapToLong(s -> Arrays.stream(s.split("\n"))
                        .map(String::trim)
                        .mapToLong(Long::parseLong)
                        .sum());
    }
}
