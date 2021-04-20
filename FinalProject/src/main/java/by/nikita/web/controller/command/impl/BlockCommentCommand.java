package by.nikita.web.controller.command.impl;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.PagePath;
import by.nikita.web.exception.TransactionException;
import by.nikita.web.model.dao.Transaction;
import org.apache.log4j.Logger;
import org.w3c.dom.Attr;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * The {@code BlockCommentCommand} class represents block comment command.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public class BlockCommentCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(BlockCommentCommand.class);
    private static final int BLOCK_COMMENT_VALUE =  1;
    private Transaction transaction = Transaction.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        int idComment = Integer.parseInt(request.getParameter(Attribute.COMMENT_ID));
        int idUser = Integer.parseInt(request.getParameter(Attribute.USERS_ID));
        int idBook = Integer.parseInt(request.getParameter(Attribute.BOOKS_ID));
        try {
            transaction.changeStateComment(idComment, idUser, BLOCK_COMMENT_VALUE);
            request.setAttribute(Attribute.BOOKS_ID, idBook);
            requestDispatcher = request.getRequestDispatcher(PagePath.MAIN_BOOK_PAGE_COMMAND);
            requestDispatcher.forward(request,response);
        } catch (TransactionException e) {
            requestDispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            requestDispatcher.forward(request, response);
            LOGGER.warn("Can't block comment", e);
        }
    }
}
