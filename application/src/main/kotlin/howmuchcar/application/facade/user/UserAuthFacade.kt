package howmuchcar.application.facade.user

import howmuchcar.application.converter.user.UserCommonConverter
import howmuchcar.application.dto.user.UserProfileResponse
import howmuchcar.application.dto.user.UserRefreshResponse
import howmuchcar.application.port.`in`.user.UserAuthUseCase
import howmuchcar.domain.entity.User
import jakarta.servlet.http.HttpServletRequest
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class UserAuthFacade (
    private val userAuthUseCase: UserAuthUseCase
){
    fun refreshUserToken(httpServletRequest: HttpServletRequest) : UserRefreshResponse {
        val userId = userAuthUseCase.resolveToken(httpServletRequest)
        return  userAuthUseCase.refreshUserToken(userId)
    }
}