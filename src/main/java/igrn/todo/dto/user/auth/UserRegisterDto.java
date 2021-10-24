package igrn.todo.dto.user.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UserRegisterDto {
    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;

    public UserRegisterDto(@JsonProperty("email") String email,
                           @JsonProperty("password") String password,
                           @JsonProperty("firstName") String firstName,
                           @JsonProperty("lastName") String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
