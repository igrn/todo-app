package igrn.todo.service;

import igrn.todo.dto.user.UserWithRolesDto;
import igrn.todo.dto.user.filter.UserFilterDto;

import java.util.Collection;
import java.util.List;

public interface UserService {

    Integer getId(String email);

    List<UserWithRolesDto> getUsers();

    List<UserWithRolesDto> getUsers(Collection<UserFilterDto> filters);

    void editRole(Integer userId, Collection<String> roleCodes);
}
