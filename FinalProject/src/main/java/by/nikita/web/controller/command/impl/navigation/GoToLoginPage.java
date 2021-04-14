package by.nikita.web.controller.command.impl.navigation;

import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.PagePath;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToLoginPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagePath.LOGIN_PAGE);
        requestDispatcher.forward(request, response);
    }
}
