package whenyourcar.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.lang.reflect.Array;
import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = {"whenyourcar" })
@EnableJpaRepositories(basePackages = "whenyourcar.storage.mysql.repository")
@EntityScan(basePackages = "whenyourcar.storage.mysql.data")
@EnableJpaAuditing
public class WhenYourCarApplication implements CommandLineRunner {
    @Autowired
    private ApplicationContext applicationContext;
    public static void main(String[] args) {
        SpringApplication.run(WhenYourCarApplication.class, args);
    }

    @Override
    public void run(String ...arg) throws Exception {
        String[] beans = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beans);
        for (String bean : beans) {
            System.out.println(bean);
        }
    }
}
