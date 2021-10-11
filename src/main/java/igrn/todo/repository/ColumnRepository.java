package igrn.todo.repository;

import igrn.todo.entity.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColumnRepository extends JpaRepository<Column, Integer> {

    Optional<Column> findByIdAndBoard_Id(Integer columnId, Integer boardId);
}
