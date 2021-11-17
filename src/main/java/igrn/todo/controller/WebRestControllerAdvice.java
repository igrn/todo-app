package igrn.todo.controller;

import igrn.todo.exception.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebRestControllerAdvice {
    private final Logger logger = LogManager.getLogger();

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleException(IllegalArgumentException e) {
        logger.warn(e.getMessage());
        return e.getMessage();
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public String handleException(UserAlreadyExistsException e) {
        logger.warn(e.getMessage());
        return e.getMessage();
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            UserNotFoundException.class, RoleNotFoundException.class,
            BoardNotFoundException.class, ColumnNotFoundException.class,
            TicketNotFoundException.class
    })
    public String handleException(RuntimeException e) {
        logger.warn(e.getMessage());
        return e.getMessage();
    }
}
