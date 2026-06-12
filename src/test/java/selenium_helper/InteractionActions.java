package selenium_helper;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.SuiteDataStore;
import hepsiburada.Driver;
import org.jspecify.annotations.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class InteractionActions {


    @Step("Find Element Where <attribute> is <value> then click")
    public void findElementThenClick(@NonNull String attribute, String value) {
        value = Driver.elementsNode.path(value).asText();
        attribute = Driver.elementsNode.path(attribute).asText();
        By selector = getSelectorBy(attribute, value);

        WebElement element = Driver.wait.until(ExpectedConditions.elementToBeClickable(selector));
        element.click();

    }

    @Step("Find Failed Where <attribute> is <value> then fill with <fillWith>")
    public void findInputThenFill(@NonNull String attribute, String value, String fillWith) {
        value = Driver.elementsNode.path(value).asText();
        attribute = Driver.elementsNode.path(attribute).asText();
        fillWith = Driver.elementsNode.path(fillWith).asText();
        By selector = getSelectorBy(attribute, value);

        WebElement element = Driver.wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        element.clear();
        element.sendKeys(fillWith);

    }

    @Step("Click On <value> by <attribute> on shadow root <shadowRootID>")
    public void clickInShadowRoot(String value, String attribute, String shadowRootID) throws InterruptedException {
        value = Driver.elementsNode.path(value).asText();
        attribute = Driver.elementsNode.path(attribute).asText();
        shadowRootID = Driver.elementsNode.path(shadowRootID).asText();

        WebElement shadowHost = Driver.driver.findElement(By.tagName(shadowRootID));

        SearchContext shadowRoot = shadowHost.getShadowRoot();

        By selector = getSelectorBy(attribute, value);
        WebElement acceptButton = shadowRoot.findElement(selector);
        acceptButton.click();
    }

    @Step("Move to <x> , <y> then click")
    public void moveToThenClick(String x, String y) {
        x = Driver.elementsNode.path(x).asText();
        y = Driver.elementsNode.path(y).asText();

        Actions pageActions = new Actions(Driver.driver);
        int xOffset = Integer.parseInt(x);
        int yOffset = Integer.parseInt(y);
        pageActions.moveByOffset(xOffset, yOffset).click().build().perform();
    }

    @Step("Press <buttonType>")
    public void pressButton(String buttonType) {
        buttonType = Driver.elementsNode.path(buttonType).asText();
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

    public static By getSelectorBy(@NonNull String attribute, String value) {
        By selector;
        switch (attribute.toLowerCase()) {
            case "id":
                selector = By.id(value);
                break;
            case "className":
                selector = By.className(value);
                break;
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
