package servlets;

import dao.jdbc.JdbcUserDaoImpl;
import model.User;
import services.UserService;
import support.ConnectionManager;
import support.DBPoolConfig;
import util.ParamFromUsers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUser extends HttpServlet {

    private final static String index = "/WEB-INF/view/updateUser.jsp";

    private UserService userService;

    public UpdateUser(UserService userService) {
        this.userService = new UserService(new JdbcUserDaoImpl(ConnectionManager.getInstance(new DBPoolConfig("jdbc.properties"))));
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userService.findAll());
        req.getRequestDispatcher(index).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF8");

        ParamFromUsers paramFromUsers = new ParamFromUsers();

        User user = userService.findByLogin(req.getParameter("Login"));

        if (req.getParameter("Password") != null) {
            req.setAttribute("Password", req.getParameter("Password"));
        } else {
            user.setPassword(user.getPassword());
        }

        userService.update(paramFromUsers.paramUser(req));
        doGet(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
