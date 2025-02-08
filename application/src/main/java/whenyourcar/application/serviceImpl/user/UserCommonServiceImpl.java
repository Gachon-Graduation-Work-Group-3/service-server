package whenyourcar.application.serviceImpl.user;

import whenyourcar.common.code.exception.AuthenticationException;
import whenyourcar.common.code.status.ErrorStatus;
import whenyourcar.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whenyourcar.domain.repository.user.UserCommonRepository;
import whenyourcar.application.converter.user.UserCommonConverter;
import whenyourcar.application.service.user.UserCommonService;

@Service
@RequiredArgsConstructor
public class UserCommonServiceImpl implements UserCommonService {
    private final UserCommonRepository userCommonRepository;
    private final UserCommonConverter userCommonConverter;
    @Override
    public User getUserByEmail(String email)  {
        User user =  userCommonRepository.findByEmail(email)
                .orElseThrow(() -> new AuthenticationException(ErrorStatus.USER_IS_NOT_EXIST));
        return user;
    }
}
