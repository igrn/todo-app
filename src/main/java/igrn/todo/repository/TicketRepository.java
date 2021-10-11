package igrn.todo.repository;

import igrn.todo.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    Optional<Ticket> findByIdAndColumn_IdAndColumn_Board_Id(Integer ticketId,
                                                            Integer columnId,
                                                            Integer boardId);
}
