package igrn.todo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class BoardUpdateDto {
    private final String title;

    public BoardUpdateDto(@JsonProperty("title") String title) {
        this.title = title;
    }
}
