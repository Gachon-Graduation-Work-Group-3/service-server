package howmuchcar.api

import howmuchcar.infra.properties.ObjectStorageProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(scanBasePackages = ["howmuchcar"])
@EnableJpaRepositories(basePackages = ["howmuchcar.domain.repository"])
@EnableJpaAuditing
@ConfigurationPropertiesScan
@EntityScan(basePackages = ["howmuchcar.domain.entity"])
class HowMuchCarApplication

fun main(args: Array<String>) {
    runApplication<HowMuchCarApplication>(*args)
}