package whenyourcar.domain.user.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whenyourcar.domain.common.code.exception.AuthenticationException;
import whenyourcar.domain.common.code.status.ErrorStatus;
import whenyourcar.domain.user.dto.security.SessionUser;
import whenyourcar.domain.user.service.UserCommonService;
import whenyourcar.storage.mysql.data.entity.User;
import whenyourcar.storage.mysql.repository.user.UserCommonRepository;

@Service
@RequiredArgsConstructor
public class UserCommonServiceImpl implements UserCommonService {
    private final UserCommonRepository userCommonRepository;
    @Override
    public SessionUser getSessionUser(String email)  {
        User user =  userCommonRepository.findByEmail(email)
                .orElseThrow(() -> new AuthenticationException(ErrorStatus.USER_IS_NOT_EXIST));
        return SessionUser.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .picture(user.getPicture())
                .build();
    }

    @Override
    public User getUser(HttpSession httpSession) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        return userCommonRepository.findUserById(user.getUserId())
                .orElseThrow(() -> new AuthenticationException(ErrorStatus.USER_IS_NOT_EXIST));
    }
}
