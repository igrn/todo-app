package igrn.todo.service.impl;

import igrn.todo.dto.TicketDto;
import igrn.todo.dto.TicketTitleDto;
import igrn.todo.entity.Ticket;
import igrn.todo.repository.TicketRepository;
import igrn.todo.service.TicketService;
import igrn.todo.service.factory.TicketFactory;
import igrn.todo.service.mapper.TicketMapper;
import org.springframework.stereotype.Service;

@Service
public class JpaTicketService implements TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final TicketFactory ticketFactory;

    public JpaTicketService(TicketRepository ticketRepository,
                            TicketMapper ticketMapper,
                            TicketFactory ticketFactory) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
        this.ticketFactory = ticketFactory;
    }

    @Override
    public TicketDto getTicket(Integer ticketId,
                               Integer columnId,
                               Integer boardId) {
        Ticket ticket = ticketRepository.findByIdAndColumn_IdAndColumn_Board_Id(
                ticketId, columnId, boardId).orElseThrow();
        return ticketMapper.toTicketDto(ticket);
    }

    @Override
    public TicketDto createTicket(Integer columnId,
                                  Integer boardId,
                                  TicketTitleDto ticketTitleDto) {
        Ticket ticket = ticketFactory.build(columnId, boardId, ticketTitleDto.getTitle());
        ticketRepository.saveAndFlush(ticket);
        return ticketMapper.toTicketDto(ticket);
    }

    @Override
    public TicketDto editTicket(Integer ticketId,
                                Integer columnId,
                                Integer boardId,
                                TicketTitleDto ticketTitleDto) {
        Ticket ticket = ticketRepository.findByIdAndColumn_IdAndColumn_Board_Id(
                ticketId, columnId, boardId).orElseThrow();
        ticket.setTitle(ticketTitleDto.getTitle());
        ticketRepository.saveAndFlush(ticket);
        return ticketMapper.toTicketDto(ticket);
    }

    @Override
    public TicketDto deleteTicket(Integer ticketId,
                                  Integer columnId,
                                  Integer boardId) {
        Ticket ticket = ticketRepository.findByIdAndColumn_IdAndColumn_Board_Id(
                ticketId, columnId, boardId).orElseThrow();
        ticketRepository.delete(ticket);
        return ticketMapper.toTicketDto(ticket);
    }
}
