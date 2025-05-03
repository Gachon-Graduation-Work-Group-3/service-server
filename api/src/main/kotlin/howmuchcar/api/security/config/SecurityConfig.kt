package howmuchcar.api.security.config

import howmuchcar.api.security.filter.JwtAuthenticationFilter
import howmuchcar.api.security.handler.OAuth2SuccessHandler
import howmuchcar.common.code.status.AuthErrorStatus
import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.time.LocalDateTime

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
class SecurityConfig (
    private val oAuth2SuccessHandler: OAuth2SuccessHandler,
    private val jwtAuthenticationFilter: JwtAuthenticationFilter
){
    @Bean
    fun configure(http: HttpSecurity): DefaultSecurityFilterChain {
        return http
            .cors { cors -> cors.configurationSource(corsWebFilter()) }
            .csrf { csrf -> csrf.disable() }
            .sessionManagement{session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)}
            .httpBasic { http -> http.disable() }
            .formLogin { login -> login.disable() }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/oauth2/authorization/google").permitAll()
                    .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui/index.html", "/webjars/**").permitAll()
                    .requestMatchers("/api/car/**").permitAll()
                    .requestMatchers("/ws/chat/**").permitAll()
                    .requestMatchers("/api/car/sale/article", "/api/car/sale/completed").authenticated()
                    .anyRequest().authenticated()
            }
            .oauth2Login { oauth2 ->
                oauth2.successHandler(oAuth2SuccessHandler)
            }
            .exceptionHandling{ ex ->
                ex.authenticationEntryPoint(authenticationEntryPoint())
            }
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }


    @Bean
    fun corsWebFilter(): CorsConfigurationSource {
        val config = CorsConfiguration().apply {
            allowCredentials = true
            allowedOrigins = listOf("http://localhost:3000", "http://localhost:3001") // 허용할 도메인 설정
            allowedHeaders = listOf("*")
            allowedMethods = listOf("*")
        }

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config)

        return source
    }

    @Bean
    fun authenticationEntryPoint(): AuthenticationEntryPoint {
        return AuthenticationEntryPoint { _, response, _ ->
            response.apply {
                status = AuthErrorStatus.INVALID_ACCESS_TOKEN.getCode().httpStatus.value()
                contentType = MediaType.APPLICATION_JSON_VALUE
                characterEncoding = "UTF-8"
                writer.write(
                    String.format(
                        "{\"timestamp\": \"%s\", \"code\": \"%s\", \"message\": \"%s\"}",
                        LocalDateTime.now(),
                        AuthErrorStatus.INVALID_ACCESS_TOKEN.getCode().code,
                        AuthErrorStatus.INVALID_ACCESS_TOKEN.getCode().message
                    )
                )
            }
        }
    }


}