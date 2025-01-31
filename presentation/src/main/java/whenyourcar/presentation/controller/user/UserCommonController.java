package whenyourcar.presentation.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import whenyourcar.domain.common.code.status.SuccessStatus;
import whenyourcar.domain.user.dto.security.SessionUser;
import whenyourcar.presentation.apiPayload.ApiResponse;
import whenyourcar.presentation.facade.user.UserCommonFacade;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserCommonController {
    private final UserCommonFacade userCommonFacade;

    @GetMapping("/profile")
    public ApiResponse<SessionUser> getProfile(HttpServletRequest request)  {
        return ApiResponse.onSuccess(SuccessStatus.USER_PROFILE_SUCCESS, userCommonFacade.getUserProfile(request));
    }
}
