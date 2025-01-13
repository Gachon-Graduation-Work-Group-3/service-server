package whenyourcar.presentation.controller.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import whenyourcar.domain.common.code.status.SuccessStatus;
import whenyourcar.domain.user.dto.userLike.UserLikeResponse;
import whenyourcar.presentation.apiPayload.ApiResponse;
import whenyourcar.presentation.facade.user.UserLikeFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserLikeController {
    private final HttpSession httpSession;
    private final UserLikeFacade userLikeFacade;

    @PostMapping("/like")
    public ApiResponse<Void> postUserLike(@RequestParam Long carId) {
        userLikeFacade.postUserLike(httpSession, carId);
        return ApiResponse.onSuccess(SuccessStatus.USER_LIKE_SUCCESS, null);
    }

    @GetMapping("/like")
    public ApiResponse<UserLikeResponse.UserLikesResponse> getUserLike(
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        return ApiResponse.onSuccess(SuccessStatus.USER_SEARCH_LIKE, userLikeFacade.getUserLikes(httpSession, PageRequest.of(page, size)));
    }

    @DeleteMapping("/like")
    public ApiResponse<Void> deleteLike(@RequestParam Long userLikeId) {
        userLikeFacade.deleteUserLike(httpSession, userLikeId);
        return ApiResponse.onSuccess(SuccessStatus.USER_DELETE_LIKE, null);
    }
}
