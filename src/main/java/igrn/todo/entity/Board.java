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
        @NamedEntityGraph(name = "Board.columns", attributeNodes = {
                @NamedAttributeNode(value = "columns", subgraph = "Column.tickets")
        }, subgraphs = {
                @NamedSubgraph(name = "Column.tickets", attributeNodes = {
                        @NamedAttributeNode(value = "tickets")
                })
        })
})
public class Board {

    @Id
    @SequenceGenerator(name = "board_id_seq_generator", sequenceName = "board_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_id_seq_generator")
    private Integer id;

    private String title;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @javax.persistence.Column(name = "user_id", nullable = false)
    private Integer userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false, nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "board")
    private Set<Column> columns;

    public Board() {}

    public Board(String title, Integer userId) {
        this.title = title;
        this.userId = userId;
    }
}
