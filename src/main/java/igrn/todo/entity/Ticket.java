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
public class Ticket {

    @Id
    @SequenceGenerator(name = "ticket_id_seq_generator", sequenceName = "ticket_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_id_seq_generator")
    private Integer id;

    private String title;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    //TODO: добавить внешний ключ?

    @ManyToOne
    @JoinColumn(name = "column_id", nullable = false)
    private Column column;

    public Ticket() {}

    public Ticket(String title, Column column) {
        this.title = title;
        this.column = column;
    }
}
