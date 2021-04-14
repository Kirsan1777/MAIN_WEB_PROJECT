package by.nikita.web.controller.command.impl;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.Message;
import by.nikita.web.controller.command.PagePath;
import by.nikita.web.exception.ServiceException;
import by.nikita.web.exception.TransactionException;
import by.nikita.web.model.dao.Transaction;
import by.nikita.web.model.entity.Book;
import by.nikita.web.model.entity.User;
import by.nikita.web.model.validator.BookValidator;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BuyBookUser implements Command {

    Transaction transaction = Transaction.getInstance();
    private static final Logger LOGGER = Logger.getLogger(BuyBookUser.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        HttpSession session = request.getSession();
        BookValidator validator = new BookValidator();
        User user = new User();
        Book book = new Book();
        String costBook = request.getParameter(Attribute.COST);
        String idAuthorString = request.getParameter(Attribute.AUTHOR_ID);
        String idBookString = request.getParameter(Attribute.BOOKS_ID);
        user.setId((Integer) session.getAttribute(Attribute.ID));
        book.setId(Integer.parseInt(idBookString));
        book.setCost(Double.parseDouble(costBook));
        book.setAuthorId(Integer.parseInt(idAuthorString));
        try {
            if (user.getId() == book.getAuthorId()) {
                response.sendRedirect(PagePath.MAIN_INDEX_PAGE_COMMAND + Attribute.MESSAGE_YOUR);
            } else {
                if (validator.chekBookAvailability(user.getId(), book.getId())) {
                    if (validator.chekBuyOperation(user.getId(), book.getCost())) {
                        transaction.buyBook(user, book);
                        response.sendRedirect(PagePath.MAIN_INDEX_PAGE_COMMAND + Attribute.MESSAGE_BUY);
                    } else {
                        response.sendRedirect(PagePath.MAIN_INDEX_PAGE_COMMAND + Attribute.MESSAGE_NOT_MONEY);
                    }
                } else {
                    response.sendRedirect(PagePath.MAIN_INDEX_PAGE_COMMAND + Attribute.MESSAGE_BOOK_BOUGHT);
                }
            }
        } catch (TransactionException | ServiceException ex) {
            LOGGER.warn("Can't buy book", ex);
            requestDispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
