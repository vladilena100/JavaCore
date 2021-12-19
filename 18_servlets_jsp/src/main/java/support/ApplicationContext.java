package support;

import dao.jdbc.JdbcRoleDaoImpl;
import dao.jdbc.JdbcUserDaoImpl;
import services.RoleService;
import services.UserService;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private static final Map<String, Object> services = new HashMap<>();

    private static volatile ApplicationContext applicationContext;

    private ApplicationContext() {

        RoleService roleService = new RoleService(new JdbcRoleDaoImpl(ConnectionManager.getInstance(new DBPoolConfig("jdbc.properties"))));

        UserService userService = new UserService(new JdbcUserDaoImpl(ConnectionManager.getInstance(new DBPoolConfig("jdbc.properties"))));

        services.put("userService", userService);
        services.put("roleService", roleService);
    }

    public Object getService(String name) {

        return services.get(name);
    }

    public static ApplicationContext getInstance() {

        if (applicationContext == null) {
            synchronized (ApplicationContext.class) {
                if (applicationContext == null) {
                    applicationContext = new ApplicationContext();
                }
            }
        }
        return applicationContext;
    }
}
