import egebozdemir.pageobjects.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class SubmitOrderTest {

    public static  void main (String[] args){

        //desired product and country code for the order
        String productName = "ZARA COAT 3";
        String countryName = "tur";

        //chrome driver initialisation with webdrivermanager
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        //go to landing page and login
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        ProductCatalogue productCatalogue = landingPage.loginApplication("ege@test.com", "Test1234*");

        //grab products in catalogue page and add the one to cart
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);

        //go to cart page, verify the product is added to cart and proceed to checkout
        CartPage cartPage = productCatalogue.goToCartPage();
        Assert.assertTrue(cartPage.isProductAddedToCart(productName));
        CheckoutPage checkoutPage = cartPage.goToCheckout();

        //select country and submit the order
        checkoutPage.selectCountry(countryName);
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

        //verify the confirmation message
        String confirmationMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        System.out.println("TEST IS SUCCESSFULLY PASSED");

        //close the driver
        driver.close();

    }
}
