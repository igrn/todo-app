package igrn.todo.service.impl;

import igrn.todo.dto.column.ColumnDto;
import igrn.todo.dto.column.ColumnShortDto;
import igrn.todo.dto.column.ColumnTitleDto;
import igrn.todo.entity.Column;
import igrn.todo.enums.ExceptionMessage;
import igrn.todo.exception.ColumnNotFoundException;
import igrn.todo.repository.ColumnRepository;
import igrn.todo.service.ColumnService;
import igrn.todo.service.factory.ColumnFactory;
import igrn.todo.service.mapper.ColumnMapper;
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

    @Transactional
    @Override
    public ColumnDto getColumn(Integer columnId, Integer boardId) {
        Column column = columnRepository.findByIdAndBoard_Id(columnId, boardId).orElseThrow(() ->
                new ColumnNotFoundException(ExceptionMessage.COLUMN_NOT_FOUND.getMessage()));
        return columnMapper.toColumnDto(column);
    }

    @Transactional
    @Override
    public ColumnShortDto createColumn(Integer boardId, ColumnTitleDto columnTitleDto) {
        Column column = columnFactory.build(boardId, columnTitleDto.getTitle());
        column = columnRepository.save(column);
        return columnMapper.toColumnShortDto(column);
    }

    @Transactional
    @Override
    public ColumnDto editColumn(Integer columnId, Integer boardId, ColumnTitleDto columnTitleDto) {
        Column column = columnRepository.findByIdAndBoard_Id(columnId, boardId).orElseThrow(() ->
                new ColumnNotFoundException(ExceptionMessage.COLUMN_NOT_FOUND.getMessage()));
        column.setTitle(columnTitleDto.getTitle());
        column = columnRepository.save(column);
        return columnMapper.toColumnDto(column);
    }

    @Transactional
    @Override
    public ColumnDto deleteColumn(Integer columnId, Integer boardId) {
        Column column = columnRepository.findByIdAndBoard_Id(columnId, boardId).orElseThrow(() ->
                new ColumnNotFoundException(ExceptionMessage.COLUMN_NOT_FOUND.getMessage()));
        columnRepository.delete(column);
        return columnMapper.toColumnDto(column);
    }
}
