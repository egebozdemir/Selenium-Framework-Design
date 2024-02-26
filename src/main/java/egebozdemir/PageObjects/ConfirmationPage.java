package egebozdemir.PageObjects;

import egebozdemir.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponent {

    WebDriver driver;

    public ConfirmationPage(WebDriver driver){
        //initialization
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //Elements
    @FindBy(css = ".hero-primary")
    WebElement confirmationMessage;

    //Action Methods
    public String getConfirmationMessage(){
        return confirmationMessage.getText();
    }

}
