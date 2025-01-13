package whenyourcar.presentation.facade.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whenyourcar.domain.user.dto.security.SessionUser;
import whenyourcar.domain.user.service.UserCommonService;


@Service
@RequiredArgsConstructor
public class UserCommonFacade {
    private final UserCommonService userCommonService;

    public SessionUser getUserProfile(HttpSession httpSession) {
        return userCommonService.getSessionUser(httpSession);
    }
}
