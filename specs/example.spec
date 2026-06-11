
# Shoping From Hepsibuarada

This sepcification for testing login,search and add to cart process in Hepsibuarada.

Just for development
//* important delay for "5" seconds


## Visit The Wesbiste
* Go To "https://www.hepsiburada.com/"
* Click On "hb-accept-all" by "id" on shadow root "efilli-layout-dynamic"

## Go To Login Page
* Find Element Where "id" is "myAccount" then click
* Find Element Where "id" is "login" then click

## Login Processes
* Find Failed Where "id" is "txtUserName" then fill with "cexop66536@alf5.com"
* Find Failed Where "id" is "txtPassword" then fill with <file:password.txt>
* Find Element Where "id" is "btnLogin" then click
* Verify Element where "xpath" is "//*[@data-test-id='account']"
* Verify User Logged In Successfully

## Search For "bilgisayar"
* Find Element Where "id" is "searchBoxOld" then click
* Move to "0" , "0" then click
* Find Element Where "id" is "searchBoxOld" then click
* Find Failed Where "css_selector" is "input[data-test-id='search-bar-input']" then fill with "bilgisayar"
* Press "ENTER"

## Verify Result Page
* Verify Element where "css_selector" is "[class*='productListContent']"
* Verify The Search Response

## Verify The Product In Second Row Existence
* Verify Element where "css_selector" is "[class*='productListContent'][id='1'] li:nth-of-type(5n)"


## Add Product To The Cart
* Find Element Where "css_selector" is "[class*='productListContent'][id='1'] li:nth-of-type(5n)" then click
* Verify Windows Count Equals To "2"
* Verify Redirection To Product Page
* Switch To "2" Window
* Find Element Where "css_selector" is "button[data-test-id='addToCart'][kind='primary']" then click
* Verify Element where "css_selector" is "[class*='checkoutui-SalesFrontCash']"
* Move to "0" , "0" then click
* Verify Product Added To Cart

## Open Cart
* Find Element Where "id" is "shoppingCart" then click

## Control The Product Inside The Cart
* Verify Product Visible In Cart
* Verify Element where "css_selector" is "[class*='basket_items'] [class*='product_box'] [class*='product_area'] [class*='product_details'] [class*='product_name']"
* Verify Element where "css_selector" is "[class*='basket_items'] [class*='product_box'] [class*='product_area'] [class*='product_details'] [class*='product_variant']"
* Expect "css_selector" "[class*='basket_items'] [class*='product_box'] [class*='product_area'] [class*='product_details'] [class*='product_variant']" to contain "GB"




