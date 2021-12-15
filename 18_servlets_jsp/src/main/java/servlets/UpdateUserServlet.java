package servlets;

import dao.jdbc.JdbcUserDaoImpl;
import model.User;
import services.UserService;
import support.ConnectionManager;
import support.DBPoolConfig;
import util.ParamFromUsers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/edit")
public class UpdateUserServlet extends HttpServlet {

    private final UserService userService;

    public UpdateUserServlet() {
        this.userService = new UserService(new JdbcUserDaoImpl(ConnectionManager.getInstance(new DBPoolConfig("jdbc.properties"))));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();
        String userId = requestURI.substring(requestURI.lastIndexOf("/") + 1);
        Long id = Long.valueOf(userId);

        req.setAttribute("action", "Edit");
        req.setAttribute("request", req.getRequestURI());
        req.setAttribute("user", userService.findById(id));
        req.getRequestDispatcher("addUpdateUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User userForUpdate = ParamFromUsers.paramUser(req);
        String password = req.getParameter("password");
        String passwordAgain = req.getParameter("passwordAgain");

        String requestURI = req.getRequestURI();
        String userId = requestURI.substring(requestURI.lastIndexOf("/") + 1);
        Long id = Long.valueOf(userId);
        User userById = userService.findById(id);

        if (userForUpdate.getPassword() == null || userForUpdate.getPassword().isEmpty()) {
            userForUpdate.setPassword(userById.getPassword());
        }

        if (password.equals(passwordAgain)) {
            userForUpdate.setPassword(password);
        }

        userService.update(userForUpdate);
        doGet(req, resp);
    }
}
