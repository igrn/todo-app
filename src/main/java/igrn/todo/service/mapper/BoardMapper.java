package igrn.todo.service.mapper;

import igrn.todo.dto.BoardDto;
import igrn.todo.dto.BoardShortDto;
import igrn.todo.entity.Board;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BoardMapper {
    private final ColumnMapper columnMapper;

    public BoardMapper(ColumnMapper columnMapper) {
        this.columnMapper = columnMapper;
    }

    public BoardDto toBoardDto(Board board) {
        return new BoardDto(
                board.getId(),
                board.getTitle(),
                columnMapper.toColumnDto(board.getColumns())
        );
    }

    public BoardShortDto toBoardShortDto(Board board) {
        return new BoardShortDto(
                board.getId(),
                board.getTitle()
        );
    }

    public List<BoardShortDto> toBoardShortDto(Collection<Board> boards) {
        return boards.stream()
                .map(this::toBoardShortDto)
                .collect(Collectors.toList());
    }
}
