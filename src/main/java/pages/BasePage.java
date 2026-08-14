package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public abstract class BasePage {
    static WebDriver driver;

    public void setDriver(WebDriver wd) {
        driver = wd;
    }

    @FindBy(xpath = "//div[@class='error']")
    List<WebElement> listErrors;

    public boolean isTextErrorPresent(String text){
        if (listErrors ==null || listErrors.isEmpty())
            return false;
        for (WebElement e : listErrors){
            if (e.getText().contains(text))
                return true;
        }
        return false;
    }

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }
}
