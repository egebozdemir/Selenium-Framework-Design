package egebozdemir.Tests;

import egebozdemir.TestComponents.BaseTest;
import egebozdemir.PageObjects.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String,String> input) throws InterruptedException {
        //login
        ProductCataloguePage productCataloguePage = landingPage.loginApplication(input.get("userEmail"), input.get("userPassword"));
        //grab products in catalogue page and add the one to cart
        productCataloguePage.addProductToCart(input.get("productName"));
        //go to cart page, verify the product is added to cart and proceed to checkout
        CartPage cartPage = productCataloguePage.goToCartPage();
        Assert.assertTrue(cartPage.isProductAddedToCart(input.get("productName")));
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        //select country and submit the order
        checkoutPage.selectCountry(input.get("countryName"));
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        //verify the confirmation message
        String confirmationMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test (dataProvider = "getData",dependsOnMethods = {"submitOrder"})
    public void checkOrderHistory(HashMap<String,String> input){
        //login
        ProductCataloguePage productCataloguePage = landingPage.loginApplication(input.get("userEmail"), input.get("userPassword"));
        //go to orders page and verify if the product is ordered
        OrdersPage ordersPage = productCataloguePage.goToOrdersPage();
        Assert.assertTrue(ordersPage.verifyOrderDisplay(input.get("productName")));
    }

    @DataProvider
    public Object[][] getData() throws IOException {

/*      HashMap<String,String> map1 = new HashMap<String,String>();
        map1.put("userEmail", "ege@test.com");
        map1.put("userPassword", "Test1234*");
        map1.put("productName", "ZARA COAT 3");
        map1.put("countryName", "tur");

        HashMap<String,String> map2 = new HashMap<String,String>();
        map2.put("userEmail", "auto.ege@test.com");
        map2.put("userPassword", "9876.Qwerty");
        map2.put("productName", "ADIDAS ORIGINAL");
        map2.put("countryName", "ind");
*/
        //calling getJsonDataToMap inherited from BaseTest to get List of Hashmaps as test data will be used in each run of the test
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"/src/test/java/egebozdemir/Data/PurchaseOrder.json");
        return new Object[][] {{data.get(0)}, {data.get(1)}};
    }
}
