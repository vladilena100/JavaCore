package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        servletRequest.setCharacterEncoding("UTF-8");

        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp =
                (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);
        String url = req.getContextPath() + "/login";

        boolean loggedIn = session != null && req.getSession().getAttribute("user") != null;
        boolean loginRequest = req.getRequestURI().equals(url);


        if (loggedIn || loginRequest) {
            filterChain.doFilter(req, resp);
        } else {
            req.getRequestDispatcher("/login").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
    }
}