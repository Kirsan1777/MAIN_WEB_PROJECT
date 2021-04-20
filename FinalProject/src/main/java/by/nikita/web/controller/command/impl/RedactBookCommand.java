package by.nikita.web.controller.command.impl;

import by.nikita.web.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * The {@code RedactBookCommand} class represents redact book.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public class RedactBookCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
