package igrn.todo.dto.board;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class BoardTitleDto {
    private final String title;

    public BoardTitleDto(@JsonProperty("title") String title) {
        this.title = title;
    }
}
