package igrn.todo.controller;

import igrn.todo.dto.TicketDto;
import igrn.todo.dto.TicketTitleDto;
import igrn.todo.service.TicketService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards/{boardId}/columns/{columnId}/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/{ticketId}")
    public TicketDto getTicket(@PathVariable Integer ticketId,
                               @PathVariable Integer columnId,
                               @PathVariable Integer boardId) {
        return ticketService.getTicket(ticketId, columnId, boardId);
    }

    @PostMapping()
    public TicketDto createTicket(@PathVariable Integer columnId,
                                  @PathVariable Integer boardId,
                                  @RequestBody TicketTitleDto ticketTitleDto) {
        return ticketService.createTicket(columnId, boardId, ticketTitleDto);
    }

    @PutMapping("/{ticketId}")
    public TicketDto editTicket(@PathVariable Integer ticketId,
                                @PathVariable Integer columnId,
                                @PathVariable Integer boardId,
                                @RequestBody TicketTitleDto ticketTitleDto) {
        return ticketService.editTicket(ticketId, columnId, boardId, ticketTitleDto);
    }

    @DeleteMapping("/{ticketId}")
    public TicketDto deleteTicket(@PathVariable Integer ticketId,
                                  @PathVariable Integer columnId,
                                  @PathVariable Integer boardId) {
        return ticketService.deleteTicket(ticketId, columnId, boardId);
    }
}
