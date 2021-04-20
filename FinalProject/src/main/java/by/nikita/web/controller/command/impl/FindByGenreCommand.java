package by.nikita.web.controller.command.impl;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.PagePath;
import by.nikita.web.exception.ServiceException;
import by.nikita.web.model.entity.Book;
import by.nikita.web.model.service.impl.BookServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/**
 * The {@code FindByGenreCommand} class represents find book by genre command.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public class FindByGenreCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(FindByGenreCommand.class);
    private BookServiceImpl bookService = BookServiceImpl.getInstance();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagePath.SEARCH_PAGE);
        int genre = Integer.parseInt(request.getParameter(Attribute.GENRE));
        try {
            List<Book> books = bookService.getAllBookByGenre(genre);
            request.setAttribute(Attribute.BOOKS, books);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            requestDispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            requestDispatcher.forward(request, response);
            LOGGER.warn("Can't find by genre", e);
        }

    }
}
