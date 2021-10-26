package igrn.todo.service.impl;

import igrn.todo.dto.user.UserWithRolesDto;
import igrn.todo.dto.user.auth.UserAuthInfoDto;
import igrn.todo.dto.user.auth.UserCreateDto;
import igrn.todo.dto.user.filter.UserFilterDto;
import igrn.todo.entity.Role;
import igrn.todo.entity.User;
import igrn.todo.exception.RoleNotFoundException;
import igrn.todo.exception.UserAlreadyExistsException;
import igrn.todo.exception.UserNotFoundException;
import igrn.todo.repository.RoleRepository;
import igrn.todo.repository.UserRepository;
import igrn.todo.repository.specification.UserSpecification;
import igrn.todo.service.UserService;
import igrn.todo.service.factory.UserFactory;
import igrn.todo.service.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JpaUserService implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final UserFactory userFactory;

    public JpaUserService(UserRepository userRepository,
                          RoleRepository roleRepository,
                          UserMapper userMapper,
                          UserFactory userFactory) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.userFactory = userFactory;
    }

    @Transactional
    @Override
    public Integer getId(String email) {
        try {
            return userRepository.findOneByEmail(email).getId();
        } catch (NullPointerException e) {
            throw UserNotFoundException.buildWith(email);
        }
    }

    @Transactional
    @Override
    public List<UserWithRolesDto> getUsers() {
        List<User> users = userRepository.findAllWithRoles();
        return userMapper.toUserWithRolesDto(users);
    }

    @Transactional
    @Override
    public List<UserWithRolesDto> getUsers(Collection<UserFilterDto> filters) {
        List<User> users = userRepository.findAll(UserSpecification.findUsers(filters));
        return userMapper.toUserWithRolesDto(users);
    }

    @Transactional
    @Override
    public void editRoles(Integer userId, String email, Collection<String> roleCodes) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.buildWith(email));

        Set<Role> newRoles = roleRepository.findAllByCodeIn(roleCodes);
        user.setRoles(newRoles);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public Optional<UserAuthInfoDto> findAuthInfo(String email) {
        Optional<User> userOpt = userRepository.findOneWithRolesByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return Optional.of(new UserAuthInfoDto(
                    user.getId(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getRoles().stream().map(Role::getCode).collect(Collectors.toSet())
            ));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public UserWithRolesDto createUser(UserCreateDto userCreateDto) {
        String email = userCreateDto.getEmail();
        String password = userCreateDto.getPassword();
        String firstName = userCreateDto.getFirstName();
        String lastName = userCreateDto.getLastName();
        Set<Role> roles = Set.of(roleRepository.findByCode("user")
                .orElseThrow(RoleNotFoundException::build));

        if (!userRepository.existsByEmail(email)) {
            User user = userFactory.build(email, password, firstName, lastName, roles);
            user = userRepository.save(user);
            return userMapper.toUserWithRolesDto(user);
        } else {
            throw UserAlreadyExistsException.buildWith(email);
        }
    }
}
