package by.nikita.web.controller.filter;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.model.entity.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The {@code LanguageFilter} class represents Language Filter.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
@WebFilter(filterName = "LanguageFilter", urlPatterns = {("/*")})
public class LanguageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        if (language == null) {
            language = Attribute.LANGUAGE_US;
            session.setAttribute(Attribute.LANGUAGE, language);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
