package whenyourcar.presentation.facade.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import whenyourcar.domain.user.dto.security.SessionUser;
import whenyourcar.domain.user.service.UserCommonService;


@Service
@RequiredArgsConstructor
public class UserCommonFacade {
    private final UserCommonService userCommonService;

    public SessionUser getUserProfile(HttpServletRequest request)  {
        String email = request.getHeader("X-User-Email");
        System.out.println(email);
        return userCommonService.getSessionUser(email);
    }
}
