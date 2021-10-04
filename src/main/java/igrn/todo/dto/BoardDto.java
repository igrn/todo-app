package igrn.todo.dto;

import lombok.Getter;
import java.util.List;

@Getter
public class BoardDto {
    private final Integer id;
    private final String title;
    private final List<ColumnDto> columns;

    public BoardDto(Integer id, String title, List<ColumnDto> columns) {
        this.id = id;
        this.title = title;
        this.columns = columns;
    }
}
