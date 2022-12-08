package aoc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    private static final String PACKAGE_NAME = "aoc.year2022.days";

    public static void main(String[] args) {
        Main a = new Main();

        IntStream.rangeClosed(1, a.findAllClassesUsingClassLoader(PACKAGE_NAME)).forEach(day -> {
            System.out.println("Day " + day + ":");
            Day instance;
            try {
                instance = (Day) Class.forName(PACKAGE_NAME + ".Day" + day).getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            instance.printParts();
            System.out.println();
        });
    }

    public Integer findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet()).size();
    }

    private Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }
}
