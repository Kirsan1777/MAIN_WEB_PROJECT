package by.nikita.web.controller.command.impl;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.Message;
import by.nikita.web.controller.command.PagePath;
import by.nikita.web.exception.ControllerException;
import by.nikita.web.exception.ServiceException;
import by.nikita.web.model.entity.User;
import by.nikita.web.model.entity.UserRole;
import by.nikita.web.model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * The {@code BlockUserCommand} class represents block user command.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public class BlockUserCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(BlockUserCommand.class);
    private UserServiceImpl userService = UserServiceImpl.getInstance();
    private static final int BLOCKED_USER_PARAMETER = 4;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        String idUserString = request.getParameter(Attribute.USERS_ID);
        int idUser = Integer.parseInt(idUserString);
        try {
            User user = userService.findUserById(idUser);
            if(user.getRole().equals(UserRole.ADMIN)){
                request.setAttribute(Attribute.MESSAGE, Message.ERROR_ADMIN);
                requestDispatcher = request.getRequestDispatcher(PagePath.ALL_USERS_PAGE_COMMAND);
                requestDispatcher.forward(request, response);
            }else if(user.getRole().equals(UserRole.BLOCKED)){
                request.setAttribute(Attribute.MESSAGE, Message.ERROR_BLOCKED);
                requestDispatcher = request.getRequestDispatcher(PagePath.ALL_USERS_PAGE_COMMAND);
                requestDispatcher.forward(request, response);
            }else{
                userService.updateUserRole(idUser, BLOCKED_USER_PARAMETER);
                request.setAttribute(Attribute.MESSAGE, Message.UPDATED);
                requestDispatcher = request.getRequestDispatcher(PagePath.ALL_USERS_PAGE_COMMAND);
                requestDispatcher.forward(request, response);
            }
        }catch (ServiceException ex){
            LOGGER.warn("Can't block user", ex);
            response.sendRedirect(PagePath.ERROR_PAGE);
        }

    }
}
