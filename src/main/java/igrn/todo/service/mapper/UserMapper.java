package igrn.todo.service.mapper;

import igrn.todo.dto.user.UserWithRolesDto;
import igrn.todo.entity.Role;
import igrn.todo.entity.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserWithRolesDto toUserWithRolesDto(User user) {
        return new UserWithRolesDto(
                user.getId(),
                user.getEmail(),
                user.getRoles().stream().map(Role::getCode).collect(Collectors.toList())
        );
    }

    public List<UserWithRolesDto> toUserWithRolesDto(Collection<User> users) {
        return users.stream()
                .map(this::toUserWithRolesDto)
                .distinct()
                .collect(Collectors.toList());
    }
}
