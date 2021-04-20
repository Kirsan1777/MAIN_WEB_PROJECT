package by.nikita.web.controller.command.impl.navigation;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.PagePath;
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
/**
 * The {@code GoToMainIndexPage} class represents main page.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public class GoToMainIndexPage implements Command {
    private static final Logger LOGGER = Logger.getLogger(GoToMainBookPage.class);
    private UserServiceImpl userService = UserServiceImpl.getInstance();
    private BookServiceImpl bookService = BookServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        try{
            List<Book> booksNew = bookService.getAllBookSort(Attribute.SORT_BY_TIME);
            booksNew.removeAll(booksNew.subList(8, booksNew.size()));
            request.setAttribute(Attribute.BOOKS_NEW, booksNew);
            List<Book> booksName = bookService.getAllBookSort(Attribute.SORT_BY_NAME);
            request.setAttribute(Attribute.BOOKS_NAME, booksName);
            List<User> users = userService.getAllUsers();
            request.setAttribute(Attribute.USERS, users);
            requestDispatcher = request.getRequestDispatcher(PagePath.MAIN_INDEX_PAGE);
            requestDispatcher.forward(request, response);
        }catch (ServiceException ex){
            requestDispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            requestDispatcher.forward(request, response);
            LOGGER.warn("Can't go to the main page", ex);
        }
    }
}
