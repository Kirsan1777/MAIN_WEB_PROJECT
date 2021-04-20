package by.nikita.web.controller.command.impl;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.Message;
import by.nikita.web.controller.command.PagePath;
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
 * The {@code UnblockUserCommand} class represents unblock user.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public class UnblockUserCommand implements Command {
    private static final int UNBLOCKED_USER_PARAMETER = 0;
    private UserServiceImpl userService = UserServiceImpl.getInstance();
    private static final Logger LOGGER = Logger.getLogger(UnblockUserCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        String idUserString = request.getParameter(Attribute.USERS_ID);
        int idUser = Integer.parseInt(idUserString);
        try {
            User user = userService.findUserById(idUser);
            if(user.getRole().equals(UserRole.BLOCKED)){
                userService.updateUserRole(idUser, UNBLOCKED_USER_PARAMETER);
                request.setAttribute(Attribute.MESSAGE, Message.UNBLOCK);
            }else{
                request.setAttribute(Attribute.MESSAGE, Message.ALREADY_UNBLOCK);
            }
            requestDispatcher = request.getRequestDispatcher(PagePath.ALL_USERS_PAGE_COMMAND);
            requestDispatcher.forward(request, response);
        }catch (ServiceException ex){
            requestDispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            requestDispatcher.forward(request, response);
            LOGGER.warn("Can't unblock user", ex);
        }
    }
}
