package ui_tests;

import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

import java.time.LocalDate;

public class SearchCarTests extends AppManager {
    HomePage homePage;
    Assert anAssert;

    @BeforeMethod
    public void openHomePage() {
        homePage = new HomePage(getDriver());
    }

    @Test
    public void searchCarPositiveTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(8);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYallaSubmit();
        Assert.assertTrue(homePage.isErrorMessagePresentCity
                (" City is required "));
    }

    @Test
    public void searchCarNegativeTestDataWrong() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().minusDays(1);
        LocalDate endDate = LocalDate.now().plusDays(5);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYallaSubmit();

    }
    @Test
    public void searchCarNegativeTestEndDateBeforeStartDate() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(5);
        LocalDate endDate = LocalDate.now().plusDays(2);

        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYallaSubmit();
        
    }
    @Test
    public void searchCarNegativeTestEmptyDates() {
        String city = "Haifa";
        homePage.typeSearchFormWithEmptyDates(city);
        homePage.clickBtnYallaSubmit();

        Assert.assertFalse(homePage.isErrorMessagePresentCity("Dates are required"));
    }
}
