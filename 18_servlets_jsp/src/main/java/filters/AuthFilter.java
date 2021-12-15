package filters;

import dao.jdbc.JdbcUserDaoImpl;
import model.User;
import services.UserService;
import support.ConnectionManager;
import support.DBPoolConfig;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    private final UserService userService;

    public AuthFilter() {
        this.userService = new UserService(new JdbcUserDaoImpl((ConnectionManager.getInstance(new DBPoolConfig("jdbc.properties")))));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (((HttpServletRequest) servletRequest).getSession() != null) {
            if (((HttpServletRequest) servletRequest).getSession().getAttribute("roleName") == "ADMIN") {
                servletRequest.setAttribute("users", userService.findAll());
                servletRequest.getRequestDispatcher("/view/adminPage.jsp").forward(servletRequest, servletResponse);
            } else {
                servletRequest.getRequestDispatcher("/view/userPage.jsp").forward(servletRequest, servletResponse);
            }
        } else {
            servletRequest.getRequestDispatcher("/view/login.jsp").forward(servletRequest, servletResponse);
        }
    }
}
