package igrn.todo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
public class Column {
    @Id
    @SequenceGenerator(name = "column_id_seq_generator", sequenceName = "column_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "column_id_seq_generator")
    private Integer id;

    private Integer boardId;
    private String title;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    public Column() {}

    public Column(Integer boardId, String title) {
        this.boardId = boardId;
        this.title = title;
    }
}
