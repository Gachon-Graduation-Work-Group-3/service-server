package howmuchcar.application.port.`in`.user

import howmuchcar.application.dto.user.UserRefreshResponse
import howmuchcar.domain.entity.User
import jakarta.servlet.http.HttpServletRequest

interface UserAuthUseCase {
    fun refreshUserToken(email: String): UserRefreshResponse
    fun resolveToken(httpServletRequest: HttpServletRequest): String
}