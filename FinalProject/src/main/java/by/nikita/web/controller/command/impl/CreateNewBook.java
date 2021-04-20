package by.nikita.web.controller.command.impl;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.Message;
import by.nikita.web.controller.command.PagePath;
import by.nikita.web.exception.ServiceException;
import by.nikita.web.exception.TransactionException;
import by.nikita.web.model.entity.Book;
import by.nikita.web.model.entity.User;
import by.nikita.web.model.service.impl.BookServiceImpl;
import org.apache.log4j.Logger;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
/**
 * The {@code CreateNewBook} class represents create new book command.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public class CreateNewBook implements Command {
    private static final Logger LOGGER = Logger.getLogger(CreateNewBook.class);
    private BookServiceImpl bookService = BookServiceImpl.getInstance();
    private static final String NAME_BASIC_PICTURE = "basicBook.jpg";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        Double cost = Double.parseDouble(request.getParameter(Attribute.COST));
        Integer genre = Integer.parseInt(request.getParameter(Attribute.GENRE));
        HttpSession session = request.getSession();
        Book book = new Book();
        book.setNameBook(request.getParameter(Attribute.NAME_BOOK));
        book.setCost(cost);
        book.setAuthorId((Integer) session.getAttribute(Attribute.ID));
        book.setDescription(request.getParameter(Attribute.DESCRIPTION));
        book.setText(request.getParameter(Attribute.TEXT));
        book.setPhotoReference(request.getParameter(Attribute.PICTURE));
        if(book.getPhotoReference().equals("/")){
            book.setPhotoReference(NAME_BASIC_PICTURE);
        }
        requestDispatcher = request.getRequestDispatcher(PagePath.BOOK_PAGE_COMMAND);
        if(book.getCost()<0){
            request.setAttribute(Attribute.MESSAGE, Message.INCORRECT_INPUT);
            requestDispatcher.forward(request, response);
        }else {
            try {
                bookService.addBook(book, genre);
                /*request.setAttribute(Attribute.MESSAGE, Message.CREATE_BOOK);
                requestDispatcher.forward(request, response);*/
                response.sendRedirect(PagePath.BOOK_PAGE_COMMAND + "&message=" + Message.CREATE_BOOK);
            } catch ( IOException | ServiceException e) {
                requestDispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
                requestDispatcher.forward(request, response);
                LOGGER.warn("Can't create book", e);
            }
        }
    }

}
