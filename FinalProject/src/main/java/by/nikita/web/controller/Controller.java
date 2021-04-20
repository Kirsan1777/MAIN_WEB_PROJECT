package by.nikita.web.controller;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.CommandProvider;
import by.nikita.web.model.dao.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The {@code Controller} class represents Controller.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final CommandProvider provider = new CommandProvider();

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        HttpSession session = request.getSession();
        String name;
        Command command;
        session.setAttribute(Attribute.URL, request.getQueryString());
        name = request.getParameter(Attribute.COMMAND);
        command = provider.takeCommand(name);
        command.execute(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.getInstance().deactivatePool();
    }

}
