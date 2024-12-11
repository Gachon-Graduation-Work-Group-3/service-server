package whenyourcar.presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"whenyourcar.domain", "whenyourcar.presentation", "whenyourcar.storage.mysql" })
@EnableJpaRepositories(basePackages = "whenyourcar.storage.mysql.repository")
@EntityScan(basePackages = "whenyourcar.storage.mysql.data")
@EnableJpaAuditing
public class WhenYourCarApplication {
    public static void main(String[] args) {
        SpringApplication.run(WhenYourCarApplication.class, args);
    }
}
