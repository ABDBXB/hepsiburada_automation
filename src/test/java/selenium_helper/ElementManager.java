package selenium_helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ElementManager {
    private final Map<String, ElementConfig> elementMap;

    public ElementManager(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File jsonFile = new File(filePath);

            // 1. Read JSON array directly into a List of POJOs
            List<ElementConfig> list = objectMapper.readValue(
                    jsonFile,
                    new TypeReference<List<ElementConfig>>() {}
            );

            // 2. Convert the List into a Map for instant lookups
            this.elementMap = list.stream()
                    .collect(Collectors.toMap(ElementConfig::getKey, element -> element));

        } catch (IOException e) {
            throw new RuntimeException("Failed to load or parse JSON configuration file: " + filePath, e);
        }
    }

    /**
     * Easily offers the object by its key.
     * Throws an exception if the key doesn't exist to fail tests early.
     */
    public ElementConfig get(String key) {
        if (!elementMap.containsKey(key)) {
            throw new IllegalArgumentException("Key '" + key + "' not found in the JSON configuration.");
        }
        return elementMap.get(key);
    }
}