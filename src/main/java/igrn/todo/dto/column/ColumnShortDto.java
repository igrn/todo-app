package igrn.todo.dto.column;

import lombok.Getter;

@Getter
public class ColumnShortDto {
    private final Integer id;
    private final String title;

    public ColumnShortDto(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
}
