package igrn.todo.dto.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserWithRolesDto {

    @EqualsAndHashCode.Include
    private final Integer id;

    private final String email;
    private final List<String> roleCodes;

    public UserWithRolesDto(Integer id, String email, List<String> roleCodes) {
        this.id = id;
        this.email = email;
        this.roleCodes = roleCodes;
    }
}
