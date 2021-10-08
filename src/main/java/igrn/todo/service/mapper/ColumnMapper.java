package igrn.todo.service.mapper;

import igrn.todo.dto.ColumnDto;
import igrn.todo.dto.ColumnShortDto;
import igrn.todo.entity.Column;
import igrn.todo.entity.Ticket;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ColumnMapper {
    private final TicketMapper ticketMapper;

    public ColumnMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public ColumnDto toColumnDto(Column column, Collection<Ticket> tickets) {
        return new ColumnDto(
                column.getId(),
                column.getTitle(),
                ticketMapper.toTicketDto(tickets)
        );
    }

    public List<ColumnDto> toColumnDto(Map<Column, Collection<Ticket>> columns) {
        return columns.entrySet().stream()
                .map(entry -> {
                    Column column = entry.getKey();
                    Collection<Ticket> tickets = entry.getValue();
                    return toColumnDto(column, tickets);
                })
                .collect(Collectors.toList());
    }

    public ColumnShortDto toColumnShortDto(Column column) {
        return new ColumnShortDto(column.getId(), column.getTitle());
    }
}
