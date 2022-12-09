package aoc.year2022.days;

import aoc.year2022.Year2022;

import java.util.HashMap;
import java.util.Map;

public class Day2 extends Year2022 {
    public Day2() {
        super(2);
    }

    private static final Map<String, Integer> TASK_1_MAPPING = new HashMap<>();
    private static final Map<String, Integer> TASK_2_MAPPING = new HashMap<>();

    static {
        TASK_1_MAPPING.put("A Y", 8);
        TASK_1_MAPPING.put("B X", 1);
        TASK_1_MAPPING.put("C Z", 6);
        TASK_1_MAPPING.put("A X", 4);
        TASK_1_MAPPING.put("A Z", 3);
        TASK_1_MAPPING.put("B Y", 5);
        TASK_1_MAPPING.put("B Z", 9);
        TASK_1_MAPPING.put("C X", 7);
        TASK_1_MAPPING.put("C Y", 2);

        TASK_2_MAPPING.put("A X", 3);
        TASK_2_MAPPING.put("A Z", 8);
        TASK_2_MAPPING.put("A Y", 4);
        TASK_2_MAPPING.put("B X", 1);
        TASK_2_MAPPING.put("B Y", 5);
        TASK_2_MAPPING.put("B Z", 9);
        TASK_2_MAPPING.put("C Z", 7);
        TASK_2_MAPPING.put("C X", 2);
        TASK_2_MAPPING.put("C Y", 6);
    }

    @Override
    public Object part1() {
        return calculateSum(TASK_1_MAPPING);
    }

    @Override
    public Object part2() {
        return calculateSum(TASK_2_MAPPING);
    }

    private int calculateSum(Map<String, Integer> task1Mapping) {
        return dayStream().map(task1Mapping::get).mapToInt(Integer::valueOf).sum();
    }
}
