package igrn.todo.service.impl;

import igrn.todo.dto.BoardDto;
import igrn.todo.dto.BoardShortDto;
import igrn.todo.dto.BoardTitleDto;
import igrn.todo.entity.Board;
import igrn.todo.repository.BoardRepository;
import igrn.todo.service.BoardService;
import igrn.todo.service.factory.BoardFactory;
import igrn.todo.service.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaBoardService implements BoardService {
    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;
    private final BoardFactory boardFactory;

    public JpaBoardService(BoardRepository boardRepository,
                           BoardMapper boardMapper,
                           BoardFactory boardFactory) {
        this.boardRepository = boardRepository;
        this.boardMapper = boardMapper;
        this.boardFactory = boardFactory;
    }

    @Override
    public List<BoardShortDto> getUserBoardsBaseInfo() {
        List<Board> boards = boardRepository.findAll();
        return boardMapper.toBoardShortDto(boards);
    }

    @Override
    public BoardDto getBoard(Integer boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        return boardMapper.toBoardDto(board);
    }

    @Override
    public BoardShortDto createBoard(BoardTitleDto boardTitleDto) {
        Board board = boardFactory.build(1, boardTitleDto.getTitle());
        boardRepository.saveAndFlush(board);
        return boardMapper.toBoardShortDto(board);
    }

    @Override
    public BoardShortDto editBoard(Integer boardId, BoardTitleDto boardTitleDto) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        board.setTitle(boardTitleDto.getTitle());
        boardRepository.saveAndFlush(board);
        return boardMapper.toBoardShortDto(board);
    }

    @Override
    public BoardShortDto deleteBoard(Integer boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        boardRepository.delete(board);
        return boardMapper.toBoardShortDto(board);
    }
}
