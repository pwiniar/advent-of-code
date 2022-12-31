package aoc.year2022.days;

import aoc.year2022.Year2022;
import org.assertj.core.api.Assertions;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Day7 extends Year2022 {

    private final String INPUT = """
            $ cd /
            $ ls
            dir a
            14848514 b.txt
            8504156 c.dat
            dir d
            $ cd a
            $ ls
            dir e
            29116 f
            2557 g
            62596 h.lst
            $ cd e
            $ ls
            584 i
            $ cd ..
            $ cd ..
            $ cd d
            $ ls
            4060174 j
            8033020 d.log
            5626152 d.ext
            7214296 k
            """;

    public Day7() {
        super(7);
    }

    @Override
    public Object example1() {
        String[] split = INPUT.split("\n");
        List<String> filteredData = removeUnnecessaryCommands(split);
        var node = parseActions(filteredData);
        long res = solution1(node);
        Assertions.assertThat(res).isEqualTo(95437);
        return res;
    }

    @Override
    public Object part1() {
        String[] split = day().split("\n");
        List<String> filteredData = removeUnnecessaryCommands(split);
        var node = parseActions(filteredData);
        long res = solution1(node);
        Assertions.assertThat(res).isEqualTo(1915606);
        return res;
    }

    @Override
    public Object example2() {
        return super.example2();
    }

    @Override
    public Object part2() {
        return super.part2();
    }

    private static long solution1(Node node) {
        return node.dirs()
                .filter(n -> n.size() <= 100000)
                .mapToLong(n -> n.size()).sum();
    }

    private static class Node {
        private final String name;
        private final Node parent;
        boolean isDirectory;
        private long size;
        private final Map<String, Node> children = new HashMap<>();

        public Node(String name, Node parent, boolean isDirectory) {
            this.name = name;
            this.parent = parent;
            this.isDirectory = isDirectory;
        }

        public void addChild(Node n) {
            children.put(n.name, n);
        }

        public void addSize(long size) {
            this.size += size;
        }

        Stream<Node> dirs() {
            return Stream.concat(Stream.of(this), children.values().stream().flatMap(n -> n.dirs())
                    .filter(n -> n.size == 0));
        }

        public long size() {
            return size > 0 ? size : children.values().stream().mapToLong(n -> n.size()).sum();
        }
    }

    private Node parseActions(List<String> commands) {
        var root = new Node("/", null, true);
        var currentNode = root;

        for (String command : commands) {
            if (command.startsWith("$")) {
                String action = command.split(" ")[2];
                switch (action) {
                    case ".." -> currentNode = currentNode.parent;
                    case "/" -> currentNode = root;
                    default -> {
                        if (currentNode.children.containsKey(action)) {
                            currentNode = currentNode.children.get(action);
                        }
                    }
                }
            } else {
                String[] action = command.split(" ");
                String nodeName = action[1];
                Node node;
                if ("dir".equals(getNodeName(action))) {
                    node = new Node(nodeName, currentNode, true);
                } else {
                    node = new Node(nodeName, currentNode, false);
                    node.addSize(Long.parseLong(getNodeSize(action)));
                }
                currentNode.addChild(node);
            }
        }

        return root;
    }

    private static String getNodeName(String[] action) {
        return action[0];
    }

    private static String getNodeSize(String[] action) {
        return action[0];
    }

    private List<String> removeUnnecessaryCommands(String[] split) {
        return Arrays.stream(split)
                .skip(1)
                .filter(val -> !val.equals("$ ls"))
                .toList();
    }
}
