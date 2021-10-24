package igrn.todo.exception;

public class ColumnNotFoundException extends RuntimeException {

    public ColumnNotFoundException(String message) {
        super(message);
    }
}
