package by.nikita.web.controller.command.impl;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.Message;
import by.nikita.web.controller.command.PagePath;
import by.nikita.web.exception.ServiceException;
import by.nikita.web.model.entity.Book;
import by.nikita.web.model.service.impl.BookServiceImpl;
import org.apache.log4j.Logger;
import org.w3c.dom.Attr;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateBookInfo implements Command {

    private BookServiceImpl bookService = BookServiceImpl.getInstance();
    private static final Logger LOGGER = Logger.getLogger(UpdateBookInfo.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher;
        Book book = new Book();
        book.setNameBook(request.getParameter(Attribute.NAME));
        book.setDescription(request.getParameter(Attribute.DESCRIPTION));
        book.setText(request.getParameter(Attribute.TEXT));
        book.setCost(Double.parseDouble(request.getParameter(Attribute.COST)));
        book.setId(Integer.parseInt(request.getParameter(Attribute.BOOKS_ID)));
        try {
            bookService.updateBook(book);
            request.setAttribute(Attribute.MESSAGE, Message.UPDATED);
            requestDispatcher = request.getRequestDispatcher(PagePath.REDACT_BOOK_PAGE_COMMAND);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            requestDispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            requestDispatcher.forward(request, response);
            LOGGER.warn("Can't update book info", e);
        }
    }
}
