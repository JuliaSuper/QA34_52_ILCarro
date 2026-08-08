package ui_tests;

import dto.User;
import manedger.AppManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.util.Random;

public class LoginTests extends AppManager {

    @BeforeMethod
    public void goToRegistrationLoginPage(){
        HomePage homePage = new HomePage(getDriver());
        homePage.clickLoginButton();
    }

    @Test
    public void loginPositiveTest(){
        User user = User.builder().
                email("juliyur2023@gmail.com")
                .password("123DFjk$")
                .build();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickYalla();
    }
}
