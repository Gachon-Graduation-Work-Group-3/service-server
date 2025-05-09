package howmuchcar.application.service.user

import howmuchcar.application.converter.user.UserAuthConverter
import howmuchcar.application.dto.user.UserRefreshResponse
import howmuchcar.application.port.`in`.user.UserAuthUseCase
import howmuchcar.application.port.`in`.user.UserCommonUseCase
import howmuchcar.application.port.out.db.user.UserPort
import howmuchcar.common.auth.JwtProvider
import howmuchcar.common.exception.RestApiException
import howmuchcar.common.status.UserErrorStatus
import howmuchcar.domain.entity.User
import jakarta.servlet.http.HttpServletRequest
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class UserAuthService(
    private val jwtProvider: JwtProvider,
    private val userAuthConverter: UserAuthConverter
) : UserAuthUseCase {

    override fun refreshUserToken(email: String): UserRefreshResponse {
        // 토큰 생성
        val accessToken = jwtProvider.generateAccessToken(email)
        val refreshToken = jwtProvider.generateRefreshToken(email)

        // Refresh token 저장
        jwtProvider.saveRefreshToken(email, refreshToken)

        return  userAuthConverter.toUserRefreshResponse(accessToken, refreshToken)
    }

    override fun resolveToken(httpServletRequest: HttpServletRequest): String {
        val token  = jwtProvider.resolveToken(httpServletRequest)
            ?: throw RestApiException(UserErrorStatus.NOT_EXIST_USER)
        val email = jwtProvider.extractRefreshTokenToUserId(token)
        return email
    }
}