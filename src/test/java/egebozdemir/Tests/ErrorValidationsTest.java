package egebozdemir.Tests;

import egebozdemir.PageObjects.CartPage;
import egebozdemir.PageObjects.ProductCataloguePage;
import egebozdemir.TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {

    @Test(dataProvider = "getData", groups = {"ErrorHandling"})
    public void loginValidateError(HashMap<String,String> input) {
        //login with incorrect credentials and verify error message
        landingPage.loginApplication(input.get("incorrectUserEmail"), input.get("incorrectUserPassword"));
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }

    @Test(dataProvider = "getData", groups = {"ErrorHandling"})
    public void productValidateError(HashMap<String,String> input) throws InterruptedException {
        //login, add to cart, go to cart, check if the incorrect product is added to cart
        ProductCataloguePage productCataloguePage = landingPage.loginApplication(input.get("userEmail"), input.get("userPassword"));
        List<WebElement> products = productCataloguePage.getProductList();
        productCataloguePage.addProductToCart(input.get("productName"));
        CartPage cartPage = productCataloguePage.goToCartPage();
        Assert.assertFalse(cartPage.isProductAddedToCart(input.get("incorrectProductName")));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        //calling getJsonDataToMap inherited from BaseTest to get List of Hashmaps as test data will be used in each run of the test
        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "/src/test/java/egebozdemir/Data/ErrorValidations.json");
        return new Object[][]{{data.getFirst()}};
    }

}
