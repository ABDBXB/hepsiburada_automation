package selenium_helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ProjectConfig {
    private Properties prop = new Properties();

    public ProjectConfig(String filePath) {
        try {
            FileInputStream input = new FileInputStream(filePath);
            prop.load(input);
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
    public String getProp(String key){
        return prop.getProperty(key);
    }
}
