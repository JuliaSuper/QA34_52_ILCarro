package ui_tests;

import data_providers.UserDataProviders;
import dto.User;
import manager.AppManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PopUpPage;
import pages.RegistrationPage;
import utils.TestINGListener;

import static utils.UserFactory.*;
@Listeners(TestINGListener.class)

public class RegistrationTests extends AppManager {
    RegistrationPage registrationPage;


    @BeforeMethod
    public void goToRegistrationPage() {
        logger.info("Start registration test");
        new HomePage(getDriver()).clickBtnSignUp();
        registrationPage = new RegistrationPage(getDriver());
    }

//    @Test
//    public void registrationPositiveTest() {
//        User user = positiveUser();
//        registrationPage.typeRegistrationForm(user);
//        registrationPage.clickCheckBoxIAgree();
//        registrationPage.clickBtnYalla();
//        Assert.assertTrue(new PopUpPage(getDriver())
//                .isTextInPopUpMessagePresent("You are logged in success"));
//    }

    @Test
    public void registrationPositiveWithJSTest() {
        User user = positiveUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckboxTermsOfUse();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("You are logged in success"));
    }

    @Test
    public void registrationPositiveWithActionsTest() {
        User user = positiveUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("You are logged in success"));
    }

    @Test(dataProvider = "dataProviderForRegistrationWrongPasswordOrEmail",
            dataProviderClass = UserDataProviders.class)
    public void registrationNegativeWrongPasswordTest(User user) {
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(registrationPage.isTextInErrorPresent("Password must contain " +
                "1 uppercase letter, " +
                "1 lowercase letter, 1 number and one special symbol of [@$#^&*!]"));
    }
}