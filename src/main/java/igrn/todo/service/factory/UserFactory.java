package igrn.todo.service.factory;

import igrn.todo.entity.Role;
import igrn.todo.entity.User;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UserFactory {

    public User build(String email, String password, String firstName,
                      String lastName, Collection<Role> roles) {
        return new User(email, password, firstName, lastName, roles);
    }
}
