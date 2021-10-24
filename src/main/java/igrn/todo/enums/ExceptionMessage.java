package igrn.todo.enums;

import lombok.Getter;

@Getter
public enum ExceptionMessage {
    USER_NOT_FOUND("[email = %s] User not found"),
    ROLE_NOT_FOUND("Role not found"),
    BOARD_NOT_FOUND("Board not found"),
    COLUMN_NOT_FOUND("Column not found"),
    TICKET_NOT_FOUND("Ticket not found");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }
}
