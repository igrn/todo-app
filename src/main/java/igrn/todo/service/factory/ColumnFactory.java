package igrn.todo.service.factory;

import igrn.todo.entity.Column;
import org.springframework.stereotype.Component;

@Component
public class ColumnFactory {

    public Column build(String title, Integer boardId) {
        return new Column(title, boardId);
    }
}
