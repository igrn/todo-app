package igrn.todo.service.impl;

import igrn.todo.dto.ColumnDto;
import igrn.todo.dto.ColumnShortDto;
import igrn.todo.dto.ColumnTitleDto;
import igrn.todo.entity.Column;
import igrn.todo.repository.ColumnRepository;
import igrn.todo.service.ColumnService;
import igrn.todo.service.factory.ColumnFactory;
import igrn.todo.service.mapper.ColumnMapper;
import org.springframework.stereotype.Service;

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

    @Override
    public ColumnDto getColumn(Integer columnId, Integer boardId) {
        Column column = columnRepository.findByIdAndBoard_Id(columnId, boardId).orElseThrow();
        return columnMapper.toColumnDto(column);
    }

    @Override
    public ColumnShortDto createColumn(Integer boardId, ColumnTitleDto columnTitleDto) {
        Column column = columnFactory.build(boardId, columnTitleDto.getTitle());
        columnRepository.saveAndFlush(column);
        return columnMapper.toColumnShortDto(column);
    }

    @Override
    public ColumnDto editColumn(Integer columnId, Integer boardId, ColumnTitleDto columnTitleDto) {
        Column column = columnRepository.findByIdAndBoard_Id(columnId, boardId).orElseThrow();
        column.setTitle(columnTitleDto.getTitle());
        columnRepository.saveAndFlush(column);
        return columnMapper.toColumnDto(column);
    }

    @Override
    public ColumnDto deleteColumn(Integer columnId, Integer boardId) {
        Column column = columnRepository.findByIdAndBoard_Id(columnId, boardId).orElseThrow();
        columnRepository.delete(column);
        return columnMapper.toColumnDto(column);
    }
}
