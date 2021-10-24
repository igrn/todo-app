package igrn.todo.service.factory;

import igrn.todo.entity.Role;
import igrn.todo.entity.User;
import igrn.todo.enums.ExceptionMessage;
import igrn.todo.exception.RoleNotFoundException;
import igrn.todo.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserFactory {
    private final RoleRepository roleRepository;

    public UserFactory(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public User build(String email, String password,
                      String firstName, String lastName) {
        Set<Role> roles = Set.of(roleRepository.findByCode("user").orElseThrow(() ->
                new RoleNotFoundException(ExceptionMessage.ROLE_NOT_FOUND.getMessage())));
        return new User(email, password, firstName, lastName, roles);
    }
}
