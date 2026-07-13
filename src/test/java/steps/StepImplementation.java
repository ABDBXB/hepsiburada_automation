package steps;

import com.thoughtworks.gauge.Step;
import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StepImplementation {

    // Kullanıcı başarılı şekilde login olur
    @Step("Assertion|Verify User Logged In Successfully")
    public void verifyUserLoggedInSuccessfully() {
        By accountSelector = By.xpath("//*[@data-test-id='account']");
        WebElement headerAccountBtn = Driver.wait.until(ExpectedConditions.visibilityOfElementLocated(accountSelector));
        assertThat(headerAccountBtn).as("Login Failed").isNotNull();
        assertThat(headerAccountBtn.getText()).contains("Hesabım").as("Login Failed");
    }

    // Arama sonuçları kullanıcı girdisine uygun gelir
    @Step("Assertion|Verify The Search Response")
    public void verifySearchResultsContent() {
        By searchResultHeaderSelector = By.xpath("//*[@data-test-id='header-h1']");
        WebElement searchResultHeader = Driver.wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultHeaderSelector));
        assertThat(searchResultHeader).as("Search Result header can't be found").isNotNull();
        assertThat(searchResultHeader.getText()).contains("bilgisayar").as("Search Result isn't matched");
    }

    // İkinci satırdaki ilk ürünün sayfasına yönlenir
    @Step("Assertion|Verify Redirection To Product Page")
    public void verifyRedirectionToProductPage() {
        Set<String> windows = Driver.driver.getWindowHandles();
        int windowsCount = windows.size();

        assertThat(windowsCount).isGreaterThan(1).as("Search Result isn't matched");
    }

    // Ürün sepete eklenir
    @Step("Assertion|Verify Product Added To Cart")
    public void verifyProductAddedToCart() {
        By cartItemCountSelector = By.id("cartItemCount");
        WebElement cartItemCount = Driver.wait.until(ExpectedConditions.visibilityOfElementLocated(cartItemCountSelector));
        assertThat(cartItemCount.getText()).isNotEqualTo("0").as("Product wasn't added to the cart");
    }

    // Sepet ekranında eklenen ürün görünür
    @Step("Assertion|Verify Product Visible In Cart")
    public void verifyProductVisibleInCart() {
        WebElement productBoxInCart = null;
        By productBoxInCartSelector = By.cssSelector("[class*='basket_items'] [class*='product_box'] [class*='product_area'] [class*='product_details'] [class*='product_name']");
        productBoxInCart = Driver.wait.until(ExpectedConditions.visibilityOfElementLocated(productBoxInCartSelector));
        assertThat(productBoxInCart.isDisplayed()).as("Product Can't be found in the cart").isTrue();
    }

}
