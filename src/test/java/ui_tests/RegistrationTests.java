package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PopUpPage;
import pages.RegistrationPage;

import static utils.UserFactory.*;

public class RegistrationTests extends AppManager {
    RegistrationPage registrationPage;

    @BeforeMethod
    public void goToRegistrationPage() {
        new HomePage(getDriver()).clickBtnSignUp();
        registrationPage = new RegistrationPage(getDriver());
    }

    @Test
    public void registrationPositiveTest() {
        User user = positiveUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxIAgree();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("You are logged in success"));
    }

    @Test
    public void registrationPositiveWithJSTest() {
        User user = positiveUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckboxTermsOfUse();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("You are logged in success"));
    }
}