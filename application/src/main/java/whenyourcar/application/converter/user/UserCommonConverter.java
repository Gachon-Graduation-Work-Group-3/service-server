package whenyourcar.application.converter.user;

import whenyourcar.domain.entity.User;
import org.springframework.stereotype.Component;
import whenyourcar.application.dto.user.common.UserCommonResponse;

@Component
public class UserCommonConverter {
    public UserCommonResponse.UserProfileResponseDto toUserProfileResponse(User user){
        return UserCommonResponse.UserProfileResponseDto.builder()
                .userId(user.getId())
                .name(user.getName())
                .picture(user.getPicture())
                .role(user.getRole())
                .build();
    }

}
