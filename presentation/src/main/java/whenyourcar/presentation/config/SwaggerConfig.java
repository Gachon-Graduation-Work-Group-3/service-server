package whenyourcar.presentation.config;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {

        Info info = new Info()
                .title("When Your Car API")
                .description("When Your Car 명세서")
                .version("1.0.0");

        String sessionSchemeName = "Session";

        // SecurityRequirement는 API 요청 시 필요한 인증 스키마를 정의
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(sessionSchemeName);

        Components components = new Components()
                .addSecuritySchemes(sessionSchemeName, new SecurityScheme()
                        .name("JSESSIONID")
                        .type(SecurityScheme.Type.APIKEY)
                        .in(SecurityScheme.In.COOKIE));

        return new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}
