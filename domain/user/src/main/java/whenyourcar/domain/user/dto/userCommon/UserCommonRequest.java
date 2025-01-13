package whenyourcar.domain.user.dto.userCommon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import whenyourcar.storage.mysql.data.enums.Role;

public class UserCommonRequest {
    @Builder
    @Getter
    @AllArgsConstructor
    public static class SignUpDto{
        String token;
        String name;
        Role role;
    }
}
