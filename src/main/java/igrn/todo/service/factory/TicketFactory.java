package igrn.todo.service.factory;

import igrn.todo.entity.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketFactory {

    public Ticket build(String title, Integer columnId) {
        return new Ticket(title, columnId);
    }
}
