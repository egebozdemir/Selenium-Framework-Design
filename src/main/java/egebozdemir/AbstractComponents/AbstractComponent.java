package egebozdemir.AbstractComponents;

import egebozdemir.PageObjects.CartPage;
import egebozdemir.PageObjects.OrdersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //Elements (common for all pages, reusable)
    @FindBy(css = "[routerlink*='cart']")
    WebElement cartIconHeader;

    @FindBy(css = "[routerlink*='myorders']")
    WebElement ordersHeader;

    By cartIconBy = By.cssSelector("[routerlink*='cart']");
    By ordersIconBy = By.cssSelector("[routerlink*='myorders']");

    //Action Methods (common for all pages, reusable)
    public void waitForElementToAppear(By findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForElementToAppear(WebElement findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public void waitForElementToClick(By findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(findBy));
    }

    public void waitForElementToDisappear(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public CartPage goToCartPage(){
        waitForElementToClick(cartIconBy);
        cartIconHeader.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    public OrdersPage goToOrdersPage(){
        waitForElementToClick(ordersIconBy);
        ordersHeader.click();
        OrdersPage ordersPage = new OrdersPage(driver);
        return ordersPage;
    }

}
