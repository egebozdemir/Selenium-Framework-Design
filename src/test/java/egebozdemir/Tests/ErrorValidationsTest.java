package egebozdemir.Tests;

import egebozdemir.PageObjects.CartPage;
import egebozdemir.PageObjects.ProductCataloguePage;
import egebozdemir.TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {

    //test data
    String productName = "ZARA COAT 3";
    String incorrectProductName = "ZARA COAT 4";
    String userEmail = "ege@test.com";
    String userPassword = "Test1234*";
    String incorrectUserEmail = "asdf@qwerty.com";
    String incorrectUserPassword = "1234Qwerty*";

    @Test(groups = {"ErrorHandling"})
    public void loginValidateError() {
        //login with incorrect credentials and verify error message
        landingPage.loginApplication(incorrectUserEmail, incorrectUserPassword);
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }

    @Test
    public void productValidateError() throws IOException, InterruptedException {
        //login, add to cart, go to cart, check if the incorrect product is added to cart
        ProductCataloguePage productCataloguePage = landingPage.loginApplication(userEmail, userPassword);
        List<WebElement> products = productCataloguePage.getProductList();
        productCataloguePage.addProductToCart(productName);
        CartPage cartPage = productCataloguePage.goToCartPage();
        Assert.assertFalse(cartPage.isProductAddedToCart(incorrectProductName));
    }

}
