package igrn.todo.service.context.impl;

import igrn.todo.service.context.UserContext;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class UserContextImpl implements UserContext {

    @Getter
    @Setter
    private String email = null;
}
