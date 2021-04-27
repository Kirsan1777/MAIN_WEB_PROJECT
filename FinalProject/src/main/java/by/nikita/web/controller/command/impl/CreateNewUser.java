package by.nikita.web.controller.command.impl;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.Message;
import by.nikita.web.controller.command.PagePath;
import by.nikita.web.exception.ServiceException;
import by.nikita.web.exception.TransactionException;
import by.nikita.web.model.dao.Transaction;
import by.nikita.web.model.entity.User;
import by.nikita.web.model.service.impl.UserServiceImpl;
import by.nikita.web.model.validator.UserValidator;
import org.apache.log4j.Logger;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.sql.Timestamp;
/**
 * The {@code CreateNewUser} class represents create new user command.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public class CreateNewUser implements Command {
    private static final Logger LOGGER = Logger.getLogger(CreateNewUser.class);
    private Transaction transaction = Transaction.getInstance();
    private UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher;
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        UserValidator validator = new UserValidator();
        User user = new User();
        user.setLogin(request.getParameter(Attribute.LOGIN));
        user.setPassword(request.getParameter(Attribute.PASSWORD));
        user.setEmail(request.getParameter(Attribute.EMAIL));
        user.setName(request.getParameter(Attribute.NAME));
        user.setDateRegistration(stamp.toString());
        try {
            if(validator.validateUser(user.getLogin(), user.getEmail())) {
                if(userService.findUserByLogin(user.getLogin()) == null){
                    transaction.createAccountUserAndBank(user);
                    requestDispatcher = request.getRequestDispatcher(PagePath.MAIN_INDEX_PAGE_COMMAND);
                    requestDispatcher.forward(request, response);
                }else{
                    request.setAttribute(Attribute.MESSAGE, Message.THIS_LOGIN_IS_ALREADY_USED);
                    requestDispatcher = request.getRequestDispatcher(PagePath.REGISTRATION_PAGE);
                    requestDispatcher.forward(request, response);
                }
            }else{
                request.setAttribute(Attribute.MESSAGE, Message.INCORRECT_INPUT);
                requestDispatcher = request.getRequestDispatcher(PagePath.REGISTRATION_PAGE);
                requestDispatcher.forward(request, response);
            }
        }
        catch (ServletException | ServiceException | TransactionException e) {
            requestDispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            requestDispatcher.forward(request, response);
            LOGGER.warn("Can't create new user", e);
        }
    }
}
