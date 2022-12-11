package aoc;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import static org.apache.commons.io.FileUtils.readFileToString;

public class Resource {

    public String getResourceAsString(String resource) {
        try {
            return readFileToString(getResource(resource), Charset.defaultCharset());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public File getResource(String path) {
        String resourcePath = "src/main/resources/";
        return new File(resourcePath + path);
    }

    public String getDayPath(int year, int day) {
        return year + "/day" + day + ".txt";
    }
}
