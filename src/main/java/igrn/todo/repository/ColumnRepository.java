package igrn.todo.repository;

import igrn.todo.entity.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColumnRepository extends JpaRepository<Column, Integer> {

    List<Column> findAllByBoardId(Integer boardId);

    void deleteAllByBoardId(Integer boardId);
}
