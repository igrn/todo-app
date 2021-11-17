package igrn.todo.exception;

import igrn.todo.enums.ExceptionMessage;

public class TicketNotFoundException extends RuntimeException {

    public TicketNotFoundException(String message) {
        super(message);
    }

    public static TicketNotFoundException build() {
        String message = ExceptionMessage.TICKET_NOT_FOUND.getMessage();
        return new TicketNotFoundException(message);
    }
}
