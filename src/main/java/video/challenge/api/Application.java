package video.challenge.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
