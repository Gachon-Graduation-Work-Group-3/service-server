package howmuchcar.api.controller.user

import howmuchcar.application.dto.user.UserLikeResponse
import howmuchcar.application.facade.user.UserLikeFacade
import howmuchcar.common.apiPayload.ApiResponse
import howmuchcar.common.auth.CurrentUser
import howmuchcar.domain.entity.User
import jakarta.servlet.http.HttpServletRequest
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
class UserLikeController (
    private val userLikeFacade: UserLikeFacade
){
    @PostMapping("/like")
    fun postUserLike(
        @CurrentUser user: User,
        @RequestParam carId: Long
    ): ApiResponse<Void> {
        userLikeFacade.postUserLike(user, carId)
        return ApiResponse.onSuccess( null)
    }

    @GetMapping("/like")
    fun getUserLike(
        @CurrentUser user: User,
        @RequestParam page: Int,
        @RequestParam size: Int
    ): ApiResponse<UserLikeResponse> {
        return ApiResponse.onSuccess(
            userLikeFacade.getUserLikes(PageRequest.of(
                    page,
                    size
                ), user
            )
        )
    }

    @DeleteMapping("/like")
    fun deleteLike(
        @CurrentUser user: User,
        @RequestParam userLikeId: Long
    ): ApiResponse<Void> {
        userLikeFacade.deleteUserLike(user, userLikeId)
        return ApiResponse.onSuccess( null)
    }

}