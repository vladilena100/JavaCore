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


    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp =
                (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();

        boolean isLoggedIn = session != null && session.getAttribute("user") != null;

        if (isLoggedIn || (req.getMethod().equals("POST") && req.getRequestURI().equals("/login"))) {
            filterChain.doFilter(req, resp);
        } else {
            req.getRequestDispatcher("/login").forward(req, resp);
        }
    }
}