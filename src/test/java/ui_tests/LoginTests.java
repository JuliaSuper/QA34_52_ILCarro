package ui_tests;

import dto.User;
import manedger.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import static utils.PropertiesReader.*;
import static utils.UserFactory.*;

public class LoginTests extends AppManager {
    LoginPage loginPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToLoginPage() {
        new HomePage(getDriver()).clickLoginButton();
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void loginPositiveTest() {
        User user = User.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();

        loginPage.typeLoginForm(user);
        loginPage.clickYalla();
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
        loginPage.clickYalla();
        Assert.assertTrue(loginPage.ispPopUpLoginFailedDisplayed());
    }

    // тест с ДЗ_4
    @Test
    public void loginNegativeTest_InvalidEmail() {
        User user = userWithInvalidEmail();
        loginPage.typeLoginForm(user);
        Assert.assertFalse(loginPage.isYallaButtonEnabled(),
                "Кнопка Y'alla должна быть недоступна при некорректном email");
        Assert.assertTrue(loginPage.getEmailErrorMessage().contains("It'snot look like email")
                        || loginPage.getEmailErrorMessage().contains("Wrong email format"),
                "Текст ошибки валидации email не соответствует" +
                        " ожидаемому");
    }
// тест с ДЗ_4
    @Test
    public void loginNegativeTest_EmptyPassword() {
        User user = userWithEmptyPassword();
        loginPage.typeLoginForm(user);
        Assert.assertFalse(loginPage.isYallaButtonEnabled(),
                "Кнопка Y'alla должна быть недоступна " +
                        "при пустом поле пароля");
    }

    @Test
    public void loginNegativeTest_EmptyAllFieldWOClick() {
        loginPage.clickYalla();
        Assert.assertFalse(loginPage.isBTNYallaEnabled());
    }

    @Test
    public void loginNegativeEmptyAllFieldsWithClickInFieldsTest() {
        User user = User.builder()
                .username("")
                .password("")
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickYalla();
        softAssert.assertFalse(loginPage.isYallaButtonEnabled(), "validate btnYalla");
        System.out.println("test working");
        softAssert.assertTrue(loginPage.isTextErrorPresent("Email is required"),
                "validate message: Email is required");
        softAssert.assertTrue(loginPage.isTextErrorPresent("Password is required"),
                "validate message: Password is required");
        softAssert.assertAll();
    }
}
