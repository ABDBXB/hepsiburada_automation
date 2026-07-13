package selenium_helper;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.SuiteDataStore;
import driver.Driver;
import org.jspecify.annotations.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ValidationActions {


    @Step("Verify Element <key>")
    public void verifyElementExistence(@NonNull String key) {
        String value = Driver.elementsNode.get(key).getValue();
        String attribute = Driver.elementsNode.get(key).getType();
        By selector = InteractionActions.getSelectorBy(attribute, value);
        Driver.wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    @Step("Expect <key> to contain <expectedTextKey>")
    public void expectContainTexts(@NonNull String key ,String expectedTextKey) {
        String value = Driver.elementsNode.get(key).getValue();
        String attribute = Driver.elementsNode.get(key).getType();
        expectedTextKey = Driver.elementsNode.get(expectedTextKey).getValue();

        String expectedTextValue = (String) SuiteDataStore.get(expectedTextKey);
        By selector = InteractionActions.getSelectorBy(attribute, value);

        WebElement element = Driver.wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        assertThat(element.getText().toLowerCase()).isEqualTo(expectedTextValue.toLowerCase()).as("The Text Doesn't Contain" + expectedTextValue);
    }
}
