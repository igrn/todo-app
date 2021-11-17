package igrn.todo.dto.ticket;

import lombok.Getter;

@Getter
public class TicketDto {
    private final Integer id;
    private final String title;

    public TicketDto(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
}
