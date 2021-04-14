package by.nikita.web.controller.command.impl;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.PagePath;
import by.nikita.web.exception.ServiceException;
import by.nikita.web.model.service.impl.BookServiceImpl;
import by.nikita.web.model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteBookCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(DeleteBookCommand.class);
    private BookServiceImpl bookService = BookServiceImpl.getInstance();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        String idBookString = request.getParameter(Attribute.BOOKS_ID);
        int idBook = Integer.parseInt(idBookString);
        try {
            bookService.deleteBook(idBook);
            requestDispatcher = request.getRequestDispatcher(PagePath.MAIN_INDEX_PAGE_COMMAND);
            requestDispatcher.forward(request, response);
        }catch (ServiceException ex){
            requestDispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            requestDispatcher.forward(request, response);
            LOGGER.warn("Can't delete book", ex);
        }
    }
}
