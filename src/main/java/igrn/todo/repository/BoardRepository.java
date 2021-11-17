package igrn.todo.repository;

import igrn.todo.entity.Board;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

    @EntityGraph("Board.columns")
    Optional<Board> findOneById(Integer boardId);
}
