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


@WebServlet("/users/edit")
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

        req.setAttribute("user", userService.findById(id));
        req.getRequestDispatcher("addUpdateUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User userForUpdate = ParamFromUsers.paramUser(req);
        String password = req.getParameter("password");

        String requestURI = req.getRequestURI();
        String userId = requestURI.substring(requestURI.lastIndexOf("/") + 1);
        Long id = Long.valueOf(userId);
        User userById = userService.findById(id);

        //TODO проверить пароль на  соответствие пароль и пароль снова

        if (password == null || password.isEmpty()) {
            userForUpdate.setPassword(userById.getPassword());
        }

        userService.update(userForUpdate);
        doGet(req, resp);
    }
}
