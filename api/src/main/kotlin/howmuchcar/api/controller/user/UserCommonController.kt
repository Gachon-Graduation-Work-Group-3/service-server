package howmuchcar.api.controller.user

import howmuchcar.api.security.annotation.CurrentUser
import howmuchcar.application.dto.user.UserProfileResponse
import howmuchcar.application.facade.user.UserCommonFacade
import howmuchcar.common.apiPayload.ApiResponse
import howmuchcar.domain.entity.User
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
class UserCommonController (
    private val userCommonFacade: UserCommonFacade
){
    @GetMapping("/profile")
    fun getUserProfile(@CurrentUser user: User) : ApiResponse<UserProfileResponse> {
        return ApiResponse.onSuccess(userCommonFacade.getUserProfile(user))
    }
}