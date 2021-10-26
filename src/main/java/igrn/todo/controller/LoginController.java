package igrn.todo.controller;

import igrn.todo.annotation.Loggable;
import igrn.todo.dto.user.UserWithRolesDto;
import igrn.todo.dto.user.auth.UserCreateDto;
import igrn.todo.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @Loggable
    @PostMapping("/signup")
    public UserWithRolesDto createUser(@RequestBody UserCreateDto userCreateDto) {
        return userService.createUser(userCreateDto);
    }
}
