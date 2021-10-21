package igrn.todo.service;

import igrn.todo.dto.board.BoardDto;
import igrn.todo.dto.board.BoardShortDto;
import igrn.todo.dto.board.BoardTitleDto;

import java.util.List;

public interface BoardService {

    List<BoardShortDto> getUserBoardsBaseInfo();

    BoardDto getBoard(Integer boardId);

    BoardShortDto createBoard(BoardTitleDto boardTitleDto);

    BoardShortDto editBoard(Integer boardId, BoardTitleDto boardTitleDto);

    BoardShortDto deleteBoard(Integer boardId);
}
