package igrn.todo.service.mapper;

import igrn.todo.dto.column.ColumnDto;
import igrn.todo.dto.column.ColumnShortDto;
import igrn.todo.entity.Column;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ColumnMapper {
    private final TicketMapper ticketMapper;

    public ColumnMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public ColumnDto toColumnDto(Column column) {
        return new ColumnDto(
                column.getId(),
                column.getTitle(),
                ticketMapper.toTicketDto(column.getTickets())
        );
    }

    public List<ColumnDto> toColumnDto(Collection<Column> columns) {
        return columns.stream()
                .map(this::toColumnDto)
                .collect(Collectors.toList());
    }

    public ColumnShortDto toColumnShortDto(Column column) {
        return new ColumnShortDto(column.getId(), column.getTitle());
    }
}
