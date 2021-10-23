package igrn.todo.dto.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserWithRolesDto {

    @EqualsAndHashCode.Include //TODO: зачем это тут?
    private final Integer id;

    private final String email;
    private final List<String> codes;

    public UserWithRolesDto(Integer id, String email, List<String> codes) {
        this.id = id;
        this.email = email;
        this.codes = codes;
    }
}
