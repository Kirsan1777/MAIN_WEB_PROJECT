package by.nikita.web.controller.command.impl;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.Message;
import by.nikita.web.controller.command.PagePath;
import by.nikita.web.exception.ServiceException;
import by.nikita.web.model.entity.Book;
import by.nikita.web.model.service.impl.BookServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * The {@code ReadBookCommand} class represents read book.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public class ReadBookCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ReadBookCommand.class);
    private BookServiceImpl bookService = BookServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        HttpSession session = request.getSession();
        Book book;
        int idBook = Integer.parseInt(request.getParameter(Attribute.BOOKS_ID));
        int idUser = (Integer) session.getAttribute(Attribute.ID);

        try {
            book = bookService.findBook(idBook);
            if (checkBookAccess(idBook, idUser) || idUser == book.getAuthorId()) {
                request.setAttribute(Attribute.ACCESS, book.getAccess());
                request.setAttribute(Attribute.TEXT, book.getText());
                requestDispatcher = request.getRequestDispatcher(PagePath.TEXT_PAGE_COMMAND);
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute(Attribute.MESSAGE, Message.BOOK_NOT_BUY);
                requestDispatcher = request.getRequestDispatcher(PagePath.MAIN_BOOK_PAGE_COMMAND);
                requestDispatcher.forward(request, response);
            }
        } catch (ServiceException e) {
            requestDispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            requestDispatcher.forward(request, response);
            LOGGER.warn("Can't read book", e);
        }
    }

    private boolean checkBookAccess(int idBook, int idUser) throws ServiceException {
        boolean doesExist = false;
        List<Book> myBook = bookService.getMyBoughtBook(idUser);
        doesExist = myBook.stream().map(Book::getId).anyMatch(b -> b == idBook);
        return doesExist;
    }
}
