package igrn.todo.service.factory;

import igrn.todo.entity.Board;
import igrn.todo.entity.Column;
import igrn.todo.exception.BoardNotFoundException;
import igrn.todo.repository.BoardRepository;
import org.springframework.stereotype.Component;

@Component
public class ColumnFactory {
    private final BoardRepository boardRepository;

    public ColumnFactory(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    /**
     * @throws BoardNotFoundException if a Board with provided boardId was not found
     */
    public Column build(Integer boardId, String title) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BoardNotFoundException("Board not found"));
        return new Column(title, board);
    }
}
