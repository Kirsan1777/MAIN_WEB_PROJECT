package by.nikita.web.controller.command.impl;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.PagePath;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * The {@code Logout} class represents logout command.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public class Logout implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute(Attribute.ID);
        session.removeAttribute(Attribute.USER_ROLE);
        response.sendRedirect(PagePath.MAIN_INDEX_PAGE_COMMAND);
    }
}
