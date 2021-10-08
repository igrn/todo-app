package igrn.todo.controller;

import igrn.todo.dto.BoardDto;
import igrn.todo.dto.BoardShortDto;
import igrn.todo.dto.BoardTitleDto;
import igrn.todo.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public List<BoardShortDto> getUserBoardsBaseInfo() {
        return boardService.getUserBoardsBaseInfo();
    }

    @GetMapping("/{boardId}")
    public BoardDto getBoard(@PathVariable Integer boardId) {
        return boardService.getBoard(boardId);
    }

    @PostMapping
    public BoardShortDto createBoard(@RequestBody BoardTitleDto boardTitleDto) {
        return boardService.createBoard(boardTitleDto);
    }

    @PutMapping("/{boardId}")
    public BoardShortDto editBoard(@PathVariable Integer boardId,
                                   @RequestBody BoardTitleDto boardTitleDto) {
        return boardService.editBoard(boardId, boardTitleDto);
    }

    @DeleteMapping("/{boardId}")
    public BoardShortDto deleteBoard(@PathVariable Integer boardId) {
        return boardService.deleteBoard(boardId);
    }
}
