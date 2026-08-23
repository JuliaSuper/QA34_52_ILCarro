package pages;

import dto.User;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//input[@ng-reflect-name='firstName']")
    WebElement inputFirstName;
    @FindBy(xpath = "//input[@ng-reflect-name='lastName']")
    WebElement inputLastName;
    @FindBy(xpath = "//input[@ng-reflect-name='email']")
    WebElement inputEmail;
    @FindBy(xpath = "//input[@ng-reflect-name='password']")
    WebElement inputPassword;
    @FindBy(xpath = "//div[@class='checkbox-container']")
    WebElement checkBoxIAgree;
    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement btnYalla;
    @FindBy(xpath = "//input[@id='terms-of-use']")
    WebElement checkBoxTermsOfUse;


    public void typeRegistrationForm(User user) {
        inputFirstName.sendKeys(user.getFirstName());
        inputLastName.sendKeys(user.getLastName());
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
    }

    public void clickCheckBoxIAgree() {
        checkBoxIAgree.click();
    }

    public void clickBtnYalla() {
        btnYalla.click();
    }

    public void clickCheckboxTermsOfUse() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", checkBoxTermsOfUse);
    }
}
