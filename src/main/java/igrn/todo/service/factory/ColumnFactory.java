package igrn.todo.service.factory;

import igrn.todo.entity.Board;
import igrn.todo.entity.Column;
import igrn.todo.repository.BoardRepository;
import org.springframework.stereotype.Component;

@Component
public class ColumnFactory {
    private final BoardRepository boardRepository;

    public ColumnFactory(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Column build(Integer boardId, String title) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        return new Column(title, board);
    }
}
