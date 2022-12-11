package aoc;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.stream.Stream;

import static aoc.Resource.*;

public abstract class Day {

    protected static final String DEFAULT_DELIMITER = "\n";
    protected final int year;
    protected final int day;

    private final Resource resource;

    public Day(int year, int day) {
        this.year = year;
        this.day = day;
        this.resource = new Resource();
        Config config = new Config(resource);
        config.loadConfiguration();
        FetchInput fetchInput = new FetchInput(resource, config);
        fetchInput.downloadIfNotDownloaded(day, year);
    }

    public abstract Object example1();

    public abstract Object part1();

    public abstract Object example2();

    public abstract Object part2();


    public void printParts() {
        System.out.println("Example 1: " + example1());
        System.out.println("Part 1: " + part1());
        System.out.println("Example 2: " + example2());
        System.out.println("Part 2: " + part2());
    }

    protected String day() {
        return resource.getResourceAsString(resource.getDayPath(year, day));
    }

    protected Stream<String> dayStream() {
        return Arrays.stream(day().split(DEFAULT_DELIMITER));
    }
}
