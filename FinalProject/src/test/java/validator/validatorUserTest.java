package validator;

import by.nikita.web.model.validator.UserValidator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class validatorUserTest {
    public static UserValidator validator;

    @BeforeClass
    public static void setup() {
        validator = new UserValidator();
    }

    @DataProvider(name = "validEmail")
    private Object[][] createValidEmail() {
        return new Object[][]{
                {"qwerty1234@gmail.com"}, {"1234@gmail.by"}, {"qwerty@gmail.ru"}
        };
    }

    @Test(dataProvider = "validEmail")
    public void validateEmailValidTest(String login) {
        boolean isValidLogin = validator.validateEmail(login);
        assertTrue(isValidLogin);
    }

    @DataProvider(name = "invalidEmail")
    private Object[][] createInvalidEmail() {
        return new Object[][]{
                {"qwerty1234gmail.com"}, {"1234@gmailby"}, {null}
        };
    }

    @Test(dataProvider = "invalidEmail")
    public void validateEmailInvalidTest(String login) {
        boolean isValidLogin = validator.validateEmail(login);
        assertFalse(isValidLogin);
    }

    @DataProvider(name = "validLogin")
    private Object[][] createValidLogin() {
        return new Object[][]{
                {"qwerwerqwe"}, {"qweqqwqeqweqwe123123123"}, {"gdhguhweirnsdpifsd"}
        };
    }

    @Test(dataProvider = "validLogin")
    public void validateLoginValidTest(String login) {
        boolean isValidLogin = validator.checkLogin(login);
        assertTrue(isValidLogin);
    }

    @DataProvider(name = "invalidLogin")
    private Object[][] createInvalidLines() {
        return new Object[][]{
                {"qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"},
                {"q"}, {null}
        };
    }

    @Test(dataProvider = "invalidLogin")
    public void validateLoginInvalidTest(String login) {
        boolean isValidLogin = validator.validateEmail(login);
        assertFalse(isValidLogin);
    }

    @Test
    public void validateUserValidTest() {
        String login = "qwerty";
        String email = "qwerty@gmail.com";
        boolean isValidUser = validator.validateUser(login, email);
        assertTrue(isValidUser);
    }

    @Test
    public void validateUserInvalidTest() {
        String login = "q";
        String email = "qwerty@gmail.com";
        boolean isValidUser = validator.validateUser(login, email);
        assertFalse(isValidUser);
    }

    @Test
    public void validateUserInvalidTest2() {
        String login = "qwerty";
        String email = "qwerty@gmailcom";
        boolean isValidUser = validator.validateUser(login, email);
        assertFalse(isValidUser);
    }

    @Test
    public void validateUserInvalidTest3() {
        String login = "q";
        String email = "qwerty";
        boolean isValidUser = validator.validateUser(login, email);
        assertFalse(isValidUser);
    }

}
