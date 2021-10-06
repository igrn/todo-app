package igrn.todo.dto;

import lombok.Getter;
import java.util.List;

@Getter
public class ColumnDto {
    private final Integer id, parentId;
    private final String title;
    private final List<TicketDto> tickets;

    public ColumnDto(Integer id, Integer parentId, String title, List<TicketDto> tickets) {
        this.id = id;
        this.parentId = parentId;
        this.title = title;
        this.tickets = tickets;
    }
}
