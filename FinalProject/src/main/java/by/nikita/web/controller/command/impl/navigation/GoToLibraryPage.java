package by.nikita.web.controller.command.impl.navigation;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.PagePath;
import by.nikita.web.exception.ServiceException;
import by.nikita.web.model.entity.Book;
import by.nikita.web.model.entity.User;
import by.nikita.web.model.service.impl.BookServiceImpl;
import by.nikita.web.model.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
/**
 * The {@code GoToLibraryPage} class represents library page.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public class GoToLibraryPage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
            requestDispatcher = request.getRequestDispatcher(PagePath.LIBRARY_PAGE);
            requestDispatcher.forward(request, response);
    }
}
