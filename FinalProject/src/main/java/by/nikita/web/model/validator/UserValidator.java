package by.nikita.web.model.validator;


import by.nikita.web.exception.ServiceException;
import by.nikita.web.model.entity.UserRole;
import by.nikita.web.model.service.impl.UserServiceImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code UserValidator} class represents User Validator.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public class UserValidator {

    private static final int MIN_LOGIN_PASSWORD_LENGTH = 4;
    private static final int MAX_LOGIN_PASSWORD_LENGTH = 29;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9]+\\.[A-Za-z0-9]+$";

    private UserServiceImpl userService = UserServiceImpl.getInstance();

    /**
     * checks for user existence
     *
     * @param login the user login
     * @param email   the user email
     * @return if user exists
     */
    public boolean validateUser(String login, String email) {
        return login != null && checkLogin(login) &&
                email != null && validateEmail(email);
    }

    /**
     * checks user role
     *
     * @param id the id user
     * @return if user role was admin
     */
    public boolean checkRoleUser(int id) throws ServiceException {
        boolean resultChecking = false;
        resultChecking = userService.findUserById(id).getRole() == UserRole.ADMIN;
        return resultChecking;
    }

    /**
     * checks the length of the login
     *
     * @param login the user login
     * @return if login was true
     */
    public boolean checkLogin(String login) {
        boolean resultChecking = false;
        return login.length() >= MIN_LOGIN_PASSWORD_LENGTH && login.length() <= MAX_LOGIN_PASSWORD_LENGTH;
    }

    /**
     * checks the correctness of the email
     *
     * @param email the email
     * @return if email was correct
     */
    public boolean validateEmail(String email) {
        boolean wasSuccessfulValidation = false;
        if (email != null) {
            Pattern pattern = Pattern.compile(EMAIL_REGEX);
            Matcher matcher = pattern.matcher(email);
            wasSuccessfulValidation = matcher.find();
        }
        return wasSuccessfulValidation;
    }

}
