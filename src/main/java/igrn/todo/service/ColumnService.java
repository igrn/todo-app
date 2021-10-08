package igrn.todo.service;

import igrn.todo.dto.ColumnDto;
import igrn.todo.dto.ColumnShortDto;
import igrn.todo.dto.ColumnTitleDto;

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
