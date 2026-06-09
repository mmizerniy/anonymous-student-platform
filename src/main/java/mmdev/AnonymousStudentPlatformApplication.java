package mmdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AnonymousStudentPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnonymousStudentPlatformApplication.class, args);
    }

}
