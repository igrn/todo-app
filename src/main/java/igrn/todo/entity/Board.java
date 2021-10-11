package igrn.todo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
public class Board {
    @Id
    @SequenceGenerator(name = "board_id_seq_generator", sequenceName = "board_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_id_seq_generator")
    private Integer id;

    private Integer userId;
    private String title;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "board")
    private List<Column> columns;

    public Board() {}

    public Board(Integer userId, String title) {
        this.userId = userId;
        this.title = title;
    }
}
