package servlets;

import dao.jdbc.JdbcUserDaoImpl;
import model.User;
import services.UserService;
import support.ConnectionManager;
import support.DBPoolConfig;
import util.ParamFromUsers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private final UserService userService;

    public UsersServlet() {
        this.userService = new UserService(new JdbcUserDaoImpl(ConnectionManager.getInstance(new DBPoolConfig("jdbc.properties"))));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        User user = (User) req.getSession().getAttribute("user");
        String role = user.getRole().getName();
        List<Integer> age = new ArrayList();
        if (role.equals("ADMIN")) {
            req.setAttribute("users", userService.findAll());
            for (User userBirthday : userService.findAll()) {
                age.add(ParamFromUsers.getAgeFromDateOfBirthday(userBirthday.getBirthday()));
            }
            req.setAttribute("age", age);
            req.getRequestDispatcher("/view/adminPage.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/view/userPage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
