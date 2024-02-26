package egebozdemir.PageObjects;

import egebozdemir.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;

    public CheckoutPage(WebDriver driver){
        //initialization
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //Elements
    @FindBy(css = "[placeholder='Select Country']")
    WebElement country;

    @FindBy(xpath = "(//button[contains(@class, 'ta-item')])[1]")
    WebElement selectTurkey;

    @FindBy(css = ".action__submit")
    WebElement submit;

    By result = By.cssSelector(".ta-results");

    //Action Methods
    public void selectCountry(String countryName){
        Actions a = new Actions(driver);
        a.sendKeys(country,countryName).build().perform();
        waitForElementToAppear(result);
        selectTurkey.click();
    }

    public ConfirmationPage submitOrder(){
        submit.click();
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;
    }
}
