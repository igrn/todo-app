package igrn.todo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
@Getter
@Setter
@NamedEntityGraphs({
        @NamedEntityGraph(name = "Column.tickets", attributeNodes = {
                @NamedAttributeNode(value = "tickets")
        })
})
public class Column {

    @Id
    @SequenceGenerator(name = "column_id_seq_generator", sequenceName = "column_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "column_id_seq_generator")
    private Integer id;

    private String title;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @javax.persistence.Column(name = "board_id", nullable = false)
    private Integer boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", insertable = false, updatable = false, nullable = false)
    private Board board;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "column")
    private Set<Ticket> tickets;

    public Column() {}

    public Column(String title, Integer boardId) {
        this.title = title;
        this.boardId = boardId;
    }
}
