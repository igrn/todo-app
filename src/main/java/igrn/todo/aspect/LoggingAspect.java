package igrn.todo.aspect;

import igrn.todo.annotation.Loggable;
import igrn.todo.service.context.UserContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private final Logger logger = LogManager.getLogger();

    public LoggingAspect(UserContext userContext) {
        this.userContext = userContext;
    }

    @After("@annotation(loggable)")
    public void loggable(JoinPoint joinPoint, Loggable loggable) {
        String email = userContext.getEmail();
        logger.info(String.format("[email = %s] Executed successfully: %s",
                email, joinPoint.getSignature().getName()));
    }
}
