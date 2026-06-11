package selenium_helper;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import hepsiburada.Driver;
import org.jspecify.annotations.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.swing.*;

import java.security.Key;
import java.util.Locale;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CustomActions {

    @Step("Go To <url>")
    public void visitAWebsite(String url) throws InterruptedException {
        // "C:\Program Files\Google\Chrome\Application\chrome.exe" --remote-debugging-port=9222 --user-data-dir="C:\selenium_chrome_profile"
        Driver.driver.get(url);
        assertThat(url).isEqualTo(Driver.driver.getCurrentUrl()).as("Field to go to the URL");
    }

    @Step("Find Element Where <attribute> is <value> then click")
    public void findElementThenClick(@NonNull String attribute, String value) {
        WebElement element = null;
        By selector = getSelectorBy(attribute, value);
        try {
            element = Driver.wait.until(ExpectedConditions.elementToBeClickable(selector));
        } catch (Exception e) {
            assertThat(element).isNotNull().as("Can't find the element " + attribute + " | " + value);
        }
        element.click();
    }

    @Step("Find Failed Where <attribute> is <value> then fill with <fillWith>")
    public void findInputThenFill(@NonNull String attribute, String value, String fillWith) {
        WebElement element = null;
        By selector = getSelectorBy(attribute, value);
        try {
            element = Driver.wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        } catch (Exception e) {
            assertThat(element).as("Can't find the Input " + attribute + " | " + value).isNotNull();
        }
        element.clear();
        element.sendKeys(fillWith);
    }

    @Step("Verify Element where <attribute> is <value>")
    public void verifyElementExistence(@NonNull String attribute, String value) {
        WebElement element = null;
        By selector = getSelectorBy(attribute, value);
        try {
            Driver.wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        } catch (Exception e) {
            assertThat(element).isNotNull().as("Can't find the element " + attribute + " | " + value);
        }
    }

    @Step("Verify Windows Count Equals To <number>")
    public void verifyWindowsCount(@NonNull String number) {
        int inputNumber = Integer.parseInt(number);
        Set<String> windows = Driver.driver.getWindowHandles();
        assertThat(windows.size()).isEqualTo(inputNumber).as("Windows Count Is Not Matched");
    }

    @Step("Switch To <number> Window")
    public void switchToWindowByNumber(@NonNull String number) {
        Set<String> windows = Driver.driver.getWindowHandles();
        int windowsCount = windows.size();
        assertThat(number).hasSizeLessThan(windowsCount).as("The Number " + number + " is bigger then windows count " + windowsCount);

        String requestedWindowHandle = windows.toArray(new String[0])[Integer.parseInt(number) - 1];
        Driver.driver.switchTo().window(requestedWindowHandle);
    }


    @Step("Click On <value> by <attribute> on shadow root <shadowRootID>")
    public void clickInShadowRoot(String value, String attribute, String shadowRootID) throws InterruptedException {
        WebElement shadowHost = Driver.driver.findElement(By.tagName(shadowRootID));

        SearchContext shadowRoot = shadowHost.getShadowRoot();

        By selector = getSelectorBy(attribute, value);
        WebElement acceptButton = shadowRoot.findElement(selector);
        acceptButton.click();
    }

    @Step("Move to <x> , <y> then click")
    public void moveToThenClick(String x, String y) {
        Actions pageActions = new Actions(Driver.driver);
        int xOffset = Integer.parseInt(x);
        int yOffset = Integer.parseInt(y);
        pageActions.moveByOffset(xOffset, yOffset).click().build().perform();
    }

    @Step("Expect <attribute> <value> to contain <expectedText>")
    public void expectContainTexts(@NonNull String attribute, String value, String expectedText) {
        By selector = getSelectorBy(attribute, value);
        WebElement element = null;
        try {
            element = Driver.wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        } catch (Exception e) {
            assertThat(element.getText()).contains(expectedText).as("The Text Doesn't Contain" + expectedText);
        }

    }

    @Step("important delay for <delay> seconds")
    public void importantDelay(Long delay) throws InterruptedException {
        delay = delay * 1000; // Convert
        Thread.sleep(delay);
    }

    @Step("Press <buttonType>")
    public void pressButton(String buttonType) {
        Actions pageActions = new Actions(Driver.driver);
        switch (buttonType.toLowerCase()) {
            case "enter":
                pageActions.sendKeys(Keys.ENTER).perform();
                break;
            case "pageup":
                pageActions.sendKeys(Keys.PAGE_UP).perform();
                break;
            case "pagedown":
                pageActions.sendKeys(Keys.PAGE_DOWN).perform();
                break;
            default:
                pageActions.sendKeys(Keys.ENTER).perform();
                break;
        }

    }

    private By getSelectorBy(@NonNull String attribute, String value) {
        By selector;
        switch (attribute.toLowerCase()) {
            case "id":
                selector = By.id(value);
                break;
            case "className":
                selector = By.className(value);
            case "xpath":
                selector = By.xpath(value);
                break;
            case "css_selector":
                selector = By.cssSelector(value);
                break;
            default:
                selector = By.id(value);
                break;
        }
        return selector;
    }

}
