package igrn.todo.service;

import igrn.todo.dto.user.UserWithRolesDto;
import igrn.todo.dto.user.auth.UserAuthInfoDto;
import igrn.todo.dto.user.auth.UserCreateDto;
import igrn.todo.dto.user.filter.UserFilterDto;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Integer getId(String email);

    List<UserWithRolesDto> getUsers();

    List<UserWithRolesDto> getUsers(Collection<UserFilterDto> filters);

    void editRoles(Integer userId, String email, Collection<String> roleCodes);

    Optional<UserAuthInfoDto> findAuthInfo(String email);

    UserWithRolesDto createUser(UserCreateDto userCreateDto);
}
