package howmuchcar.common.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(): OpenAPI {
        val info = Info()
            .title("얼마카 API 명세서")
            .description("얼마카 API 명세서 입니다")
            .version("1.1.0")

        val jwtSchemeName = "Authorization"
        val securityRequirement = SecurityRequirement().addList(jwtSchemeName)

        val component = Components()
            .addSecuritySchemes(jwtSchemeName, SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("Bearer"))

        return OpenAPI()
            .addSecurityItem(securityRequirement)
            .components(component)
            .info(info)
    }
}