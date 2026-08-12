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
    @FindBy(xpath =
            "//input[@id='email']/following-sibling::div[contains(@class, 'error')]")
    WebElement errorEmail;
    @FindBy(xpath =
            "//div[contains(text(), 'Password is required')]")
    WebElement errorPasswordFormat;

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

    public boolean isYallaButtonEnabled() {
        return btnYalla.isEnabled();
    }

    public String getEmailErrorMessage() {
        return errorEmail.getText().trim();
    }

    public String getPasswordErrorMessage() {
        return errorPasswordFormat.getText().trim();
    }
}
