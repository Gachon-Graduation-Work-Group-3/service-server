package howmuchcar.application.converter.user

import howmuchcar.application.dto.user.UserProfileResponse
import howmuchcar.domain.entity.User
import org.springframework.stereotype.Component

@Component
class UserCommonConverter {
    fun toUserProfileResponse(user: User): UserProfileResponse {
        return UserProfileResponse(
            userId = user.id,
            name = user.name,
            picture = user.picture,
            email = user.email
        )
    }
}