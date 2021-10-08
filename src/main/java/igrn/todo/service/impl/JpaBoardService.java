package igrn.todo.service.impl;

import igrn.todo.dto.BoardDto;
import igrn.todo.dto.BoardShortDto;
import igrn.todo.dto.BoardTitleDto;
import igrn.todo.entity.Board;
import igrn.todo.entity.Column;
import igrn.todo.entity.Ticket;
import igrn.todo.repository.BoardRepository;
import igrn.todo.repository.ColumnRepository;
import igrn.todo.repository.TicketRepository;
import igrn.todo.service.BoardService;
import igrn.todo.service.factory.BoardFactory;
import igrn.todo.service.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JpaBoardService implements BoardService {
    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;
    private final BoardFactory boardFactory;
    private final ColumnRepository columnRepository;
    private final TicketRepository ticketRepository;

    public JpaBoardService(BoardRepository boardRepository,
                           BoardMapper boardMapper,
                           BoardFactory boardFactory,
                           ColumnRepository columnRepository,
                           TicketRepository ticketRepository) {
        this.boardRepository = boardRepository;
        this.boardMapper = boardMapper;
        this.boardFactory = boardFactory;
        this.columnRepository = columnRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<BoardShortDto> getUserBoardsBaseInfo() {
        List<Board> boards = boardRepository.findAll();
        return boardMapper.toBoardShortDto(boards);
    }

    @Override
    public BoardDto getBoard(Integer boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        Map<Column, Collection<Ticket>> columns = new HashMap<>();
        columnRepository.findAllByBoardId(boardId).forEach(column -> {
            List<Ticket> tickets = ticketRepository.findAllByColumnId(column.getId());
            columns.put(column, tickets);
        });
        return boardMapper.toBoardDto(board, columns);
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
        List<Column> columns = columnRepository.findAllByBoardId(boardId);
        columns.forEach(column -> ticketRepository.deleteAllByColumnId(column.getId()));
        columnRepository.deleteAllByBoardId(boardId); //FIXME: Null не учитывается?
        boardRepository.delete(board);
        return boardMapper.toBoardShortDto(board);
    }
}
