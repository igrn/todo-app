package igrn.todo.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ColumnDto {
    private final Integer id;
    private final String title;
    private final List<TicketDto> tickets;

    public ColumnDto(Integer id, String title, List<TicketDto> tickets) {
        this.id = id;
        this.title = title;
        this.tickets = tickets;
    }
}
