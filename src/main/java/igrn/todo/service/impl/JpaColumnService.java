package igrn.todo.service.impl;

import igrn.todo.dto.ColumnDto;
import igrn.todo.dto.ColumnShortDto;
import igrn.todo.dto.ColumnTitleDto;
import igrn.todo.entity.Board;
import igrn.todo.entity.Column;
import igrn.todo.entity.Ticket;
import igrn.todo.repository.ColumnRepository;
import igrn.todo.repository.TicketRepository;
import igrn.todo.service.ColumnService;
import igrn.todo.service.factory.ColumnFactory;
import igrn.todo.service.mapper.ColumnMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class JpaColumnService implements ColumnService {
    private final ColumnRepository columnRepository;
    private final ColumnMapper columnMapper;
    private final ColumnFactory columnFactory;
    private final TicketRepository ticketRepository;

    public JpaColumnService(ColumnRepository columnRepository,
                            ColumnMapper columnMapper,
                            ColumnFactory columnFactory,
                            TicketRepository ticketRepository) {
        this.columnRepository = columnRepository;
        this.columnMapper = columnMapper;
        this.columnFactory = columnFactory;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public ColumnDto getColumn(Integer columnId, Integer boardId) {
        Column column = columnRepository.findById(columnId).orElseThrow();
        validateBoardId(column, boardId);
        List<Ticket> tickets = ticketRepository.findAllByColumnId(columnId);
        return columnMapper.toColumnDto(column, tickets);
    }

    //В этом методе не нужна валидация boardId
    @Override
    public ColumnShortDto createColumn(Integer boardId, ColumnTitleDto columnTitleDto) {
        Column column = columnFactory.build(boardId, columnTitleDto.getTitle());
        columnRepository.saveAndFlush(column);
        return columnMapper.toColumnShortDto(column);
    }

    @Override
    public ColumnDto editColumn(Integer columnId, Integer boardId, ColumnTitleDto columnTitleDto) {
        Column column = columnRepository.findById(columnId).orElseThrow();
        validateBoardId(column, boardId);
        column.setTitle(columnTitleDto.getTitle());
        columnRepository.saveAndFlush(column);
        List<Ticket> tickets = ticketRepository.findAllByColumnId(columnId);
        return columnMapper.toColumnDto(column, tickets);
    }

    @Override
    public ColumnDto deleteColumn(Integer columnId, Integer boardId) {
        Column column = columnRepository.findById(columnId).orElseThrow();
        validateBoardId(column, boardId);
        List<Ticket> tickets = ticketRepository.findAllByColumnId(columnId);
        ticketRepository.deleteAllByColumnId(columnId);
        columnRepository.delete(column);
        return columnMapper.toColumnDto(column, tickets);
    }

    /**
     * Checks if provided boardId is correct.
     * @param column provided Column entity.
     * @param boardId id of a Board in which provided Column must exist.
     * @throws RuntimeException if provided boardId is invalid.
     * @see Column
     * @see Board
     */
    private void validateBoardId(Column column, Integer boardId) {
        if (!Objects.equals(column.getBoardId(), boardId)) {
            throw new RuntimeException("Invalid boardId");
        }
    }
}
