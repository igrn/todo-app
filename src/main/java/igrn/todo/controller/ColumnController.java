package igrn.todo.controller;

import igrn.todo.dto.ColumnDto;
import igrn.todo.dto.ColumnUpdateDto;
import igrn.todo.dto.TicketDto;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/boards/{boardId}/columns")
public class ColumnController {

    // Получить определенную колонку со всеми её карточками
    @GetMapping("/{columnId}")
    public ColumnDto getColumn(@PathVariable Integer columnId,
                               @PathVariable Integer boardId) {
        List<TicketDto> tickets = List.of(
                new TicketDto(1, columnId, "Ticket 1"),
                new TicketDto(2, columnId, "Ticket 2")
        );
        return new ColumnDto(columnId, boardId, "Column " + columnId, tickets);
    }

    // Создать колонку (без карточек)
    @PostMapping
    public ColumnDto createColumn(@RequestBody ColumnUpdateDto columnUpdateDto,
                                  @PathVariable Integer boardId) {
        return new ColumnDto(999, boardId, columnUpdateDto.getTitle(), null);
    }

    // Изменить определенную колонку
    @PutMapping("/{columnId}")
    public ColumnDto editColumn(@RequestBody ColumnUpdateDto columnUpdateDto,
                                @PathVariable Integer columnId,
                                @PathVariable Integer boardId) {
        List<TicketDto> tickets = List.of(
                new TicketDto(1, columnId, "Ticket 1"),
                new TicketDto(2, columnId, "Ticket 2")
        );
        return new ColumnDto(columnId, boardId, columnUpdateDto.getTitle(), tickets);
    }

    // Удалить колонку (со всеми её карточками!)
    @DeleteMapping("/{columnId}")
    public ColumnDto deleteColumn(@PathVariable Integer columnId,
                                  @PathVariable Integer boardId) {
        List<TicketDto> tickets = List.of(
                new TicketDto(1, columnId, "Ticket 1"),
                new TicketDto(2, columnId, "Ticket 2")
        );
        return new ColumnDto(columnId, boardId, "Deleted Column", tickets);
    }
}
