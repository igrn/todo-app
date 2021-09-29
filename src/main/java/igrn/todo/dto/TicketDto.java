package igrn.todo.dto;

import lombok.Getter;

@Getter
public class TicketDto {
    private final Integer id, parentId;
    private final String title;

    public TicketDto(Integer id, Integer parentId, String title) {
        this.id = id;
        this.parentId = parentId;
        this.title = title;
    }
}
