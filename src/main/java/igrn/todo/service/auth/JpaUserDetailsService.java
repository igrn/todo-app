package igrn.todo.service.auth;

import igrn.todo.dto.user.auth.UserAuthInfoDto;
import igrn.todo.exception.UserNotFoundException;
import igrn.todo.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public JpaUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        UserAuthInfoDto userInfo = userService.findAuthInfo(username)
                .orElseThrow(() -> UserNotFoundException.buildWith(username));

        return User.builder()
                .username(userInfo.getEmail())
                .password(userInfo.getPassword())
                .roles(userInfo.getRoleCodes().toArray(String[]::new))
                .build();
    }
}
