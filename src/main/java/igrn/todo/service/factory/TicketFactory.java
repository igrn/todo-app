package igrn.todo.service.factory;

import igrn.todo.entity.Column;
import igrn.todo.entity.Ticket;
import igrn.todo.repository.ColumnRepository;
import org.springframework.stereotype.Component;

@Component
public class TicketFactory {
    private final ColumnRepository columnRepository;

    public TicketFactory(ColumnRepository columnRepository) {
        this.columnRepository = columnRepository;
    }

    public Ticket build(Integer columnId, Integer boardId, String title) {
        Column column = columnRepository.findByIdAndBoard_Id(columnId, boardId).orElseThrow();
        return new Ticket(title, column);
    }
}
