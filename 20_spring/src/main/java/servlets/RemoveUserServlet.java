package servlets;


import lombok.AllArgsConstructor;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import services.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/users/delete")
@AllArgsConstructor
public class RemoveUserServlet extends HttpServlet {

    @Autowired
    private final UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = (String) req.getAttribute("id");
        long userId = Long.parseLong(id);
        User userById = userService.findById(userId);
        userService.remove(userById);
        resp.sendRedirect("/users");
    }
}