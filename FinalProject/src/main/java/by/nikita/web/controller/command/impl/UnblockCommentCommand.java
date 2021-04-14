package by.nikita.web.controller.command.impl;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.PagePath;
import by.nikita.web.exception.TransactionException;
import by.nikita.web.model.dao.Transaction;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnblockCommentCommand implements Command {
    private static final int UNBLOCK_COMMENT_VALUE =  0;
    private Transaction transaction = Transaction.getInstance();
    private static final Logger LOGGER = Logger.getLogger(UnblockCommentCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        int idComment = Integer.parseInt(request.getParameter(Attribute.COMMENT_ID));
        int idUser = Integer.parseInt(request.getParameter(Attribute.USERS_ID));
        int idBook = Integer.parseInt(request.getParameter(Attribute.BOOKS_ID));
        try {
            transaction.changeStateComment(idComment, idUser, UNBLOCK_COMMENT_VALUE);
            request.setAttribute(Attribute.BOOKS_ID, idBook);
            requestDispatcher = request.getRequestDispatcher(PagePath.MAIN_BOOK_PAGE_COMMAND);
            requestDispatcher.forward(request,response);
        } catch (TransactionException e) {
            requestDispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            requestDispatcher.forward(request, response);
            LOGGER.warn("Can't unblock comment", e);
        }
    }
}
