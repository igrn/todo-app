package igrn.todo.service.impl;

import igrn.todo.dto.ticket.TicketDto;
import igrn.todo.dto.ticket.TicketTitleDto;
import igrn.todo.entity.Ticket;
import igrn.todo.exception.ColumnNotFoundException;
import igrn.todo.exception.TicketNotFoundException;
import igrn.todo.repository.ColumnRepository;
import igrn.todo.repository.TicketRepository;
import igrn.todo.service.TicketService;
import igrn.todo.service.factory.TicketFactory;
import igrn.todo.service.mapper.TicketMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JpaTicketService implements TicketService {
    private final ColumnRepository columnRepository;
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final TicketFactory ticketFactory;

    public JpaTicketService(ColumnRepository columnRepository,
                            TicketRepository ticketRepository,
                            TicketMapper ticketMapper,
                            TicketFactory ticketFactory) {
        this.columnRepository = columnRepository;
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
        this.ticketFactory = ticketFactory;
    }

    @Transactional
    @Override
    public TicketDto getTicket(Integer ticketId, Integer columnId, Integer boardId) {
        Ticket ticket = ticketRepository
                .findByIdAndColumn_IdAndColumn_Board_Id(ticketId, columnId, boardId)
                .orElseThrow(TicketNotFoundException::build);

        return ticketMapper.toTicketDto(ticket);
    }

    @Transactional
    @Override
    public TicketDto createTicket(Integer columnId, Integer boardId,
                                  TicketTitleDto ticketTitleDto) {
        if (columnRepository.existsByIdAndBoard_Id(columnId, boardId)) {
            Ticket ticket = ticketFactory.build(ticketTitleDto.getTitle(), columnId);
            ticket = ticketRepository.save(ticket);
            return ticketMapper.toTicketDto(ticket);
        } else {
            throw ColumnNotFoundException.build();
        }
    }

    @Transactional
    @Override
    public TicketDto editTicket(Integer ticketId, Integer columnId,
                                Integer boardId, TicketTitleDto ticketTitleDto) {
        Ticket ticket = ticketRepository
                .findByIdAndColumn_IdAndColumn_Board_Id(ticketId, columnId, boardId)
                .orElseThrow(TicketNotFoundException::build);

        ticket.setTitle(ticketTitleDto.getTitle());
        ticket = ticketRepository.save(ticket);
        return ticketMapper.toTicketDto(ticket);
    }

    @Transactional
    @Override
    public TicketDto deleteTicket(Integer ticketId, Integer columnId, Integer boardId) {
        Ticket ticket = ticketRepository
                .findByIdAndColumn_IdAndColumn_Board_Id(ticketId, columnId, boardId)
                .orElseThrow(TicketNotFoundException::build);

        ticketRepository.delete(ticket);
        return ticketMapper.toTicketDto(ticket);
    }
}
