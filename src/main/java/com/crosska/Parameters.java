package com.crosska;

import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.Map;

public class Parameters {

    private final Yaml yaml;

    public Parameters() {
        yaml = new Yaml();
    }

    public Map<String, Object> getParameters() {
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("properties.yml");
        return yaml.load(inputStream);
    }

}
