package howmuchcar.common.handler

import com.fasterxml.jackson.databind.ObjectMapper
import howmuchcar.common.auth.JwtProvider
import howmuchcar.domain.entity.User
import howmuchcar.domain.enums.Role
import howmuchcar.domain.repository.user.UserCommonRepository
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class OAuth2SuccessHandler(
    private val jwtProvider: JwtProvider,
    private val userCommonRepository: UserCommonRepository
): AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        val oAuth2User = authentication!!.principal as OAuth2User
        val email: String = oAuth2User.getAttribute<String?>("email")!!.toString()

        if(!userCommonRepository.existsUserByEmail(email)) {
            val user = User.builder()
                .name(oAuth2User.getAttribute("name"))
                .email(email)
                .picture(oAuth2User.getAttribute("picture"))
                .role(Role.USER)
                .build()
            userCommonRepository.save(user)
        }

        // 토큰 생성
        val accessToken = jwtProvider.generateAccessToken(email)
        val refreshToken = jwtProvider.generateRefreshToken(email)

        // Refresh token 저장
        jwtProvider.saveRefreshToken(email, refreshToken)

        // 응답 상태 설정 및 리디렉션
        response!!.status = HttpStatus.FOUND.value()
        val responseBody = mapOf(
            "accessToken" to accessToken,
            "refreshToken" to refreshToken
        )

        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = "UTF-8"
        response.writer.write(
            ObjectMapper().writeValueAsString(responseBody)
        )
        response.flushBuffer()
    }

    private fun createCookie(name: String, value: String, maxAge: Int) = Cookie(name, value).apply {
        isHttpOnly = true
        path = "/"
        setMaxAge(maxAge)
        secure = true
        domain = "localhost" // 배포 시 도메인으로 변경
        setAttribute("SameSite", "None") // SameSite 설정
    }
}