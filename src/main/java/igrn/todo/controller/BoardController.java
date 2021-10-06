package igrn.todo.controller;

import igrn.todo.dto.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {

    //Получить список всех досок пользователя (без подробной информации)
    @GetMapping
    public List<BoardShortDto> getAllBoards() {
        return List.of(new BoardShortDto(1, "Board 1"),
                       new BoardShortDto(2, "Board 2"),
                       new BoardShortDto(3, "Board 3"));
    }

    // Получить рабочую доску (со всеми колонками и карточками)
    @GetMapping("/{boardId}")
    public BoardDto getBoard(@PathVariable Integer boardId) {
        List<TicketDto> tickets1 = List.of(
                new TicketDto(1, 1, "Ticket 1"),
                new TicketDto(2, 1, "Ticket 2")
        );
        List<TicketDto> tickets2 = List.of(new TicketDto(3, 2, "Ticket 3"));
        List<ColumnDto> columns =  List.of(
                new ColumnDto(1, boardId, "Column 1", tickets1),
                new ColumnDto(2, boardId, "Column 2", tickets2)
        );
        return new BoardDto(boardId, "Board" + boardId, columns);
    }

    // Создать новую доску
    @PostMapping
    public BoardShortDto createBoard(@RequestBody BoardUpdateDto boardUpdateDto) {
        return new BoardShortDto(999, boardUpdateDto.getTitle());
    }

    // Изменить имя доски
    @PutMapping("/{boardId}")
    public BoardShortDto editBoard(@RequestBody BoardUpdateDto boardUpdateDto,
                                   @PathVariable Integer boardId) {
        return new BoardShortDto(boardId, boardUpdateDto.getTitle());
    }

    // Удалить доску (со всем содержимым!)
    @DeleteMapping("/{boardId}")
    public BoardShortDto deleteBoard(@PathVariable Integer boardId) {
        return new BoardShortDto(boardId, "Deleted Board");
    }
}
