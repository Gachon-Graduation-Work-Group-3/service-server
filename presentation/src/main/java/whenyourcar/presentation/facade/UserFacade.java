package whenyourcar.presentation.facade;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whenyourcar.domain.user.converter.UserConverter;
import whenyourcar.domain.auth.dto.TokenInfo;
import whenyourcar.domain.user.dto.UserRequest;
import whenyourcar.domain.user.dto.UserResponse;
import whenyourcar.domain.auth.service.AuthService;
import whenyourcar.domain.user.service.UserService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserFacade {
    private final AuthService authService;
    private final UserService userService;

    private final UserConverter userConverter;

    /*public UserResponse.AuthResponseDto signup(UserRequest.SignUpDto signUpDto) {
        String email = authService.verifySocialToken(signUpDto.getToken());
        userService.signup(signUpDto, email);
        TokenInfo tokenInfo = authService.generateToken(email);

        Date date = authService.getExpiration(tokenInfo.getAccessToken());
        LocalDateTime expiration = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

        return userConverter.toUserAuthResponse(email, tokenInfo, expiration);
    }

    public UserResponse.AuthResponseDto login() {
        String email = userService.getEmail();
        TokenInfo tokenInfo = authService.generateToken(email);

        Date date = authService.getExpiration(tokenInfo.getAccessToken());
        LocalDateTime expiration = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

        return userConverter.toUserAuthResponse(email, tokenInfo, expiration);
    }*/
}
