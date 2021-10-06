package igrn.todo.controller;

import igrn.todo.dto.TicketDto;
import igrn.todo.dto.TicketUpdateDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards/{boardId}/columns/{columnId}/tickets")
public class TicketController {

    // Получить карточку из определенной колонки
    @GetMapping("/{ticketId}")
    public TicketDto getTicket(@PathVariable Integer ticketId,
                               @PathVariable Integer columnId,
                               @PathVariable Integer boardId) {
        return new TicketDto(ticketId, columnId, "Ticket " + ticketId);
    }

    // Создать карточку в определенной колонке
    @PostMapping()
    public TicketDto createTicket(@RequestBody TicketUpdateDto ticketUpdateDto,
                                  @PathVariable Integer columnId,
                                  @PathVariable Integer boardId) {
        return new TicketDto(999, columnId, ticketUpdateDto.getTitle());
    }

    // Изменить карточку в определенной колонке
    @PutMapping("/{ticketId}")
    public TicketDto editTicket(@RequestBody TicketUpdateDto ticketUpdateDto,
                                @PathVariable Integer ticketId,
                                @PathVariable Integer columnId,
                                @PathVariable Integer boardId) {
        return new TicketDto(ticketId, columnId, ticketUpdateDto.getTitle());
    }

    // Удалить карточку из определенной колонки
    @DeleteMapping("/{ticketId}")
    public TicketDto deleteTicket(@PathVariable Integer ticketId,
                                  @PathVariable Integer columnId,
                                  @PathVariable Integer boardId) {
        return new TicketDto(ticketId, columnId, "Deleted Ticket");
    }
}
