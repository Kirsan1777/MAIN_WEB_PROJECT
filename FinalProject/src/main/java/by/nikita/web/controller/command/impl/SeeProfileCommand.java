package by.nikita.web.controller.command.impl;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.PagePath;
import by.nikita.web.exception.ServiceException;
import by.nikita.web.model.entity.User;
import by.nikita.web.model.service.impl.UserServiceImpl;
import by.nikita.web.model.validator.UserValidator;
import org.apache.log4j.Logger;
import org.w3c.dom.Attr;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SeeProfileCommand implements Command {
    UserServiceImpl userService = UserServiceImpl.getInstance();
    private static final Logger LOGGER = Logger.getLogger(SeeProfileCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        User user;
        int idUser = Integer.parseInt(request.getParameter(Attribute.ID));
        try {
            user = userService.findUserInformation(idUser);
            request.setAttribute(Attribute.USERS, user);
            requestDispatcher = request.getRequestDispatcher(PagePath.HOME_PAGE_COMMAND);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            requestDispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            requestDispatcher.forward(request, response);
            LOGGER.warn("Can't see profile", e);
        }
    }
}
