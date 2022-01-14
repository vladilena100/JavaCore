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
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UsersServlet extends HttpServlet {

    @Autowired
    private final UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        User user = (User) req.getSession().getAttribute("user");
        String role = user.getRole().getName();
        if (role.equals("ADMIN")) {
            req.setAttribute("users", userService.findAll());
            req.getRequestDispatcher("/view/adminPage.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/view/userPage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    }
}