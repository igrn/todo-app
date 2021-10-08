package igrn.todo.service;

import igrn.todo.dto.TicketDto;
import igrn.todo.dto.TicketTitleDto;

public interface TicketService {

    TicketDto getTicket(Integer ticketId,
                        Integer columnId,
                        Integer boardId);

    TicketDto createTicket(Integer columnId,
                           Integer boardId,
                           TicketTitleDto ticketTitleDto);

    TicketDto editTicket(Integer ticketId,
                         Integer columnId,
                         Integer boardId,
                         TicketTitleDto ticketTitleDto);

    TicketDto deleteTicket(Integer ticketId,
                           Integer columnId,
                           Integer boardId);
}
