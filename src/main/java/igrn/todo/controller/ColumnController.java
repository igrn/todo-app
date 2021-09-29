package igrn.todo.controller;

import igrn.todo.dto.ColumnDto;
import igrn.todo.dto.ColumnUpdateDto;
import igrn.todo.dto.TicketDto;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/columns")
public class ColumnController {

    // Получить все колонки
    @GetMapping
    public List<ColumnDto> getAllColumns() {
        List<TicketDto> tickets1 = List.of(
                new TicketDto(1, 1, "Ticket 1"),
                new TicketDto(2, 1, "Ticket 2")
        );
        List<TicketDto> tickets2 = List.of(new TicketDto(3, 2, "Ticket 3"));
        return List.of(
                new ColumnDto(1, "Column 1", tickets1),
                new ColumnDto(2, "Column 2", tickets2)
        );
    }

    // Получить определенную колонку со всеми её карточками
    @GetMapping("/{columnId}")
    public ColumnDto getColumn(@PathVariable("columnId") Integer columnId) {
        List<TicketDto> tickets = List.of(
                new TicketDto(1, columnId, "Ticket 1"),
                new TicketDto(2, columnId, "Ticket 2")
        );
        return new ColumnDto(columnId, "Column " + columnId, tickets);
    }

    // Создать колонку (без карточек)
    @PostMapping
    public ColumnDto createColumn(@RequestBody ColumnUpdateDto columnUpdateDto) {
        return new ColumnDto(999, columnUpdateDto.getTitle(), null);
    }

    // Изменить определенную колонку
    @PutMapping("/{columnId}")
    public ColumnDto editColumn(@RequestBody ColumnUpdateDto columnUpdateDto,
                                @PathVariable("columnId") Integer columnId) {
        List<TicketDto> tickets = List.of(
                new TicketDto(1, columnId, "Ticket 1"),
                new TicketDto(2, columnId, "Ticket 2")
        );
        return new ColumnDto(columnId, columnUpdateDto.getTitle(), tickets);
    }

    // Удалить колонку (со всеми её карточками!)
    @DeleteMapping("/{columnId}")
    public ColumnDto deleteColumn(@PathVariable("columnId") Integer columnId) {
        List<TicketDto> tickets = List.of(
                new TicketDto(1, columnId, "Ticket 1"),
                new TicketDto(2, columnId, "Ticket 2")
        );
        return new ColumnDto(columnId, "Deleted Column", tickets);
    }
}
