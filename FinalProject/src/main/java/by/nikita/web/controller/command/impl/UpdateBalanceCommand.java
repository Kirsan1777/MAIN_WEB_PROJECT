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
/**
 * The {@code UpdateBalanceCommand} class represents update balance from user.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public class UpdateBalanceCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(UpdateBalanceCommand.class);
    private UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher;
        HttpSession session = request.getSession();
        String moneyString = request.getParameter(Attribute.BALANCE);
        double money = Double.parseDouble(moneyString);
        int idUser = (Integer)session.getAttribute(Attribute.ID);
        try {
            userService.updateBalance(idUser, money);
            //requestDispatcher = request.getRequestDispatcher(PagePath.HOME_PAGE_COMMAND);
            response.sendRedirect(PagePath.HOME_PAGE_COMMAND);
            //requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            requestDispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            requestDispatcher.forward(request, response);
            LOGGER.warn("Can't update balance", e);
        }
    }
}
