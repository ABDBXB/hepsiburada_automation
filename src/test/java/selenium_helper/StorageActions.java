package selenium_helper;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.SuiteDataStore;
import driver.Driver;
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

    @Step("Store Element Text <key> as <uniqueKey>")
    public void storeElementTextAs(@NonNull String key, String uniqueKey) {
        String value = Driver.elementsNode.get(key).getValue();
        String attribute = Driver.elementsNode.get(key).getType();
        uniqueKey = Driver.elementsNode.get(uniqueKey).getValue();
        By selector = InteractionActions.getSelectorBy(attribute, value);
        WebElement element = Driver.wait.until(ExpectedConditions.visibilityOfElementLocated(selector));

        SuiteDataStore.put(uniqueKey, element.getText());

    }
}
