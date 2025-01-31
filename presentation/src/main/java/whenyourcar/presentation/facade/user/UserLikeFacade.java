package whenyourcar.presentation.facade.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import whenyourcar.domain.user.dto.security.SessionUser;
import whenyourcar.domain.user.dto.userLike.UserLikeResponse;
import whenyourcar.domain.user.service.UserCommonService;
import whenyourcar.domain.user.service.UserLikeService;


@Service
@RequiredArgsConstructor
public class UserLikeFacade {
    private final UserCommonService userCommonService;
    private final UserLikeService userLikeService;

    public void postUserLike( HttpServletRequest request,
                             Long carId) {
        String email = request.getHeader("X-User-Email");
        SessionUser sessionUser = userCommonService.getSessionUser(email);
        userLikeService.postUserLike(carId, sessionUser.getUserId());
    }

    public UserLikeResponse.UserLikesResponse getUserLikes(HttpServletRequest request, Pageable pageable)  {
        String email = request.getHeader("X-User-Email");
        SessionUser sessionUser = userCommonService.getSessionUser(email);
        return userLikeService.getUserLikes(sessionUser.getUserId(), pageable);
    }

    public void deleteUserLike( HttpServletRequest request, Long userLikeId) {
        String email = request.getHeader("X-User-Email");
        SessionUser sessionUser = userCommonService.getSessionUser(email);
        userLikeService.deleteUserLike(sessionUser.getUserId(), userLikeId);
    }
}
