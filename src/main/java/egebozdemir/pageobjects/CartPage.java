package egebozdemir.pageobjects;

import egebozdemir.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

//PageFactory
public class CartPage extends AbstractComponent {

    WebDriver driver;

    public CartPage(WebDriver driver){
        //initialization
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //Elements
    @FindBy(css = ".cartSection h3")
    List<WebElement> cartProducts;

    @FindBy(css = ".totalRow button")
    WebElement checkoutButton;


    //Action Methods
    public List<WebElement> getCartProducts(){
        return cartProducts;
    }

    public Boolean isProductAddedToCart(String productName){
        Boolean match = cartProducts.stream().anyMatch(product->
                product.getText().equalsIgnoreCase(productName));
        return  match;
    }

    public CheckoutPage goToCheckout(){
        checkoutButton.click();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        return checkoutPage;
    }

}
