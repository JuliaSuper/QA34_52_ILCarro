package pages;

import dto.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.nio.file.WatchEvent;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        PageFactory.initElements
                (new AjaxElementLocatorFactory(driver, 10),
                        this);
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
    @FindBy(xpath =
            "//input[@id='email']/following-sibling::div[contains(@class, 'error')]")
    WebElement errorEmail;

    public void typeLoginForm(User user) {
        if (user.getUsername() != null) {
            inputEmail.click();
            inputEmail.clear();
            inputEmail.sendKeys(user.getUsername());
        }
        if (user.getPassword() != null) {
            inputPassword.click();
            inputPassword.clear();
            inputPassword.sendKeys(user.getPassword());
        }
    }

    public void clickYalla() {
        btnYalla.click();
    }

    public boolean ispPopUpSuccessLoginDisplayed() {
        return isElementDisplayed(popUpSuccessLogin);
    }

    public boolean ispPopUpLoginFailedDisplayed() {
        return isElementDisplayed(popUpLoginFailed);
    }

    public boolean isBTNYallaEnabled() {
        return btnYalla.isEnabled();
    }

    public boolean isYallaButtonEnabled() {
        return btnYalla.isEnabled();
    }

    public String getEmailErrorMessage() {
        return errorEmail.getText().trim();
    }
}
