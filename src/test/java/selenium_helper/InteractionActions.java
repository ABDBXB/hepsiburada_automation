package selenium_helper;

import com.thoughtworks.gauge.Step;
import driver.Driver;
import org.jspecify.annotations.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class InteractionActions {


    @Step("Find Element Where <key> then click")
    public void findElementThenClick(@NonNull String key) {
        String value = Driver.elementsNode.get(key).getValue();
        String attribute = Driver.elementsNode.get(key).getType();
        By selector = getSelectorBy(attribute, value);

        WebElement element = Driver.wait.until(ExpectedConditions.elementToBeClickable(selector));
        element.click();

    }

    @Step("Find Failed <key> then fill with <fillWith>")
    public void findInputThenFill(@NonNull String key, String fillWith) {

        String value = Driver.elementsNode.get(key).getValue();
        String attribute = Driver.elementsNode.get(key).getType();
        fillWith = Driver.elementsNode.get(fillWith).getValue();

        By selector = getSelectorBy(attribute, value);

        WebElement element = Driver.wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        element.clear();
        element.sendKeys(fillWith);

    }

    @Step("Click On <key> on shadow root <shadowRootID>")
    public void clickInShadowRoot(String key, String shadowRootID) throws InterruptedException {
        String value = Driver.elementsNode.get(key).getValue();
        String attribute = Driver.elementsNode.get(key).getType();
        shadowRootID = Driver.elementsNode.get(shadowRootID).getValue();

        WebElement shadowHost = Driver.driver.findElement(By.tagName(shadowRootID));

        SearchContext shadowRoot = shadowHost.getShadowRoot();

        By selector = getSelectorBy(attribute, value);
        WebElement acceptButton = shadowRoot.findElement(selector);
        acceptButton.click();
    }

    @Step("Move to <x> , <y> then click")
    public void moveToThenClick(String x, String y) {
        x = Driver.elementsNode.get(x).getValue();
        y = Driver.elementsNode.get(y).getValue();

        Actions pageActions = new Actions(Driver.driver);
        int xOffset = Integer.parseInt(x);
        int yOffset = Integer.parseInt(y);
        pageActions.moveByOffset(xOffset, yOffset).click().build().perform();
    }

    @Step("Press <buttonType>")
    public void pressButton(String buttonType) {
        buttonType = Driver.elementsNode.get(buttonType).getValue();
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
            case "css":
                selector = By.cssSelector(value);
                break;
            default:
                selector = By.id(value);
                break;
        }
        return selector;
    }

}
