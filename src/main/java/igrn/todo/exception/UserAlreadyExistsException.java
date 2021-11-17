package igrn.todo.exception;

import igrn.todo.enums.ExceptionMessage;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public static UserAlreadyExistsException buildWith(String email) {
        String message = String.format(ExceptionMessage.USER_ALREADY_EXISTS.getMessage(), email);
        return new UserAlreadyExistsException(message);
    }
}
