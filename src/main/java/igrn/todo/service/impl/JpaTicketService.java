package igrn.todo.service.impl;

import igrn.todo.dto.TicketDto;
import igrn.todo.dto.TicketTitleDto;
import igrn.todo.entity.Board;
import igrn.todo.entity.Column;
import igrn.todo.entity.Ticket;
import igrn.todo.repository.ColumnRepository;
import igrn.todo.repository.TicketRepository;
import igrn.todo.service.TicketService;
import igrn.todo.service.factory.TicketFactory;
import igrn.todo.service.mapper.TicketMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class JpaTicketService implements TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final TicketFactory ticketFactory;
    private final ColumnRepository columnRepository;

    public JpaTicketService(TicketRepository ticketRepository,
                            TicketMapper ticketMapper,
                            TicketFactory ticketFactory,
                            ColumnRepository columnRepository) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
        this.ticketFactory = ticketFactory;
        this.columnRepository = columnRepository;
    }

    @Override
    public TicketDto getTicket(Integer ticketId,
                               Integer columnId,
                               Integer boardId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();
        validateParentIds(ticket, columnId, boardId);
        return ticketMapper.toTicketDto(ticket);
    }

    @Override
    public TicketDto createTicket(Integer columnId,
                                  Integer boardId,
                                  TicketTitleDto ticketTitleDto) {
        Ticket ticket = ticketFactory.build(columnId, ticketTitleDto.getTitle());
        validateParentIds(ticket, columnId, boardId);
        ticketRepository.saveAndFlush(ticket);
        return ticketMapper.toTicketDto(ticket);
    }

    @Override
    public TicketDto editTicket(Integer ticketId,
                                Integer columnId,
                                Integer boardId,
                                TicketTitleDto ticketTitleDto) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();
        validateParentIds(ticket, columnId, boardId);
        ticket.setTitle(ticketTitleDto.getTitle());
        ticketRepository.saveAndFlush(ticket);
        return ticketMapper.toTicketDto(ticket);
    }

    @Override
    public TicketDto deleteTicket(Integer ticketId,
                                  Integer columnId,
                                  Integer boardId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();
        validateParentIds(ticket, columnId, boardId);
        ticketRepository.delete(ticket);
        return ticketMapper.toTicketDto(ticket);
    }

    /**
     * Checks if provided boardId is correct.
     * @param ticket provided Ticket entity.
     * @param columnId id of a Column in which provided Ticket must exist.
     * @param boardId id of a Board in which provided Ticket must exist.
     * @throws RuntimeException if provided columnId or boardId are invalid.
     * @see Ticket
     * @see Column
     * @see Board
     */
    private void validateParentIds(Ticket ticket, Integer columnId, Integer boardId) {
        Column column = columnRepository.findById(columnId)
                .orElseThrow(() -> new RuntimeException("Invalid columnId"));

        if (!Objects.equals(column.getBoardId(), boardId)) {
            throw new RuntimeException("Invalid boardId");
        }
        if (!Objects.equals(ticket.getColumnId(), columnId)) {
            throw new RuntimeException("Invalid columnId");
        }
    }
}
