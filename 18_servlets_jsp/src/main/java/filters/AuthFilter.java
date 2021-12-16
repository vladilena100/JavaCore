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
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter("/*")
//public class AuthFilter implements Filter {
//
//    private final UserService userService;
//
//    public AuthFilter() {
//        this.userService = new UserService(new JdbcUserDaoImpl((ConnectionManager.getInstance(new DBPoolConfig("jdbc.properties")))));
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//        if (((HttpServletRequest) servletRequest).getSession().getAttribute("login") != null) {
//            if (((HttpServletRequest) servletRequest).getSession().getAttribute("roleName") == "ADMIN") {
//                servletRequest.setAttribute("users", userService.findAll());
//                servletRequest.getRequestDispatcher("/view/adminPage.jsp").forward(servletRequest, servletResponse);
//            } else if (((HttpServletRequest) servletRequest).getRequestURI().equals("/logout")){
//                filterChain.doFilter(servletRequest, servletResponse);
//            } else {
//                servletRequest.getRequestDispatcher("/users").forward(servletRequest, servletResponse);
//            }
//        } else if (((HttpServletRequest) servletRequest).getRequestURI().equals("/login") && ((HttpServletRequest) servletRequest).getMethod().equals("POST")) {
//            filterChain.doFilter(servletRequest, servletResponse);
//        } else {
//            servletRequest.getRequestDispatcher("/view/login.jsp").forward(servletRequest, servletResponse);
//
//        }
//
//
////        if (((HttpServletRequest) servletRequest).getSession().getAttribute("login") == null) {
////            servletRequest.getRequestDispatcher("/login").forward(servletRequest, servletResponse);
////        } else {
////            filterChain.doFilter(servletRequest, servletResponse);
////        }
//    }
//}

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

        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = req.getRequestURI().equals(url);


        if (loggedIn || loginRequest) {
            filterChain.doFilter(req, resp);
        } else {
            resp.sendRedirect(url);
        }
    }

    @Override
    public void destroy() {
    }
}