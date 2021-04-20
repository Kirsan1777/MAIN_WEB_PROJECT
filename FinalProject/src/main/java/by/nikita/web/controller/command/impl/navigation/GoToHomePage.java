package by.nikita.web.controller.command.impl.navigation;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.PagePath;
import by.nikita.web.controller.command.impl.AuthorizationUser;
import by.nikita.web.exception.ServiceException;
import by.nikita.web.model.entity.User;
import by.nikita.web.model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
/**
 * The {@code GoToHomePage} class represents profile page.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public class GoToHomePage implements Command {
    private static final Logger LOGGER = Logger.getLogger(GoToHomePage.class);
    private UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        HttpSession session = request.getSession();
        User user;
        try {
            user = userService.findUserInformation((Integer) session.getAttribute(Attribute.ID));
            request.setAttribute(Attribute.USERS, user);
            request.setAttribute(Attribute.BALANCE, userService.getBalance(user.getId()));
            requestDispatcher = request.getRequestDispatcher(PagePath.HOME_PAGE);
            requestDispatcher.forward(request, response);
        } catch (ServiceException ex) {
            LOGGER.warn("Can't go to the home page", ex);
            response.sendRedirect(PagePath.ERROR_PAGE);
        }

    }
}
