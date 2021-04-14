package by.nikita.web.model.validator;


import by.nikita.web.exception.ServiceException;
import by.nikita.web.model.entity.UserRole;
import by.nikita.web.model.service.impl.UserServiceImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private static final int MIN_LOGIN_PASSWORD_LENGTH = 4;
    private static final int MAX_LOGIN_PASSWORD_LENGTH = 29;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9]+\\.[A-Za-z0-9]+$";
    private UserServiceImpl userService = UserServiceImpl.getInstance();

    public boolean validateUser(String login, String email){
        return login!=null && checkLogin(login) &&
                email != null && validateEmail(email);
    }


    public boolean checkRoleUser(int id){

        boolean resultChecking = false;
        try {
            resultChecking = userService.findUserById(id).getRole() == UserRole.ADMIN;
        } catch (ServiceException e) {
            //
        }
        return resultChecking;
    }

    public boolean checkLogin(String login){
        //логин не должен включать подчеркивания, пробелы, @,
        boolean resultChecking = false;
        if(login.length() >= MIN_LOGIN_PASSWORD_LENGTH && login.length() <= MAX_LOGIN_PASSWORD_LENGTH){

        }
        return true;
    }


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
