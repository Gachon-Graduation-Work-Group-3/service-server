package whenyourcar.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import whenyourcar.infra.objectStorage.properties.ObjectStorageProperties;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = {"whenyourcar"})
@EnableJpaRepositories(basePackages = "whenyourcar.domain.repository")
@EntityScan(basePackages = "whenyourcar/domain/entity")
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
