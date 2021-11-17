package igrn.todo.dto.user.auth;

import lombok.Getter;

import java.util.Set;

@Getter
public class UserAuthInfoDto {
    private final Integer id;
    private final String email;
    private final String password;
    private final Set<String> roleCodes;

    public UserAuthInfoDto(Integer id, String email,
                           String password, Set<String> roleCodes) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roleCodes = roleCodes;
    }
}
