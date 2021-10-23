package igrn.todo.service.impl;

import igrn.todo.dto.user.UserWithRolesDto;
import igrn.todo.dto.user.filter.UserFilterDto;
import igrn.todo.entity.Role;
import igrn.todo.entity.User;
import igrn.todo.exception.UserNotFoundException;
import igrn.todo.repository.RoleRepository;
import igrn.todo.repository.UserRepository;
import igrn.todo.repository.specification.UserSpecification;
import igrn.todo.service.UserService;
import igrn.todo.service.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class JpaUserService implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public JpaUserService(UserRepository userRepository,
                          RoleRepository roleRepository,
                          UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Integer getId(String email) {
        return userRepository.findOneByEmail(email).getId();
    }

    @Override
    public List<UserWithRolesDto> getUsers() {
        List<User> users = userRepository.findAllWithRoles();
        return userMapper.toUserWithRolesDto(users);
    }

    @Override
    public List<UserWithRolesDto> getUsers(Collection<UserFilterDto> filters) {
        List<User> users = userRepository.findAll(UserSpecification.findUsers(filters));
        return userMapper.toUserWithRolesDto(users);
    }

    @Transactional
    @Override
    public void editRole(Integer userId, Collection<String> roleCodes) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Set<Role> newRoles = roleRepository.findAllByCodeIn(roleCodes);
        user.setRoles(newRoles);
        userRepository.save(user);
    }
}
