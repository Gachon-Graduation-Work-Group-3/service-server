package whenyourcar.domain.user.dto.userCommon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class UserCommonResponse {
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
