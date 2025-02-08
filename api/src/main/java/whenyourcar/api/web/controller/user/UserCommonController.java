package whenyourcar.api.web.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import whenyourcar.common.code.status.SuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import whenyourcar.api.apiPayload.ApiResponse;
import whenyourcar.application.dto.user.common.UserCommonResponse;
import whenyourcar.application.facade.user.UserCommonFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserCommonController {
    private final UserCommonFacade userCommonFacade;

    @GetMapping("/profile")
    public ApiResponse<UserCommonResponse.UserProfileResponseDto> getProfile(HttpServletRequest httpServletRequest)  {
        String email = httpServletRequest.getHeader("X-User-Email");
        return ApiResponse.onSuccess(SuccessStatus.USER_PROFILE_SUCCESS, userCommonFacade.getUserProfile(email));
    }
}
