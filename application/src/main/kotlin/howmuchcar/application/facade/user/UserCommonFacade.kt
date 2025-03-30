package howmuchcar.application.facade.user

import howmuchcar.application.converter.user.UserCommonConverter
import howmuchcar.application.dto.user.UserProfileResponse
import howmuchcar.domain.entity.User
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class UserCommonFacade (
    private val userCommonConverter: UserCommonConverter
){
    fun getUserProfile(user: User) : UserProfileResponse {
        return  userCommonConverter.toUserProfileResponse(user)
    }
}