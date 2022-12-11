package aoc;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Config {

    private final Resource resource;
    private Map<String, Object> config;

    public Config(Resource resource) {
        this.resource = resource;
    }

    public void loadConfiguration() {
        this.config = Arrays.stream(resource.getResourceAsString("secret-config.cfg").split("\n"))
                .map(String::trim)
                .map(s -> s.split(":"))
                .collect(Collectors.toMap(key -> key[0], val -> val[1]));
    }

    public Object getConfigProperty(String key) {
        return Optional.ofNullable(config.get(key)).orElseThrow();
    }
}
