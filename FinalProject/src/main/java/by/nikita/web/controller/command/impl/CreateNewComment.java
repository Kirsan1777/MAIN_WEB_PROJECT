package by.nikita.web.controller.command.impl;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.Message;
import by.nikita.web.controller.command.PagePath;
import by.nikita.web.exception.ServiceException;
import by.nikita.web.model.entity.Comment;
import by.nikita.web.model.service.impl.BookServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * The {@code CreateNewComment} class represents create new comment command.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public class CreateNewComment implements Command {
    private static final Logger LOGGER = Logger.getLogger(CreateNewComment.class);
    private BookServiceImpl bookService = BookServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        HttpSession session = request.getSession();
        Comment comment = new Comment();
        String text = request.getParameter(Attribute.TEXT);
        String idBook = request.getParameter(Attribute.BOOKS_ID);
        comment.setIdBook((Integer.parseInt(idBook)));
        comment.setComment(text);
        comment.setIdUser((Integer)session.getAttribute(Attribute.ID));
        try {
            bookService.addComment(comment);
            request.setAttribute(Attribute.BOOKS_ID, comment.getIdBook());
            requestDispatcher = request.getRequestDispatcher(PagePath.MAIN_BOOK_PAGE_COMMAND);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            requestDispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            requestDispatcher.forward(request, response);
            LOGGER.warn("Can't create comment", e);
        }

    }
}
