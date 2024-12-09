package whenyourcar.domain.user.converter;

import org.springframework.stereotype.Component;
import whenyourcar.storage.mysql.data.entity.User;
import whenyourcar.domain.auth.dto.TokenInfo;
import whenyourcar.domain.user.dto.UserRequest;
import whenyourcar.domain.user.dto.UserResponse;

import java.time.LocalDateTime;

@Component
public class UserConverter {
    public User toUser(UserRequest.SignUpDto signUpDto, String email) {
        return User.builder()
                .email(email)
                .name(signUpDto.getName())
                .build();
    }

    public UserResponse.AuthResponseDto toUserAuthResponse(String email, TokenInfo tokenInfo, LocalDateTime expiration) {
        return UserResponse.AuthResponseDto.builder()
                .email(email)
                .accessToken(tokenInfo.getAccessToken())
                .refreshToken(tokenInfo.getRefreshToken())
                .expiration(expiration)
                .build();
    }
}
