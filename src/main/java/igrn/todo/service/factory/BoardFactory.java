package igrn.todo.service.factory;

import igrn.todo.entity.Board;
import org.springframework.stereotype.Component;

@Component
public class BoardFactory {

    public Board build(String title, Integer userId) {
        return new Board(title, userId);
    }
}
