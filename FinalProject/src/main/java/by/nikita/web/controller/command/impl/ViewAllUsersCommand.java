package by.nikita.web.controller.command.impl;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.PagePath;
import by.nikita.web.exception.ServiceException;
import by.nikita.web.model.entity.User;
import by.nikita.web.model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/**
 * The {@code ViewAllUsersCommand} class represents view all users.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public class ViewAllUsersCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ViewAllUsersCommand.class);
    private UserServiceImpl userService = UserServiceImpl.getInstance();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        String sort = request.getParameter(Attribute.SORT);
        try {
            List<User> users = userService.getAllSortUsers(sort);
            request.setAttribute(Attribute.USERS, users);
            List<User> usersMain = userService.getAllSortUsers(sort);
            request.setAttribute(Attribute.USERS_MAIN, usersMain);
            requestDispatcher = request.getRequestDispatcher(PagePath.ALL_USERS_PAGE);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            requestDispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            requestDispatcher.forward(request, response);
            LOGGER.warn("Can't view all users", e);
        }
    }
}
