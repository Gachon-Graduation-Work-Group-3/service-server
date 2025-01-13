package whenyourcar.presentation.controller.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import whenyourcar.domain.common.code.status.SuccessStatus;
import whenyourcar.domain.user.dto.security.SessionUser;
import whenyourcar.presentation.apiPayload.ApiResponse;
import whenyourcar.presentation.facade.user.UserCommonFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserCommonController {
    private final HttpSession httpSession;
    private final UserCommonFacade userCommonFacade;

    @GetMapping("/profile")
    public ApiResponse<SessionUser> getProfile(){
        return ApiResponse.onSuccess(SuccessStatus.USER_PROFILE_SUCCESS, userCommonFacade.getUserProfile(httpSession));
    }
}
