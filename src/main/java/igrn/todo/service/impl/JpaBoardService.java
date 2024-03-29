package igrn.todo.service.impl;

import igrn.todo.dto.board.BoardDto;
import igrn.todo.dto.board.BoardShortDto;
import igrn.todo.dto.board.BoardTitleDto;
import igrn.todo.entity.Board;
import igrn.todo.exception.BoardNotFoundException;
import igrn.todo.exception.UserNotFoundException;
import igrn.todo.repository.BoardRepository;
import igrn.todo.repository.UserRepository;
import igrn.todo.service.BoardService;
import igrn.todo.service.context.UserContext;
import igrn.todo.service.factory.BoardFactory;
import igrn.todo.service.mapper.BoardMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public List<BoardShortDto> getUserBoardsBaseInfo() {
        List<Board> boards = boardRepository.findAll();
        return boardMapper.toBoardShortDto(boards);
    }

    @Transactional
    @Override
    public BoardDto getBoard(Integer boardId) {
        Board board = boardRepository.findOneById(boardId)
                .orElseThrow(BoardNotFoundException::build);

        return boardMapper.toBoardDto(board);
    }

    @Transactional
    @Override
    public BoardShortDto createBoard(BoardTitleDto boardTitleDto) {
        String email = userContext.getEmail();
        Integer userId = userRepository.findByEmail(email).orElseThrow(() ->
                UserNotFoundException.buildWith(email)).getId();

        Board board = boardFactory.build(boardTitleDto.getTitle(), userId);
        board = boardRepository.save(board);
        return boardMapper.toBoardShortDto(board);
    }

    @Transactional
    @Override
    public BoardShortDto editBoard(Integer boardId, BoardTitleDto boardTitleDto) {
        Board board = boardRepository.findOneById(boardId)
                .orElseThrow(BoardNotFoundException::build);

        board.setTitle(boardTitleDto.getTitle());
        board = boardRepository.save(board);
        return boardMapper.toBoardShortDto(board);
    }

    @Transactional
    @Override
    public BoardShortDto deleteBoard(Integer boardId) {
        Board board = boardRepository.findOneById(boardId)
                .orElseThrow(BoardNotFoundException::build);

        boardRepository.delete(board);
        return boardMapper.toBoardShortDto(board);
    }
}
