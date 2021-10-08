package igrn.todo.service.factory;

import igrn.todo.entity.Board;
import org.springframework.stereotype.Component;

@Component
public class BoardFactory {

    public Board build(Integer userId, String title) {
        return new Board(userId, title);
    }
}
