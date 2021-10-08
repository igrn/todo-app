package igrn.todo.service.factory;

import igrn.todo.entity.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketFactory {

    public Ticket build(Integer columnId, String title) {
        return new Ticket(columnId, title);
    }
}
