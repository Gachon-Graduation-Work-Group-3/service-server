package howmuchcar.application.converter.user

import howmuchcar.application.dto.user.UserProfileResponse
import howmuchcar.application.dto.user.UserRefreshResponse
import howmuchcar.domain.entity.User
import org.springframework.stereotype.Component

@Component
class UserAuthConverter {
    fun toUserRefreshResponse(accessToken:String, refreshToken:String): UserRefreshResponse{
        return UserRefreshResponse(
            accessToken, refreshToken
        )
    }
}