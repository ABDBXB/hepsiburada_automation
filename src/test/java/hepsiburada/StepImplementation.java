package hepsiburada;

import com.thoughtworks.gauge.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StepImplementation {

    @Step("Verify User Logged In Successfully")
    public void verifyUserLoggedInSuccessfully(){
//        Kullanıcı başarılı şekilde login olur
        By accountSelector = By.xpath("//*[@data-test-id='account']");
        WebElement headerAccountBtn = Driver.wait.until(ExpectedConditions.visibilityOfElementLocated(accountSelector));
        assertThat(headerAccountBtn.getText()).contains("Hesabım").as("Login Failed");
//        Gauge.writeMessage("✔ LOGGED IN SUCCESSFULLY");
    }

    @Step("Verify The Search Response")
    public void verifySearchResultsContent(){
//        Arama sonuçları kullanıcı girdisine uygun gelir
        By searchResultHeaderSelector = By.xpath("//*[@data-test-id='header-h1']");
        WebElement searchResultHeader = Driver.wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultHeaderSelector));

        assertThat(searchResultHeader.getText()).contains("bilgisayar").as("Search Result isn't matched");
    }
    @Step("Verify Redirection To Product Page")
    public void verifyRedirectionToProductPage(){
//        İkinci satırdaki ilk ürünün sayfasına yönlenir
        Set<String> windows = Driver.driver.getWindowHandles();
        int windowsCount = windows.size();

        assertThat(windowsCount).isGreaterThan(1).as("Search Result isn't matched");
    }

    @Step("Verify Product Added To Cart")
    public void verifyProductAddedToCart(){
//        İkinci satırdaki ilk ürünün sayfasına yönlenir
        By cartItemCountSelector = By.id("cartItemCount");
        WebElement cartItemCount = Driver.wait.until(ExpectedConditions.visibilityOfElementLocated(cartItemCountSelector));

        assertThat(cartItemCount.getText()).isNotEqualTo("0").as("Product wasn't added to the cart");
    }

    @Step("Verify Product Visible In Cart")
    public void verifyProductVisibleInCart(){
//        Sepet ekranında eklenen ürün görünür
        By productBoxInCartSelector = By.cssSelector("[class*='basket_items'] [class*='product_box'] [class*='product_area'] [class*='product_details'] [class*='product_name']");
        WebElement productBoxInCart = Driver.wait.until(ExpectedConditions.visibilityOfElementLocated(productBoxInCartSelector));

        assertThat(productBoxInCart.getText()).as("Product Can't be found in the cart").isNotNull();
    }

}
