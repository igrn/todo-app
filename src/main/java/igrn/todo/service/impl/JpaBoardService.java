package igrn.todo.service.impl;

import igrn.todo.dto.BoardDto;
import igrn.todo.dto.BoardShortDto;
import igrn.todo.dto.BoardTitleDto;
import igrn.todo.entity.Board;
import igrn.todo.repository.BoardRepository;
import igrn.todo.repository.UserRepository;
import igrn.todo.service.BoardService;
import igrn.todo.service.context.UserContext;
import igrn.todo.service.factory.BoardFactory;
import igrn.todo.service.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaBoardService implements BoardService {
    private final UserContext userContext;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;
    private final BoardFactory boardFactory;

    public JpaBoardService(UserContext userContext,
                           UserRepository userRepository,
                           BoardRepository boardRepository,
                           BoardMapper boardMapper,
                           BoardFactory boardFactory) {
        this.userContext = userContext;
        this.userRepository = userRepository;
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
        String email = userContext.getEmail();
        Integer userId = userRepository.findByEmail(email).orElseThrow().getId();
        Board board = boardFactory.build(userId, boardTitleDto.getTitle());
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
