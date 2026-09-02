package ui_tests;

import dto.Car;
import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LoginPage;
import pages.PopUpPage;
import utils.enums.HeaderMenu;

import static utils.PropertiesReader.getProperty;
import static utils.CarFactory.*;

public class AddNewCarTest extends AppManager {
    LoginPage loginPage;
    LetTheCarWorkPage letTheCarWorkPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToTheCarWorkPage() {
//        new HomePage(getDriver()).clickBtnLogin();
//        loginPage = new LoginPage(getDriver());
        loginPage = new HomePage(getDriver()).clickHeaderButtons(HeaderMenu.LOGIN);
        User user = User.builder()
                .username(getProperty("base.properties",
                        "email"))
                .password(getProperty("base.properties",
                        "password"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        new PopUpPage(getDriver()).clickBtnOk();
        letTheCarWorkPage = new HomePage(getDriver())
                .clickHeaderButtons(HeaderMenu.LET_THE_CAR_WORK);
    }

    @Test
    public void addNewCarPositiveTest() {
        Car car = posittiveCar();
        System.out.println(car);
        letTheCarWorkPage.typeAddNewCarForm(car);
        letTheCarWorkPage.downloadImage("Photo1.jpg");
        letTheCarWorkPage.clickBtnSubmitWithJS();
        Assert.assertTrue((new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("{\"city\":\"must not be blank\"}")));

//        Assert.assertTrue(letTheCarWorkPage.showInvalidAddressAlert(),
//                "Error message 'Wrong address' is not displayed!");

    }
    @Test
    public void addNewCarNegativeAllFieldsEmptySubmitButtonRemainsEnabledTest() {
        letTheCarWorkPage.clickBtnSubmitWithJS();
        Assert.assertTrue(letTheCarWorkPage.isBtnSubmitEnabled());
    }
    @Test
    public void addNewCarNegativeAllFieldsEmptyWithClickInFieldsTest() {
        Car car = Car.builder()
                .serialNumber("")
                .manufacture("")
                .model("")
                .year("")
                .fuel(null)
                .seats(null)
                .carClass("")
                .pricePerDay(null)
                .about("")
                .city("")
                .build();
        letTheCarWorkPage.typeAddNewCarForm(car);
        letTheCarWorkPage.clickBtnSubmitWithJS();
        softAssert.assertTrue(letTheCarWorkPage.isErrorMessagePresent("Wrong address"),
                "Error message 'Wrong address' is not displayed!");
        softAssert.assertTrue(letTheCarWorkPage.isErrorMessagePresent(" Make is required "),
                "validate message: Make is required");
        softAssert.assertTrue(letTheCarWorkPage.isErrorMessagePresent(" Car class is required "),
                "validate message:  Car class is required ");
        softAssert.assertAll();
    }

    @Test
    public void addNewCarNegativeOneFieldBlankTest() {
        Car car = posittiveCar();
        car.setModel("");

        letTheCarWorkPage.typeAddNewCarForm(car);
        letTheCarWorkPage.downloadImage("Photo2.jpg");
        letTheCarWorkPage.clickBtnSubmitWithJS();

        softAssert.assertTrue(
                letTheCarWorkPage.isMessageDisplayed("Model is required"),
                "validate message:  Model is required "
        );
        softAssert.assertAll();
    }

    @Test
    public void addNewCarNegativeFutureYearTest() {
        Car car = negativeCarFutureYear();

        letTheCarWorkPage.typeAddNewCarForm(car);
        letTheCarWorkPage.clickBtnSubmitWithJS();

        softAssert.assertTrue(
                letTheCarWorkPage.isMessageDisplayed("Wrong year"),
                "Validation error for future year is not displayed!"
        );
        softAssert.assertAll();
    }

    @Test
    public void addNewCarNegativeYearNegativeTest() {
        Car car = negativeCarNegativeYear();

        letTheCarWorkPage.typeAddNewCarForm(car);
        letTheCarWorkPage.clickBtnSubmitWithJS();

        softAssert.assertTrue(
                letTheCarWorkPage.isMessageDisplayed("Wrong year"),
                "Validation error for future year is not displayed!"
        );
        softAssert.assertAll();
    }

    }
// Homework Negative Tests
// 1. only click btn Submit
// 2. click all fields and btnSubmit
// 3. leave one field blank and other fields type with valid data
// 4. wrong year