package by.nikita.web.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
/**
 * The {@code Command} class represents command.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public interface Command {

    void execute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException;

}
