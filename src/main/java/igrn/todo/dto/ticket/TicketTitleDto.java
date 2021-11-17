package igrn.todo.dto.ticket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TicketTitleDto {
    private final String title;

    public TicketTitleDto(@JsonProperty("title") String title) {
        this.title = title;
    }
}
