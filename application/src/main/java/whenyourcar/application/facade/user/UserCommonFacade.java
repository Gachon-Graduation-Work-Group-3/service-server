package whenyourcar.application.facade.user;

import whenyourcar.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whenyourcar.application.converter.user.UserCommonConverter;
import whenyourcar.application.dto.user.common.UserCommonResponse;
import whenyourcar.application.service.user.UserCommonService;


@Service
@RequiredArgsConstructor
public class UserCommonFacade {
    private final UserCommonService userCommonService;
    private final UserCommonConverter userCommonConverter;
    public UserCommonResponse.UserProfileResponseDto getUserProfile(String email)  {
        User user = userCommonService.getUserByEmail(email);
        return userCommonConverter.toUserProfileResponse(user);
    }
}
