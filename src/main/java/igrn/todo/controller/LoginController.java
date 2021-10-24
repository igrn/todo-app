package igrn.todo.controller;

import igrn.todo.dto.user.UserWithRolesDto;
import igrn.todo.dto.user.auth.UserRegisterDto;
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

    @PostMapping("/signup")
    public UserWithRolesDto register(@RequestBody UserRegisterDto userRegisterDto) {
        return userService.createUser(userRegisterDto);
    }
}
