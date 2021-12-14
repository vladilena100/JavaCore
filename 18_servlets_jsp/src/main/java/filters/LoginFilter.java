package filters;

import dao.jdbc.JdbcUserDaoImpl;
import model.User;
import services.UserService;
import support.ConnectionManager;
import support.DBPoolConfig;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/login")
public class LoginFilter implements Filter {

    private final UserService userService;

    public LoginFilter() {
        this.userService = new UserService(new JdbcUserDaoImpl((ConnectionManager.getInstance(new DBPoolConfig("jdbc.properties")))));
        ;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String login = servletRequest.getParameter("login");
        String password = servletRequest.getParameter("password");
        User user = userService.getUserByLoginPassword(login, password);
        if (user != null) {
            setSessionAttributeRequest(servletRequest, user);
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect("/login");
        }
    }

    private void setSessionAttributeRequest(ServletRequest servletRequest, User user) {
        ((HttpServletRequest) servletRequest).getSession().setAttribute("login", servletRequest.getParameter("login"));
        ((HttpServletRequest) servletRequest).getSession().setAttribute("id", user.getId());
        ((HttpServletRequest) servletRequest).getSession().setAttribute("firstName", user.getFirstName());
        ((HttpServletRequest) servletRequest).getSession().setAttribute("lastName", user.getLastName());
        ((HttpServletRequest) servletRequest).getSession().setAttribute("roleName", user.getRole().getName());
    }
}
