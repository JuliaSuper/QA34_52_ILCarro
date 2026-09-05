package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.time.LocalDate;

import static utils.PropertiesReader.*;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        setDriver(driver);
        //driver.get("https://ilcarro.web.app/search");
        driver.get(getProperty("base.properties", "baseUrl"));
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//a[text()=' Log in ']")
    WebElement btnLogin;
    @FindBy(xpath = "//a[text()=' Sign up ']")
    WebElement btnSignUp;
    @FindBy(id = "city")
    WebElement inputCity;
    @FindBy(id = "dates")
    WebElement inputDates;
    @FindBy(xpath = "//button[@type='submit' and text()='Y’alla!']")
    WebElement btnYalla;

    public void clickBtnLogin() {
        btnLogin.click();
    }

    public void clickBtnSignUp() {
        btnSignUp.click();
    }

    public void  typeSearchForm(String city, LocalDate startDate,
                                LocalDate endDate){
        inputCity.sendKeys(city);
//        System.out.println(startDate);
//        System.out.println(endDate);
//        System.out.println(startDate.getMonthValue());
//        System.out.println(startDate.getDayOfMonth());
        String dates = startDate.getMonthValue()+"/"+startDate.getDayOfMonth()
                +"/"+startDate.getYear() +" - " +endDate.getMonthValue()+"/"+endDate.getDayOfMonth()
                +"/"+endDate.getYear();
//        System.out.println(dates);
        inputDates.sendKeys(dates);
    }

    public void clickBtnYallaSubmit() {
        btnYalla.click();
    }
    public boolean isErrorMessagePresentCity(String expectedText) {
        return isMessageDisplayed(expectedText);
    }
    public boolean isErrorMessagePresent(String messageText) {
        String xpath = String.format("//div[@class='error' and contains(normalize-space(text()), \"%s\")]", messageText.trim());
        return driver.findElement(By.xpath(xpath)).isDisplayed();
    }

    public void typeSearchFormWithEmptyDates(String city) {
        inputCity.clear();
        inputCity.sendKeys(city);
        inputDates.clear();
        inputCity.click();
    }
}


