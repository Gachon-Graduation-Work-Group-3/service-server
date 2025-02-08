package whenyourcar.application.dto.user.common;


import whenyourcar.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UserCommonResponse {
    @Builder
    @Getter
    @AllArgsConstructor
    public static class UserProfileResponseDto{
        private Long userId;
        private String name;
        private String picture;
        private Role role;
    }
}
