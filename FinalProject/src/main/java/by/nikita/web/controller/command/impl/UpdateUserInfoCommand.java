package by.nikita.web.controller.command.impl;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.Message;
import by.nikita.web.controller.command.PagePath;
import by.nikita.web.exception.ServiceException;
import by.nikita.web.exception.TransactionException;
import by.nikita.web.model.entity.User;
import by.nikita.web.model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class UpdateUserInfoCommand implements Command {

    private UserServiceImpl userService = UserServiceImpl.getInstance();
    private static final Logger LOGGER = Logger.getLogger(UpdateUserInfoCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        HttpSession session = request.getSession();
        User user = new User();
        user.setId((Integer)session.getAttribute(Attribute.ID));
        user.setPassword(request.getParameter(Attribute.PASSWORD));
        user.setEmail(request.getParameter(Attribute.EMAIL));
        user.setName(request.getParameter(Attribute.NAME));
        try {
            userService.updateUserInfo(user);
            request.setAttribute(Attribute.MESSAGE, Message.CORRECT_UPDATE);
            requestDispatcher = request.getRequestDispatcher(PagePath.HOME_PAGE_COMMAND);
            requestDispatcher.forward(request, response);
        }
        catch (ServletException | ServiceException | IOException e) {
            requestDispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            requestDispatcher.forward(request, response);
            LOGGER.warn("Can't update user info", e);
        }
    }
}
