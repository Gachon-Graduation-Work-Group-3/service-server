package whenyourcar.presentation.facade.user;

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

    public void postUserLike(HttpSession httpSession,
                             Long carId) {
        SessionUser sessionUser = userCommonService.getSessionUser(httpSession);
        userLikeService.postUserLike(carId, sessionUser.getUserId());
    }

    public UserLikeResponse.UserLikesResponse getUserLikes(HttpSession httpSession, Pageable pageable) {
        SessionUser sessionUser = userCommonService.getSessionUser(httpSession);
        return userLikeService.getUserLikes(sessionUser.getUserId(), pageable);
    }

    public void deleteUserLike(HttpSession httpSession, Long userLikeId) {
        SessionUser sessionUser = userCommonService.getSessionUser(httpSession);
        userLikeService.deleteUserLike(sessionUser.getUserId(), userLikeId);
    }
}
