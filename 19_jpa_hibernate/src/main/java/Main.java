import dao.hibernate.HibernateUserDaoImpl;
import model.User;
import support.HibernateSession;

public class Main {
    public static void main(String[] args) {
        HibernateUserDaoImpl hibernateUserDao = new HibernateUserDaoImpl(HibernateSession.getInstance());

        Long id = 1L;

        User user = hibernateUserDao.findById(id);

        System.out.println(user.toString());
    }
}
