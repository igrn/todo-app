package igrn.todo.controller;

import igrn.todo.dto.ColumnDto;
import igrn.todo.dto.ColumnShortDto;
import igrn.todo.dto.ColumnTitleDto;
import igrn.todo.service.ColumnService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards/{boardId}/columns")
public class ColumnController {
    private final ColumnService columnService;

    public ColumnController(ColumnService columnService) {
        this.columnService = columnService;
    }

    @GetMapping("/{columnId}")
    public ColumnDto getColumn(@PathVariable Integer columnId,
                               @PathVariable Integer boardId) {
        return columnService.getColumn(columnId, boardId);
    }

    @PostMapping
    public ColumnShortDto createColumn(@PathVariable Integer boardId,
                                       @RequestBody ColumnTitleDto columnTitleDto) {
        return columnService.createColumn(boardId, columnTitleDto);
    }

    @PutMapping("/{columnId}")
    public ColumnDto editColumn(@PathVariable Integer columnId,
                                @PathVariable Integer boardId,
                                @RequestBody ColumnTitleDto columnTitleDto) {
        return columnService.editColumn(columnId, boardId, columnTitleDto);
    }

    @DeleteMapping("/{columnId}")
    public ColumnDto deleteColumn(@PathVariable Integer columnId,
                                  @PathVariable Integer boardId) {
        return columnService.deleteColumn(columnId, boardId);
    }
}
