package driver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeSuite;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium_helper.ElementManager;
import selenium_helper.ProjectConfig;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;

public class Driver {
    private ChromeOptions options;
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static ElementManager elementsNode;
    public static ProjectConfig configNode;

    @BeforeSuite
    public void initializeDriver(){
        configNode = new ProjectConfig("src/test/resources/config.properties");
        int explicitly_duration = Integer.parseInt(configNode.getProp("explicitly_wait_duration"));
        String browserType = configNode.getProp("browser_type");

        // Set Options to pass bot check
        options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);


        driver = createDriver(browserType);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(explicitly_duration));

        elementsNode = new ElementManager("src/test/resources/data.json");


    }

    @AfterSuite
    public void quitDriver(){
        if (driver != null) {
            driver.quit();
        }
    }

    private static WebDriver createDriver(String browserType) {
        if (browserType == null) {
            browserType = "chrome";
        }

        switch (browserType.toLowerCase().trim()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                chromeOptions.setExperimentalOption("useAutomationExtension", false);

                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(chromeOptions);

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--disable-blink-features=AutomationControlled");
                edgeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                edgeOptions.setExperimentalOption("useAutomationExtension", false);

                WebDriverManager.edgedriver().setup();
                return new EdgeDriver(edgeOptions);

            default:
                throw new IllegalArgumentException("Unsupported browser specified: " + browserType);
        }
    }


}
