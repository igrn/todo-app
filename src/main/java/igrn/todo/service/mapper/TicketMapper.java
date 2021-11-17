package igrn.todo.service.mapper;

import igrn.todo.dto.ticket.TicketDto;
import igrn.todo.entity.Ticket;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TicketMapper {

    public TicketDto toTicketDto(Ticket ticket) {
        return new TicketDto(
                ticket.getId(),
                ticket.getTitle()
        );
    }

    public List<TicketDto> toTicketDto(Collection<Ticket> tickets) {
        return tickets.stream()
                .map(this::toTicketDto)
                .collect(Collectors.toList());
    }
}
