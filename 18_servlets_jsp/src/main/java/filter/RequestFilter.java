package filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter("/users/*")
public class RequestFilter implements Filter {

    public static final String USERS_EDIT = "/users/edit";
    public static final String USERS_DELETE = "/users/delete";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp =
                (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession(false);

        User user = (User) session.getAttribute("user");
        if (!user.getRole().getName().equals("ADMIN") && !req.getRequestURI().equals("/logout")) {
            req.getRequestDispatcher("/users").forward(req, resp);
            return;
        }
        if (req.getRequestURI().contains(USERS_EDIT)) {
            String requestURI = req.getRequestURI();
            String userId = requestURI.substring(requestURI.lastIndexOf("/") + 1);
            req.setAttribute("id", userId);
            req.getRequestDispatcher(USERS_EDIT).forward(servletRequest, servletResponse);
        } else if (req.getRequestURI().contains(USERS_DELETE)) {
            String requestURI = req.getRequestURI();
            String userId = requestURI.substring(requestURI.lastIndexOf("/") + 1);
            req.setAttribute("id", userId);
            req.getRequestDispatcher(USERS_DELETE).forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(req, resp);
        }
    }
}
