package by.nikita.web.controller.command.impl;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
/**
 * The {@code SortBookCommand} class represents sort all books by criterion.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public class SortBookCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(SortBookCommand.class);
    private UserServiceImpl userService = UserServiceImpl.getInstance();
    private BookServiceImpl bookService = BookServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        HttpSession session = request.getSession();
        int idUser = 0;
        if(session.getAttribute(Attribute.ID) != null) {
            idUser = (Integer) session.getAttribute(Attribute.ID);
        }
        String sort = request.getParameter(Attribute.SORT);
        try{
            if(session.getAttribute(Attribute.ID) != null) {
                List<Book> newBook = bookService.getMyBoughtBook(idUser);
                request.setAttribute(Attribute.BOOKS_NEW, newBook);
                List<Book> authorBook = bookService.getAllBookByIdAuthor(idUser);
                request.setAttribute(Attribute.MY_BOOKS, authorBook);
            }
            List<Book> books = bookService.getAllBookSort(sort);
            request.setAttribute(Attribute.BOOKS, books);
            List<User> users = userService.getAllUsers();
            request.setAttribute(Attribute.USERS, users);
            requestDispatcher = request.getRequestDispatcher(PagePath.LIBRARY_PAGE);
            requestDispatcher.forward(request, response);
        }catch (ServiceException ex){
            requestDispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            requestDispatcher.forward(request, response);
            LOGGER.warn("Can't sort book", ex);
        }
    }
}
