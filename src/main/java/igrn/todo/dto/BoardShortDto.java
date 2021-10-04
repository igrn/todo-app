package igrn.todo.dto;

import lombok.Getter;

@Getter
public class BoardShortDto {
    private final Integer id;
    private final String title;

    public BoardShortDto(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
}
