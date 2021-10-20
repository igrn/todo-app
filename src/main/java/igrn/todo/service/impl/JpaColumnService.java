package igrn.todo.service.impl;

import igrn.todo.dto.ColumnDto;
import igrn.todo.dto.ColumnShortDto;
import igrn.todo.dto.ColumnTitleDto;
import igrn.todo.entity.Column;
import igrn.todo.exception.ColumnNotFoundException;
import igrn.todo.repository.ColumnRepository;
import igrn.todo.service.ColumnService;
import igrn.todo.service.factory.ColumnFactory;
import igrn.todo.service.mapper.ColumnMapper;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JpaColumnService implements ColumnService {
    private final ColumnRepository columnRepository;
    private final ColumnMapper columnMapper;
    private final ColumnFactory columnFactory;

    public JpaColumnService(ColumnRepository columnRepository,
                            ColumnMapper columnMapper,
                            ColumnFactory columnFactory) {
        this.columnRepository = columnRepository;
        this.columnMapper = columnMapper;
        this.columnFactory = columnFactory;
    }

    @Retryable(IllegalArgumentException.class)
    @Transactional
    @Override
    public ColumnDto getColumn(Integer columnId, Integer boardId) {
        Column column = columnRepository.findByIdAndBoard_Id(columnId, boardId)
                .orElseThrow(() -> new ColumnNotFoundException("Column not found"));
        return columnMapper.toColumnDto(column);
    }

    @Retryable(IllegalArgumentException.class)
    @Transactional
    @Override
    public ColumnShortDto createColumn(Integer boardId, ColumnTitleDto columnTitleDto) {
        Column column = columnFactory.build(boardId, columnTitleDto.getTitle());
        columnRepository.saveAndFlush(column);
        return columnMapper.toColumnShortDto(column);
    }

    @Retryable(IllegalArgumentException.class)
    @Transactional
    @Override
    public ColumnDto editColumn(Integer columnId, Integer boardId, ColumnTitleDto columnTitleDto) {
        Column column = columnRepository.findByIdAndBoard_Id(columnId, boardId)
                .orElseThrow(() -> new ColumnNotFoundException("Column not found"));
        column.setTitle(columnTitleDto.getTitle());
        columnRepository.saveAndFlush(column);
        return columnMapper.toColumnDto(column);
    }

    @Retryable(IllegalArgumentException.class)
    @Transactional
    @Override
    public ColumnDto deleteColumn(Integer columnId, Integer boardId) {
        Column column = columnRepository.findByIdAndBoard_Id(columnId, boardId)
                .orElseThrow(() -> new ColumnNotFoundException("Column not found"));
        columnRepository.delete(column);
        return columnMapper.toColumnDto(column);
    }
}
