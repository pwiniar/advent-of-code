package aoc.year2022.days;

import aoc.year2022.Year2022;
import org.assertj.core.api.Assertions;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class Day5 extends Year2022 {
    private static final String EXAMPLE = """
                [D]
            [N] [C]
            [Z] [M] [P]
             1   2   3
                       
            move 1 from 2 to 1
            move 3 from 1 to 3
            move 2 from 2 to 1
            move 1 from 1 to 2
            """;
    private final MoveHandler moveHandler = new MoveHandler();
    private final StackHandler stackHandler = new StackHandler();

    public Day5() {
        super(5);
    }

    @Override
    public Object example1() {
        var parts = EXAMPLE.split("\n\n");
        var stackPart = parts[0];
        var movePart = parts[1];
        var initialStack = stackHandler.parseStack(stackPart);

        var moves = moveHandler.parseMoves(movePart);
        var res = stackHandler.getTopCrates(stackHandler.move(moves, initialStack));

        Assertions.assertThat(res).isEqualTo("CMZ");
        return res;
    }

    @Override
    public Object part1() {
        var parts = day().split("\n\n");
        var stackPart = parts[0];
        var movePart = parts[1];
        var initialStack = stackHandler.parseStack(stackPart);

        var moves = moveHandler.parseMoves(movePart);
        var res = stackHandler.getTopCrates(stackHandler.move(moves, initialStack));

        return res;
    }

    @Override
    public Object example2() {
        var parts = EXAMPLE.split("\n\n");
        var stackPart = parts[0];
        var movePart = parts[1];
        var initialStack = stackHandler.parseStack(stackPart);

        var moves = moveHandler.parseMoves(movePart);
        var res = stackHandler.getTopCrates(stackHandler.move2(moves, initialStack));

        Assertions.assertThat(res).isEqualTo("MCD");
        return res;
    }

    @Override
    public Object part2() {
        var parts = EXAMPLE.split("\n\n");
        var stackPart = parts[0];
        var movePart = parts[1];
        var initialStack = stackHandler.parseStack(stackPart);

        var moves = moveHandler.parseMoves(movePart);
        var res = stackHandler.getTopCrates(stackHandler.move2(moves, initialStack));

        Assertions.assertThat(res).isEqualTo("MCD");
        return res;
    }


    static class StackHandler {
        public Map<Integer, Deque<Character>> parseStack(String input) {
            var lines = input.lines().filter(l -> !l.contains("1")).map(l -> parseLine(l)).toList();
            Map<Integer, java.util.Deque<Character>> deque = new HashMap<>();

            for (String line : lines) {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) != ' ') {
                        char letter = line.charAt(i);
                        whenPresent(deque, i, letter);
                        whenAbsent(deque, i, letter);
                    }
                }
            }
            return deque;
        }

        private static void whenAbsent(Map<Integer, Deque<Character>> deque, int i, char letter) {
            Deque<Character> val = new ArrayDeque<>();
            val.add(letter);
            deque.putIfAbsent(i + 1, val);
        }

        private static void whenPresent(Map<Integer, Deque<Character>> deque, int i, char letter) {
            deque.computeIfPresent(i + 1, (integer, characters) -> {
                Deque<Character> de = new ArrayDeque<>(characters);
                de.add(letter);
                return de;
            });
        }

        private String parseLine(String line) {
            return Pattern.compile(".(.)..?")
                    .matcher(line)
                    .results()
                    .map(r -> r.group(1))
                    .collect(Collectors.joining());
        }

        public Map<Integer, Deque<Character>> move(List<Move> moves, Map<Integer, Deque<Character>> initialStack) {
            moves.forEach(move -> {
                Deque<Character> from = initialStack.get(move.fromStackNumber());
                Deque<Character> to = initialStack.get(move.toStackNumber());
                for (int i = 0; i < move.numberOfBoxes(); i++) {
                    Character topOne = from.pollFirst();
                    to.addFirst(topOne);
                }
            });

            return initialStack;
        }

        public Map<Integer, Deque<Character>> move2(List<Move> moves, Map<Integer, Deque<Character>> initialStack) {
            System.out.println(initialStack);
            moves.forEach(move -> {

                System.out.println(move);
                Deque<Character> from = initialStack.get(move.fromStackNumber());
                Deque<Character> to = initialStack.get(move.toStackNumber());
                if (move.numberOfBoxes == 3) {
                    for (int i = 0; i < move.numberOfBoxes(); i++) {
                        Character topOne = from.pollFirst();
                        to.addLast(topOne);
                    }
                } else {
                    for (int i = 0; i < move.numberOfBoxes(); i++) {
                        Character topOne = from.pollFirst();
                        to.addFirst(topOne);
                    }
                }
                System.out.println(initialStack);
            });

            return initialStack;
        }

        public String getTopCrates(Map<Integer, Deque<Character>> deq) {
            return deq.values().stream().map(Deque::getFirst).map(String::valueOf).collect(Collectors.joining());
        }
    }

    static class MoveHandler {
        public List<Move> parseMoves(String input) {
            return input.lines().map(this::parse).toList();
        }

        private Move parse(String line) {
            var m = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)").matcher(line);
            m.matches();
            return new Move(parseInt(m.group(1)), parseInt(m.group(2)), parseInt(m.group(3)));
        }
    }

    record Move(int numberOfBoxes, int fromStackNumber, int toStackNumber) {
    }
}





