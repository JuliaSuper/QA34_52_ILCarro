package pages;

import dto.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import utils.enums.Fuel;

import java.io.File;

public class LetTheCarWorkPage extends BasePage {
    public LetTheCarWorkPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(id = "pickUpPlace")
    WebElement inputLocation;
    @FindBy(id = "make")
    WebElement inputManufacture;
    @FindBy(id = "model")
    WebElement inputModel;
    @FindBy(id = "year")
    WebElement inputYear;
    @FindBy(id = "fuel")
    WebElement selectFuel;
    @FindBy(id = "seats")
    WebElement inputSeats;
    @FindBy(id = "class")
    WebElement inputCarClass;
    @FindBy(id = "serialNumber")
    WebElement inputSerialNumber;
    @FindBy(id = "price")
    WebElement inputPrice;
    @FindBy(id = "about")
    private WebElement inputAbout;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSubmit;
    @FindBy(xpath = "//div[text()=' Wrong address ']")
    WebElement errorLocation;
    @FindBy(id = "photos")
    WebElement inputImage;

    public void typeAddNewCarForm(Car car) {
        inputLocation.sendKeys(car.getCity());
        inputManufacture.sendKeys(car.getManufacture());
        inputModel.sendKeys(car.getModel());
        inputYear.sendKeys(car.getYear());
        chooseFuel(car.getFuel());
//        inputSeats.sendKeys(String.valueOf(car.getSeats()));
//        inputSeats.sendKeys(car.getSeats().toString());
//        inputSeats.sendKeys(car.getSeats()+"");
        if (car.getSeats() !=null){
        inputSeats.sendKeys(Integer.toString(car.getSeats()));
        }
        inputCarClass.sendKeys(car.getCarClass());
        inputSerialNumber.sendKeys(car.getSerialNumber());
        if (car.getPricePerDay() !=null){
        inputPrice.sendKeys(String.valueOf(car.getPricePerDay()));}
        inputAbout.sendKeys(car.getAbout());
    }

    public void downloadImage(String fileName) {
        inputImage.sendKeys(new File("src/test/resources/" + fileName)
                .getAbsolutePath());
    }

    public void chooseFuel(Fuel fuel) {
        if (fuel != null) {
            selectFuel.click();
            driver.findElement(By.xpath(fuel.getLocator())).click();
        }
    }

    public boolean showInvalidAddressAlert() {
        return isElementDisplayed(errorLocation);
    }

    public boolean isBtnSubmitEnabled() {
        return btnSubmit.isEnabled();
    }
    public boolean isErrorMessagePresent(String expectedText) {
        return isMessageDisplayed(expectedText);
    }

    public void clickBtnSubmitWithJS() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\")" +
                ".removeAttribute('disabled')");
        btnSubmit.click();
    }
}
