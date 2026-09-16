package ui_tests;

import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.BasePage;
import pages.HomePage;

import java.time.LocalDate;

public class SearchCarTests extends AppManager {
    HomePage homePage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod(alwaysRun = true)
    public void openHomePage() {
        homePage = new HomePage(getDriver());
    }

    @Test
    public void searchCarPositiveTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(8);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.isUrlContactsText("results"));
    }

    @Test
    public void searchCarNegativeTestDataWrong() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().minusDays(1);
        LocalDate endDate = LocalDate.now().plusDays(5);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.isTextInErrorPresent
        ("You can't pick date before today"));
    }

    @Test
    public void searchCarNegativeSameStartAndEndDatesTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.isTextInErrorPresent
                ("You can't book car for less than a day"));
    }


    @Test
    public void searchCarNegativeTestEndDateBeforeStartDate() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(10);
        LocalDate endDate = LocalDate.now()
                .plusDays(7);

        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYalla();
        softAssert.assertTrue(homePage.isTextInErrorPresent
                ("Second date must be after first date"));
        softAssert.assertTrue(homePage.isTextInErrorPresent
                ("You can't book car for less than a day"));
        softAssert.assertAll();


    }
    @Test
    public void searchCarNegativeTestEmptyDates() {
        String city = "Haifa";
        homePage.typeSearchFormWithEmptyDates(city);
        homePage.clickBtnYalla();
        Assert.assertFalse(homePage.isTextInErrorPresent
                ("Dates are required"));
    }

    @Test(groups = "smoke")
    public void searchCarWithCalendarPositiveTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(8);
        homePage.typeSearchFormWithCalendar(city, startDate, endDate);
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.isUrlContactsText("results"));
    }

    @Test
    public void searchCarNegativeWithCalendarPastDateTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().minusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(8);
        homePage.typeSearchFormWithCalendar(city, startDate, endDate);
        Assert.assertFalse(homePage.isYallaButtonEnabled(),
                "Button Y'alla! should be disabled for past dates");
    }

    @Test
    public void searchCarNegativeWithCalendarEndDateBeforeStartDateTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(10);
        LocalDate endDate = LocalDate.now().plusDays(2);
        homePage.typeSearchFormWithCalendar(city, startDate, endDate);
        Assert.assertFalse(homePage.isYallaButtonEnabled(),
                "Button Y'alla! should be disabled for past dates");
    }

    @Test
    public void searchCarNegativeWithCalendarSameDatesTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(3);
        LocalDate endDate = LocalDate.now().plusDays(3);
        homePage.typeSearchFormWithCalendar(city, startDate, endDate);
        homePage.clickBtnYalla();
        softAssert.assertTrue(homePage.isUrlContactsText("results"),
                "URL does not contain 'results'");
        softAssert.assertTrue(homePage.isMessageNoCarPresent(),
                "Message 'No available cars in' is missing on results page");
        softAssert.assertAll();
    }

}
