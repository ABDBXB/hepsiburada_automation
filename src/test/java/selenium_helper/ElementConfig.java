package selenium_helper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ElementConfig {
    private String key;
    private String value;
    private String type; // Will be null if not provided in JSON

    // Default constructor required by Jackson
    public ElementConfig() {}

    // Getters and Setters
    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    @Override
    public String toString() {
        return "ElementConfig{key='" + key + "', value='" + value + "', type='" + type + "'}";
    }
}