package exception;

public class HibernateDaoException extends RuntimeException {
    public HibernateDaoException(String message, Exception e) {
        super(message);
    }
}
