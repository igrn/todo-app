package todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TodoApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(TodoApplication.class);
    }
}
