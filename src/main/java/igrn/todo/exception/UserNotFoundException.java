package igrn.todo.exception;

import igrn.todo.enums.ExceptionMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserNotFoundException extends UsernameNotFoundException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public static UserNotFoundException buildWith(String email) {
        String message = String.format(ExceptionMessage.USER_NOT_FOUND.getMessage(), email);
        return new UserNotFoundException(message);
    }
}
