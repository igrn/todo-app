package igrn.todo.exception;

import igrn.todo.enums.ExceptionMessage;

public class ColumnNotFoundException extends RuntimeException {

    public ColumnNotFoundException(String message) {
        super(message);
    }

    public static ColumnNotFoundException build() {
        String message = ExceptionMessage.COLUMN_NOT_FOUND.getMessage();
        return new ColumnNotFoundException(message);
    }
}
