package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import static utils.PropertiesReader.*;

public class LoginTests extends AppManager {
    LoginPage loginPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToLoginPage() {
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void loginPositiveTest() {
        User user = User.builder()
                .username(getProperty("base.properties",
                        "email"))
                .password(getProperty("base.properties",
                        "password"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.ispPopUpSuccessLoginDisplayed());
    }

    @Test
    public void loginNegativeWrongEmailTest() {
        User user = User.builder()
                .username(getProperty("base.properties",
                        "emailTest"))
                .password(getProperty("base.properties",
                        "password"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.ispPopUpLoginFailedDisplayed());
    }

    @Test
    public void loginNegativeWrongPasswordTest() {
        User user = User.builder()
                .username(getProperty("base.properties",
                        "email"))
                .password(getProperty("base.properties",
                        "passwordRegistrationUser"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.ispPopUpLoginFailedDisplayed());
    }

    @Test
    public void loginNegativeEmptyAllFieldsWOClickInFieldsTest() {
        loginPage.clickBtnYalla();
        Assert.assertFalse(loginPage.isBtnYallaEnabled());
    }

    @Test
    public void loginNegativeEmptyAllFieldsWithClickInFieldsTest() {
        User user = User.builder()
                .username("")
                .password("")
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        softAssert.assertFalse(loginPage.isBtnYallaEnabled(),
                "validate isBtnYallaEnabled()");
        System.out.println("test working");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Email is required"),
                "validate message: Email is required");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Password is required"),
                "validate message: Password is required");
        softAssert.assertAll();
    }

    @Test
    public void loginNegativeEmptyFieldPasswordTest() {
        User user = User.builder()
                .username(getProperty("base.properties",
                        "email"))
                .password("")
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        softAssert.assertFalse(loginPage.isBtnYallaEnabled(),
                "validate isBtnYallaEnabled()");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Password is required"),
                "validate message: Password is required");
        softAssert.assertAll();
    }

    @Test
    public void loginNegativeEmptyFieldEmailTest() {
        User user = User.builder()
                .username("")
                .password(getProperty("base.properties",
                        "password"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        softAssert.assertFalse(loginPage.isBtnYallaEnabled(),
                "validate isBtnYallaEnabled()");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Email is required"),
                "validate message: Email is required");
        softAssert.assertAll();
    }
}