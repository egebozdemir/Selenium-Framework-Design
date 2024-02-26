package egebozdemir.PageObjects;

import egebozdemir.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//PageFactory
public class LandingPage extends AbstractComponent {

    WebDriver driver;

    public LandingPage(WebDriver driver){
        //initialization
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //Elements
    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement userPassword;

    @FindBy(id="login")
    WebElement loginBtn;

    @FindBy(css="[class*='flyInOut")
    WebElement errorMessage;

    //Action Methods
    public ProductCatalogue loginApplication(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginBtn.click();
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
    }

    public String getErrorMessage(){
        waitForElementToAppear(errorMessage);
        return errorMessage.getText();
    }

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client/");
    }

}
