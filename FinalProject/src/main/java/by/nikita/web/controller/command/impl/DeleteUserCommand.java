package by.nikita.web.controller.command.impl;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.PagePath;
import by.nikita.web.exception.ServiceException;
import by.nikita.web.model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUserCommand implements Command {
    private UserServiceImpl userService = UserServiceImpl.getInstance();
    private static final Logger LOGGER = Logger.getLogger(DeleteUserCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        String idUserString = request.getParameter(Attribute.USERS_ID);
        int idUser = Integer.parseInt(idUserString);

        try {
            userService.deleteUser(idUser);
            requestDispatcher = request.getRequestDispatcher(PagePath.MAIN_INDEX_PAGE_COMMAND);
            requestDispatcher.forward(request, response);
        }catch (ServiceException ex){
            requestDispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            requestDispatcher.forward(request, response);
            LOGGER.warn("Can't delete user", ex);
        }
    }
}
