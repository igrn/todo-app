package igrn.todo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TicketUpdateDto {
    private final String title;

    public TicketUpdateDto(@JsonProperty("title") String title) {
        this.title = title;
    }
}
