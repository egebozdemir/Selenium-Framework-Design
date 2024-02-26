package egebozdemir.Tests;

import egebozdemir.TestComponents.BaseTest;
import egebozdemir.PageObjects.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    //test data
    String productName = "ZARA COAT 3";
    String countryName = "tur";
    String userEmail = "ege@test.com";
    String userPassword = "Test1234*";
    String expConfirmMessage = "THANKYOU FOR THE ORDER.";

    @Test
    public void submitOrder() throws IOException, InterruptedException {
        //login
        ProductCataloguePage productCataloguePage = landingPage.loginApplication(userEmail, userPassword);
        //grab products in catalogue page and add the one to cart
        List<WebElement> products = productCataloguePage.getProductList();
        productCataloguePage.addProductToCart(productName);
        //go to cart page, verify the product is added to cart and proceed to checkout
        CartPage cartPage = productCataloguePage.goToCartPage();
        Assert.assertTrue(cartPage.isProductAddedToCart(productName));
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        //select country and submit the order
        checkoutPage.selectCountry(countryName);
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        //verify the confirmation message
        String confirmationMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase(expConfirmMessage));
    }

    @Test (dependsOnMethods = {"submitOrder"})
    public void checkOrderHistory(){
        //login
        ProductCataloguePage productCataloguePage = landingPage.loginApplication(userEmail, userPassword);
        //go to orders page and verify if the product is ordered
        OrdersPage ordersPage = productCataloguePage.goToOrdersPage();
        Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
    }

}
