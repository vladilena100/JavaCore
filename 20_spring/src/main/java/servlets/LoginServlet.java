package servlets;


import dao.DaoUser;
import model.User;
import services.UserService;
import support.UserDAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance((DaoUser) new UserDAOFactory().getDao());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = userService.getUserByLoginPassword(login, password);
        if (user == null) {
            req.setAttribute("error", "login or password is not correct");
            doGet(req, resp);
        } else {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/users");
        }
    }
}