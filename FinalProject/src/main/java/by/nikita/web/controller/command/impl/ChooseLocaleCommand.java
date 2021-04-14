package by.nikita.web.controller.command.impl;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.controller.command.Command;
import by.nikita.web.controller.command.PagePath;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChooseLocaleCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        HttpSession session = request.getSession();
        String path = request.getParameter(Attribute.URL);
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        if(language.equals(Attribute.LANGUAGE_RU)){
            session.setAttribute(Attribute.LANGUAGE, Attribute.LANGUAGE_US);
        }else{
            session.setAttribute(Attribute.LANGUAGE, Attribute.LANGUAGE_RU);
        }
        requestDispatcher = request.getRequestDispatcher(PagePath.CONTROLLER + path);
        requestDispatcher.forward(request, response);
    }
}
