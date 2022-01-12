package servlets;


import dao.DaoUser;
import model.User;
import services.UserService;
import support.UserDAOFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/users/delete")
public class RemoveUserServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance((DaoUser) new UserDAOFactory().getDao());

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