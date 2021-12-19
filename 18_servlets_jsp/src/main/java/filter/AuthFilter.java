package filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter("/*")
public class AuthFilter implements Filter {

    public static final String USERS_EDIT = "/users/edit";
    public static final String USERS_DELETE = "/users/delete";

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        servletRequest.setCharacterEncoding("UTF-8");

        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp =
                (HttpServletResponse) servletResponse;

        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        resp.setDateHeader("Expires", 0);

        HttpSession session = req.getSession(false);
        String url = req.getContextPath() + "/login";

        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = req.getRequestURI().equals(url);


        if (loggedIn) {
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
                return;
            } else if (req.getRequestURI().contains(USERS_DELETE)) {
                String requestURI = req.getRequestURI();
                String userId = requestURI.substring(requestURI.lastIndexOf("/") + 1);
                req.setAttribute("id", userId);
                req.getRequestDispatcher(USERS_DELETE).forward(servletRequest, servletResponse);
                return;
            }
            filterChain.doFilter(req, resp);
        } else if (req.getMethod().equals("POST") && loginRequest) {
            filterChain.doFilter(req, resp);
        } else {
            req.getRequestDispatcher("/login").forward(req, resp);
        }
    }
}