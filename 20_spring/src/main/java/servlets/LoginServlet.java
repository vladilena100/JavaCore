package servlets;


import lombok.AllArgsConstructor;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/login")
@AllArgsConstructor
public class LoginServlet extends HttpServlet {

    @Autowired
    private final UserService userService;

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