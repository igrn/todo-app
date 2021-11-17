package igrn.todo.exception;

import igrn.todo.enums.ExceptionMessage;

public class BoardNotFoundException extends RuntimeException {

    public BoardNotFoundException(String message) {
        super(message);
    }

    public static BoardNotFoundException build() {
        String message = ExceptionMessage.BOARD_NOT_FOUND.getMessage();
        return new BoardNotFoundException(message);
    }
}
