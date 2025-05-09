package howmuchcar.api.controller.user

import howmuchcar.application.dto.user.UserRefreshResponse
import howmuchcar.application.facade.user.UserAuthFacade
import howmuchcar.common.apiPayload.ApiResponse
import jakarta.servlet.http.HttpServletRequest
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/token")
class UserAuthController(
    private val userAuthFacade: UserAuthFacade
) {
    @GetMapping("/renew")
    fun refreshUserToken(httpServletRequest: HttpServletRequest): ApiResponse<UserRefreshResponse> {
        return ApiResponse.onSuccess(userAuthFacade.refreshUserToken(httpServletRequest))
    }
}