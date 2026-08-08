package pages;

import dto.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.nio.file.WatchEvent;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver){
        PageFactory.initElements
                (new AjaxElementLocatorFactory(driver, 10),
                        this);
    }

    @FindBy(id = "email")
    WebElement inputEmail;
    @FindBy(id= "password")
    WebElement inputPassword;
    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement btnYalla;

    public void typeLoginForm(User user){
        inputEmail.sendKeys(user.getEmail());
        inputPassword.sendKeys(user.getPassword());

    }
    public  void clickYalla(){
        btnYalla.click();
    }
}
