package igrn.todo.exception;

import igrn.todo.enums.ExceptionMessage;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(String message) {
        super(message);
    }

    public static RoleNotFoundException build() {
        String message = ExceptionMessage.ROLE_NOT_FOUND.getMessage();
        return new RoleNotFoundException(message);
    }
}
