package howmuchcar.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(scanBasePackages = ["howmuchcar"])
@EnableJpaRepositories(basePackages = ["howmuchcar.infra.persistence"])
@EnableJpaAuditing
@ConfigurationPropertiesScan
@EntityScan(basePackages = ["howmuchcar.domain.entity"])
class HowMuchCarApplication

fun main(args: Array<String>) {
    runApplication<HowMuchCarApplication>(*args)
}