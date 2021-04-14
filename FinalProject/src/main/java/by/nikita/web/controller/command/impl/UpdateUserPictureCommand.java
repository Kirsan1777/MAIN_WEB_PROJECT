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
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateUserPictureCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(UpdateUserPictureCommand.class);
    UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        RequestDispatcher requestDispatcher;
        String namePicture =(String) request.getAttribute(Attribute.PICTURE);
        int idUser = (Integer)session.getAttribute(Attribute.ID);
        try {
            User user = userService.findUserInformation((Integer)session.getAttribute(Attribute.ID));
            request.setAttribute(Attribute.USERS, user);
            userService.updateUserPicture(idUser, namePicture);
        } catch (ServiceException e) {
            requestDispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            requestDispatcher.forward(request, response);
            LOGGER.warn("Can't update user picture", e);
        } finally {
            requestDispatcher = request.getRequestDispatcher(PagePath.HOME_PAGE_COMMAND);
            requestDispatcher.forward(request,response);
        }
    }
}
