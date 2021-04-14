package by.nikita.web.controller.command.impl.navigation;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.PagePath;
import by.nikita.web.controller.command.impl.AuthorizationUser;
import by.nikita.web.exception.ServiceException;
import by.nikita.web.model.entity.Book;
import by.nikita.web.model.entity.User;
import by.nikita.web.model.service.impl.BookServiceImpl;
import by.nikita.web.model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToAuthorPage implements Command {
    private static final Logger LOGGER = Logger.getLogger(GoToAuthorPage.class);
    private BookServiceImpl bookService = BookServiceImpl.getInstance();
    private UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        int idAuthor = Integer.parseInt(request.getParameter(Attribute.AUTHOR_ID));
        try {
            User user = userService.findUserInformation(idAuthor);
            List<Book> books = bookService.getAllBookByIdAuthor(idAuthor);
            request.setAttribute(Attribute.BOOKS, books);
            request.setAttribute(Attribute.USERS, user);
            requestDispatcher = request.getRequestDispatcher(PagePath.AUTHOR_PAGE);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            LOGGER.warn("Can't block user", e);
            response.sendRedirect(PagePath.ERROR_PAGE);
        }
    }
}
