package igrn.todo.repository;

import igrn.todo.entity.User;
import igrn.todo.entity.projection.UserIdProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends
        JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    Optional<User> findByEmail(String email); //TODO: заменить этот метод на проекцию?

    UserIdProjection findOneByEmail(String email);

    @EntityGraph("User.roles")
    Optional<User> findOneWithRolesByEmail(String email);

    @EntityGraph("User.roles")
    @Query("select u from User u")
    List<User> findAllWithRoles();
}
