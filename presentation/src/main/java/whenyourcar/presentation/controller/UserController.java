package whenyourcar.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import whenyourcar.domain.common.code.status.SuccessStatus;
import whenyourcar.domain.user.dto.SessionUser;
import whenyourcar.domain.user.dto.UserRequest;
import whenyourcar.domain.user.dto.UserResponse;
import whenyourcar.presentation.apiPayload.ApiResponse;
import whenyourcar.presentation.facade.UserFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserFacade userFacade;
    private final HttpSession httpSession;

    /*@PostMapping("/signup")
    public ApiResponse<UserResponse.AuthResponseDto> signup(
            @RequestBody UserRequest.SignUpDto signUpDto)
    {
        return ApiResponse.onSuccess(SuccessStatus.USER_SIGNUP_SUCCESS, userFacade.signup(signUpDto));
    }

    @GetMapping("/login")
    public ApiResponse<UserResponse.AuthResponseDto> login()
    {
        return ApiResponse.onSuccess(SuccessStatus.USER_LOGIN_SUCCESS, userFacade.login());
    }*/

    @GetMapping("/profile")
    public SessionUser demo(){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user == null) {
            throw new RuntimeException("User not logged in");
        }
        return user;    }
}
