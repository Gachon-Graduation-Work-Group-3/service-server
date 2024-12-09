package whenyourcar.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UserRequest {
    @Builder
    @Getter
    @AllArgsConstructor
    public static class SignUpDto{
        String token;
        String name;
    }
}
