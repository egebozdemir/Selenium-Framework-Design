package egebozdemir.Tests;

import egebozdemir.PageObjects.CartPage;
import egebozdemir.PageObjects.ProductCatalogue;
import egebozdemir.TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {

    @Test
    public void loginValidateError() {
        //test data
        String incorrectUserEmail = "asdf@qwerty.com";
        String incorrectUserPassword = "1234Qwerty*";

        //login with incorrect credentials and verify error message
        landingPage.loginApplication(incorrectUserEmail, incorrectUserPassword);
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
        System.out.println("TEST IS SUCCESSFULLY PASSED!");
    }

    @Test
    public void productValidateError() throws IOException {
        //test data
        String productName = "ZARA COAT 3";
        String incorrectProductName = "ZARA COAT 4";
        String userEmail = "ege@test.com";
        String userPassword = "Test1234*";

        //login, add to cart, go to cart, check the incorrect product if added to cart
        ProductCatalogue productCatalogue = landingPage.loginApplication(userEmail, userPassword);
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();
        Assert.assertFalse(cartPage.isProductAddedToCart(incorrectProductName));
        System.out.println("TEST IS SUCCESSFULLY PASSED!");
    }

}
