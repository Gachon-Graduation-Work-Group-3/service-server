package whenyourcar.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class UserResponse {
    @Builder
    @Getter
    @AllArgsConstructor
    public static class AuthResponseDto{
        private String email;
        private String accessToken;
        private String refreshToken;
        private LocalDateTime expiration;
    }
}
