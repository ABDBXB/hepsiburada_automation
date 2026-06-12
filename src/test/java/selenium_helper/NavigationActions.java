package selenium_helper;

import com.thoughtworks.gauge.Step;
import hepsiburada.Driver;
import org.jspecify.annotations.NonNull;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NavigationActions {


    @Step("Go To <url>")
    public void visitAWebsite(String url) throws InterruptedException {
        url = Driver.elementsNode.path(url).asText();
        Driver.driver.get(url);
    }

    @Step("Verify Windows Count Equals To <number>")
    public void verifyWindowsCount(@NonNull String number) {
        number = Driver.elementsNode.path(number).asText();
        int inputNumber = Integer.parseInt(number);
        Set<String> windows = Driver.driver.getWindowHandles();
        assertThat(windows.size()).isEqualTo(inputNumber).as("Windows Count does not match");
    }

    @Step("Switch To <number> Window")
    public void switchToWindowByNumber(@NonNull String number) {
        number = Driver.elementsNode.path(number).asText();
        Set<String> windows = Driver.driver.getWindowHandles();
        int windowsCount = windows.size();
        int expectedNumber = Integer.parseInt(number);
        assertThat(expectedNumber).as("The requested window number " + expectedNumber + " is invalid for total windows: " + windowsCount).isBetween(1, windowsCount);

        String requestedWindowHandle = windows.toArray(new String[0])[Integer.parseInt(number) - 1];
        Driver.driver.switchTo().window(requestedWindowHandle);
    }
}
