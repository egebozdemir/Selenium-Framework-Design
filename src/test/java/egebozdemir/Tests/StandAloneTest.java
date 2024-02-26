package egebozdemir.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {

    public static void main(String[] args){

        //desired product to order
        String productName = "ZARA COAT 3";

        //chrome driver initialisation with webdrivermanager
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client/");

        //enter user credentials and click login button
        driver.findElement(By.id("userEmail")).sendKeys("ege@test.com");
        driver.findElement(By.id("userPassword")).sendKeys("Test1234*");
        driver.findElement(By.id("login")).click();

        //list all the products and find the desired one
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod = products.stream().filter(product->
                product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);

        //click add to cart button in the product tile
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        //wait until the success msg displayed and loading cursor is vanished
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        //click cart icon
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        //check if the desired product is present in the cart
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);

        //click checkout button
        driver.findElement(By.cssSelector(".totalRow button")).click();

        //typing to select a country from auto-suggestion dropdown
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"tur").build().perform();

        //wait for the suggested options displayed and select one (Turkey)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[1]")).click();

        //submit the checkout form
        driver.findElement(By.cssSelector(".action__submit")).click();

        //check if the success message displayed for the successfully placed order
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        System.out.println("TEST IS SUCCESSFULLY PASSED");

        //close the browser
        driver.close();
    }
}
