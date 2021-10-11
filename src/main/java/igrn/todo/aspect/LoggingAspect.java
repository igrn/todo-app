package igrn.todo.aspect;

import igrn.todo.annotation.Loggable;
import igrn.todo.service.context.UserContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class LoggingAspect {
    private final UserContext userContext;

    public LoggingAspect(UserContext userContext) {
        this.userContext = userContext;
    }

    @After("@annotation(loggable)")
    public void loggable(JoinPoint joinPoint, Loggable loggable) {
        String email = userContext.getEmail();
        System.out.printf("[email = %s] Hello, executed: %s\n",
                email, joinPoint.getSignature().getName()
        );
    }
}
