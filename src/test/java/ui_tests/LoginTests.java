package ui_tests;

import dto.User;
import manedger.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.UserFactory;

import static utils.UserFactory.*;

public class LoginTests extends AppManager {

    LoginPage loginPage;

    @BeforeMethod
    public void goToLoginPage() {
        new HomePage(getDriver()).clickLoginButton();
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void loginPositiveTest() {
        User user = User.builder()
                .username("juliyur2023@gmail.com")
                .password("123DFjk$")
                .build();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickYalla();
    }

    @Test
    public void loginNegativeTest_InvalidEmail() {
        User user = userWithInvalidEmail();
        loginPage.typeLoginForm(user);

        Assert.assertFalse(loginPage.isYallaButtonEnabled(),
                "Кнопка Y'alla должна быть недоступна при некорректном email");

        Assert.assertTrue(loginPage.getEmailErrorMessage().contains("It'snot look like email")
                        || loginPage.getEmailErrorMessage().contains("Wrong email format"),
                "Текст ошибки валидации email не соответствует ожидаемому");
    }

    @Test
    public void loginNegativeTest_EmptyPassword() {
        User user = userWithEmptyPassword();
        loginPage.typeLoginForm(user);

        Assert.assertFalse(loginPage.isYallaButtonEnabled(),
                "Кнопка Y'alla должна быть недоступна при пустом поле пароля");
    }

//    @Test
//    public void loginNegativeTest_InvalidPasswordFormat() {
//        User user = UserFactory.userWithInvalidPassword();
//
//        loginPage.typeLoginForm(user);
//
//        Assert.assertFalse(loginPage.isYallaButtonEnabled(),
//                "Кнопка Y'alla должна быть недоступна при некорректном" +
//                        " формате пароля");
//
//        String actualErrorMessage = loginPage.getPasswordErrorMessage();
//
//        Assert.assertTrue(actualErrorMessage.contains("Password must contain"),
//                "Ожидалось сообщение о требованиях к паролю, " +
//                        "но получено: " + actualErrorMessage);
//    } этот тест не срабатывает, так как требования для пароля у ILCarro
//    не установлены
}
