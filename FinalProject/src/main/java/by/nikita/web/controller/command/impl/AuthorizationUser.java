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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;


public class AuthorizationUser implements Command {
    private static final Logger LOGGER = Logger.getLogger(AuthorizationUser.class);
    private UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        RequestDispatcher requestDispatcher;
        String login = request.getParameter(Attribute.LOGIN);
        String password = request.getParameter(Attribute.PASSWORD);
        try{
            User user = userService.authorization(login, password);
            if(user == null){
                requestDispatcher = request.getRequestDispatcher(PagePath.LOGIN_PAGE);
                request.setAttribute(Attribute.MESSAGE, Message.INCORRECT_INPUT);
                requestDispatcher.forward(request, response);
                return;
            }
            if(user.getRole() == UserRole.BLOCKED){
                requestDispatcher = request.getRequestDispatcher(PagePath.BLOCKED_PAGE);
                requestDispatcher.forward(request, response);
                return;
            }
            HttpSession session = request.getSession();
            session.setAttribute(Attribute.ID, user.getId());
            session.setAttribute(Attribute.USER_ROLE, user.getRole());
            response.sendRedirect(PagePath.MAIN_INDEX_PAGE_COMMAND);
        }catch (ServiceException ex){
            response.sendRedirect(PagePath.ERROR_PAGE);
            LOGGER.warn(ex);
        } catch (ServletException e) {
            response.sendRedirect(PagePath.ERROR_PAGE);
            LOGGER.warn(e);
        }
    }
}
