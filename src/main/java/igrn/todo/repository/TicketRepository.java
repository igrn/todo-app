package igrn.todo.repository;

import igrn.todo.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    List<Ticket> findAllByColumnId(Integer columnId);

    void deleteAllByColumnId(Integer columnId);
}
