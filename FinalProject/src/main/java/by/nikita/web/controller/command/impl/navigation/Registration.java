package by.nikita.web.controller.command.impl.navigation;

import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.PagePath;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * The {@code Registration} class represents registration page.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public class Registration implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagePath.REGISTRATION_PAGE);
        requestDispatcher.forward(request, response);
    }
}
