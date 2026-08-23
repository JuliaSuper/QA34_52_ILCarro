package pages;

import dto.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(id = "email")
    WebElement inputEmail;
    @FindBy(id = "password")
    WebElement inputPassword;
    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement btnYalla;
    @FindBy(xpath = "//h1[text()='Logged in']")
    WebElement popUpSuccessLogin;
    @FindBy(xpath = "//h1[text()='Login failed']")
    WebElement popUpLoginFailed;


    public void typeLoginForm(User user) {
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
    }

    public void clickBtnYalla() {
        btnYalla.click();
    }

    public boolean ispPopUpSuccessLoginDisplayed() {
        return isElementDisplayed(popUpSuccessLogin);
    }

    public boolean ispPopUpLoginFailedDisplayed() {
        return isElementDisplayed(popUpLoginFailed);
    }

    public boolean isBtnYallaEnabled() {
        return btnYalla.isEnabled();
    }
}