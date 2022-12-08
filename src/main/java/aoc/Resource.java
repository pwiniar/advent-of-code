package aoc;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import static org.apache.commons.io.FileUtils.readFileToString;

public class Resource {

    private static int example = 0;

    public static String getResourceAsString(String resource) {
        try {
            return readFileToString(getResource(resource), Charset.defaultCharset());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    static File getResource(String path) {
        return new File("src/main/resources/" + path);
    }

    static String getDayPath(int year, int day) {
        boolean b = example != 0;
        return year + (b ? "-examples" : "") + "/day" + day + (b ? "-" + example : "") + ".txt";
    }

    public static void downloadIfNotDownloaded(int day, int year) {
        if (!getResource(getDayPath(year, day)).exists()) {
            new FetchInput().retrieveInput(day, year);
        }
    }
}
