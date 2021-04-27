package by.nikita.web.controller.command.impl;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.PagePath;
import by.nikita.web.exception.ServiceException;
import by.nikita.web.model.service.impl.BookServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * The {@code UpdateBookPictureCommand} class represents update book picture.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public class UpdateBookPictureCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(UpdateBookPictureCommand.class);
    private BookServiceImpl bookService = BookServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        String namePicture =(String) request.getAttribute(Attribute.PICTURE);
        try {
            if(request.getAttribute(Attribute.BOOKS_ID) != null){
                int idBook = Integer.parseInt(request.getParameter(Attribute.BOOKS_ID));
                bookService.updateBookPicture(idBook, namePicture);
                request.setAttribute(Attribute.BOOKS, idBook);
                requestDispatcher = request.getRequestDispatcher(PagePath.REDACT_BOOK_PAGE_COMMAND);
                //requestDispatcher.forward(request,response);
                //response.sendRedirect(PagePath.REDACT_BOOK_PAGE_COMMAND + "&books=" + idBook);
            }else{
                requestDispatcher = request.getRequestDispatcher(PagePath.BOOK_PAGE_COMMAND);
                //response.sendRedirect(PagePath.BOOK_PAGE_COMMAND);
            }
            requestDispatcher.forward(request,response);
            //requestDispatcher.forward(request,response);
        } catch (ServiceException e) {
            requestDispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            requestDispatcher.forward(request, response);
            LOGGER.warn("Can't update book picture", e);
        }

    }

}
