package igrn.todo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ColumnUpdateDto {
    private final String title;

    public ColumnUpdateDto(@JsonProperty("title") String title) {
        this.title = title;
    }
}
