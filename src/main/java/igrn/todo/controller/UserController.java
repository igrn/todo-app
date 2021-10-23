package igrn.todo.controller;

import igrn.todo.dto.user.UserWithRolesDto;
import igrn.todo.dto.user.filter.UserFilterDto;
import igrn.todo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserWithRolesDto> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public List<UserWithRolesDto> getUsers(@RequestBody Collection<UserFilterDto> filters) {
        return userService.getUsers(filters);
    }

    @PostMapping("/{email}/roles")
    public void editRoles(@PathVariable String email,
                          @RequestBody Collection<String> roleCodes) {
        Integer userId = userService.getId(email);
        userService.editRole(userId, roleCodes);
    }
}
