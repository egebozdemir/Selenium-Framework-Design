package egebozdemir.PageObjects;

import egebozdemir.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends AbstractComponent {

    WebDriver driver;

    public OrdersPage(WebDriver driver){
        //initialization
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //Elements
    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> orderProducts;

    //Action Methods
    public Boolean verifyOrderDisplay(String productName){
        Boolean match = orderProducts.stream().anyMatch(product->
                product.getText().equalsIgnoreCase(productName));
        return  match;
    }

}
