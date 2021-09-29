package igrn.todo.controller;

import igrn.todo.dto.TicketDto;
import igrn.todo.dto.TicketUpdateDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/columns/{columnId}/tickets")
public class TicketController {

    // Получить карточку из определенной колонки
    @GetMapping("/{ticketId}")
    public TicketDto getTicket(@PathVariable("ticketId") Integer id,
                               @PathVariable("columnId") Integer parentId) {
        return new TicketDto(id, parentId, "Ticket " + id);
    }

    // Создать карточку в определенной колонке
    @PostMapping()
    public TicketDto createTicket(@RequestBody TicketUpdateDto ticketUpdateDto,
                                  @PathVariable("columnId") Integer parentId) {
        return new TicketDto(999, parentId, ticketUpdateDto.getTitle());
    }

    // Изменить карточку в определенной колонке
    @PutMapping("/{ticketId}")
    public TicketDto editTicket(@RequestBody TicketUpdateDto ticketUpdateDto,
                                @PathVariable("ticketId") Integer id,
                                @PathVariable("columnId") Integer parentId) {
        return new TicketDto(id, parentId, ticketUpdateDto.getTitle());
    }

    // Удалить карточку из определенной колонки
    @DeleteMapping("/{ticketId}")
    public TicketDto deleteTicket(@PathVariable("ticketId") Integer id,
                                  @PathVariable("columnId") Integer parentId) {
        return new TicketDto(id, parentId, "Deleted Ticket");
    }
}
