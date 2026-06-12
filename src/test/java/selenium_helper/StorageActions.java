package selenium_helper;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.SuiteDataStore;
import hepsiburada.Driver;
import org.jspecify.annotations.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StorageActions {


    @Step("important delay for <delay> seconds")
    public void importantDelay(Long delay) throws InterruptedException {
        delay = delay * 1000; // Convert
        Thread.sleep(delay);
    }

    @Step("Store Element Text <attribute> <value> as <uniqueKey>")
    public void storeElementTextAs(@NonNull String attribute, String value, String uniqueKey) {
        value = Driver.elementsNode.path(value).asText();
        attribute = Driver.elementsNode.path(attribute).asText();
        uniqueKey = Driver.elementsNode.path(uniqueKey).asText();
        By selector = InteractionActions.getSelectorBy(attribute, value);
        WebElement element = Driver.wait.until(ExpectedConditions.visibilityOfElementLocated(selector));

        SuiteDataStore.put(uniqueKey, element.getText());

    }
}
