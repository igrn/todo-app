package igrn.todo.exception;

public class ColumnNotFoundException extends RuntimeException {

    public ColumnNotFoundException() {
        super();
    }

    public ColumnNotFoundException(String message) {
        super(message);
    }

    public ColumnNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ColumnNotFoundException(Throwable cause) {
        super(cause);
    }

    public ColumnNotFoundException(String message, Throwable cause,
                                   boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
