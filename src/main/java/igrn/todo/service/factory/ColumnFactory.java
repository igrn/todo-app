package igrn.todo.service.factory;

import igrn.todo.entity.Column;
import org.springframework.stereotype.Component;

@Component
public class ColumnFactory {

    public Column build(Integer boardId, String title) {
        return new Column(boardId, title);
    }
}
