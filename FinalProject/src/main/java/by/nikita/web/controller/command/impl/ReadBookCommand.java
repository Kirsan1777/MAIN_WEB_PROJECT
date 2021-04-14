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

public class ReadBookCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ReadBookCommand.class);
    BookServiceImpl bookService = BookServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        Book book;
        int idBook = Integer.parseInt(request.getParameter(Attribute.BOOKS_ID));
        try {
            book = bookService.findBook(idBook);
            request.setAttribute(Attribute.ACCESS, book.getAccess());
            request.setAttribute(Attribute.TEXT, book.getText());
            requestDispatcher = request.getRequestDispatcher(PagePath.TEXT_PAGE_COMMAND);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            requestDispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            requestDispatcher.forward(request, response);
            LOGGER.warn("Can't read book", e);
        }
    }
}
