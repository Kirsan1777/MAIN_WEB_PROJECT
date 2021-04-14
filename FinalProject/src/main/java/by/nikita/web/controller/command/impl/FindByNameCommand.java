package by.nikita.web.controller.command.impl;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.Message;
import by.nikita.web.controller.command.PagePath;
import by.nikita.web.exception.ServiceException;
import by.nikita.web.model.entity.Book;
import by.nikita.web.model.entity.User;
import by.nikita.web.model.service.impl.BookServiceImpl;
import by.nikita.web.model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.w3c.dom.Attr;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FindByNameCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(FindByNameCommand.class);
    private BookServiceImpl bookService = BookServiceImpl.getInstance();
    private UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        if(request.getParameter(Attribute.NAME).equals("") || request.getParameter(Attribute.NAME) == null){
            request.setAttribute(Attribute.MESSAGE, Message.INCORRECT_INPUT);
            requestDispatcher = request.getRequestDispatcher(PagePath.SEARCH_PAGE);
            requestDispatcher.forward(request, response);
        }
        String name = request.getParameter(Attribute.NAME);
        try {
            List<Book> books = bookService.getAllBookByName(name);
            List<User> users = userService.getAllUserByName(name);
            if(books.size() != 0) {
                request.setAttribute(Attribute.BOOKS, books);
            }
            if(users.size() != 0) {
                request.setAttribute(Attribute.USERS, users);
            }
            requestDispatcher = request.getRequestDispatcher(PagePath.SEARCH_PAGE);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            requestDispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            requestDispatcher.forward(request, response);
            LOGGER.warn("Can't find by name", e);
        }
    }
}
