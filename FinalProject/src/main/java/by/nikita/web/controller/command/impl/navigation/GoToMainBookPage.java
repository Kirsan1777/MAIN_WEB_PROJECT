package by.nikita.web.controller.command.impl.navigation;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.PagePath;
import by.nikita.web.controller.command.impl.AuthorizationUser;
import by.nikita.web.exception.ServiceException;
import by.nikita.web.model.entity.Book;
import by.nikita.web.model.entity.Comment;
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
/**
 * The {@code GoToMainBookPage} class represents book info page.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public class GoToMainBookPage implements Command {
    private static final Logger LOGGER = Logger.getLogger(GoToMainBookPage.class);
    private BookServiceImpl bookService = BookServiceImpl.getInstance();
    private UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        String idBookStr = request.getParameter(Attribute.BOOKS_ID);
        int idBook = Integer.parseInt(idBookStr);
        try {
            Book book = bookService.findBook(idBook);
            User user = userService.findUserInformation(book.getAuthorId());
            List<Comment> comments = bookService.findAllComments(idBook);
            List<User> users = userService.getAllUsersInformation();
            request.setAttribute(Attribute.USERS_MAIN, users);
            request.setAttribute(Attribute.COMMENT, comments);
            request.setAttribute(Attribute.BOOKS, book);
            request.setAttribute(Attribute.USERS, user);
            requestDispatcher = request.getRequestDispatcher(PagePath.MAIN_BOOK_PAGE);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            requestDispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            requestDispatcher.forward(request, response);
            LOGGER.warn("Can't go to the main book page", e);
        }
    }
}
