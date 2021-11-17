package igrn.todo.service;

import igrn.todo.dto.column.ColumnDto;
import igrn.todo.dto.column.ColumnShortDto;
import igrn.todo.dto.column.ColumnTitleDto;

public interface ColumnService {

    ColumnDto getColumn(Integer columnId,
                        Integer boardId);

    ColumnShortDto createColumn(Integer boardId,
                                ColumnTitleDto columnTitleDto);

    ColumnDto editColumn(Integer columnId,
                         Integer boardId,
                         ColumnTitleDto columnTitleDto);

    ColumnDto deleteColumn(Integer columnId,
                           Integer boardId);
}
