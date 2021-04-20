package by.nikita.web.controller.filter;

import by.nikita.web.controller.command.Attribute;
import by.nikita.web.model.entity.UserRole;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * The {@code UserRoleFilter} class represents User Role Filter.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
@WebFilter(urlPatterns = {("/*")})
    public class UserRoleFilter implements Filter {
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpSession session = request.getSession();
            UserRole type = (UserRole) session.getAttribute(Attribute.USER_ROLE);
            if (type == null) {
                type = UserRole.GUEST;
                session.setAttribute(Attribute.USER_ROLE, type);//правильная реализация
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }

        @Override
        public void destroy() {

        }
}
