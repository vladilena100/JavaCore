package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter("/*")
public class AuthFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        servletRequest.setCharacterEncoding("UTF-8");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp =
                (HttpServletResponse) servletResponse;

        boolean isLoggedIn = req.getSession().getAttribute("user") != null;
        final String REQ_METHOD = req.getMethod();
        final String REQ_URI = req.getRequestURI();

        if (isLoggedIn || (REQ_METHOD.equals("POST") && REQ_URI.equals("/login"))) {
            filterChain.doFilter(req, resp);
        } else {
            req.getRequestDispatcher("/login").forward(req, resp);
        }
    }
}