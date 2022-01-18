package exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import services.UserService;

@ControllerAdvice
public class ExceptionAdvice {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    @ExceptionHandler(Throwable.class)
    public String exception(Throwable e) {
        LOGGER.error("Error: {}", e.getMessage());
        return "error";
    }
}
