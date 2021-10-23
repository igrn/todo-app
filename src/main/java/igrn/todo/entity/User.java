package igrn.todo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
@Getter
@Setter
@NamedEntityGraphs({
        @NamedEntityGraph(name = "User.roles", attributeNodes = {
                @NamedAttributeNode(value = "roles")
        })
})
public class User {

    @Id
    @SequenceGenerator(name = "user_id_seq_generator", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq_generator")
    private Integer id;

    private String firstName;
    private String lastName;
    private String email;

    @CreationTimestamp
    private Instant createdAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Board> boards;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;
}
