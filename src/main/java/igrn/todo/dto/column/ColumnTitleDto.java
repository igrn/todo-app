package igrn.todo.dto.column;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ColumnTitleDto {
    private final String title;

    public ColumnTitleDto(@JsonProperty("title") String title) {
        this.title = title;
    }
}
