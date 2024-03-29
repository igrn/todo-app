package igrn.todo.repository;

import igrn.todo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByCode(String code);

    Set<Role> findAllByCodeIn(Collection<String> codes);
}
