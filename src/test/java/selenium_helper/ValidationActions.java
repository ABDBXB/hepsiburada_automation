package selenium_helper;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.SuiteDataStore;
import hepsiburada.Driver;
import org.jspecify.annotations.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ValidationActions {


    @Step("Verify Element where <attribute> is <value>")
    public void verifyElementExistence(@NonNull String attribute, String value) {
        value = Driver.elementsNode.path(value).asText();
        attribute = Driver.elementsNode.path(attribute).asText();
        By selector = InteractionActions.getSelectorBy(attribute, value);
        Driver.wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    @Step("Expect <attribute> <value> to contain <expectedTextKey>")
    public void expectContainTexts(@NonNull String attribute, String value, String expectedTextKey) {
        value = Driver.elementsNode.path(value).asText();
        attribute = Driver.elementsNode.path(attribute).asText();
        expectedTextKey = Driver.elementsNode.path(expectedTextKey).asText();

        String expectedTextValue = (String) SuiteDataStore.get(expectedTextKey);
        By selector = InteractionActions.getSelectorBy(attribute, value);

        WebElement element = Driver.wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        assertThat(element.getText().toLowerCase()).isEqualTo(expectedTextValue.toLowerCase()).as("The Text Doesn't Contain" + expectedTextValue);
    }
}
