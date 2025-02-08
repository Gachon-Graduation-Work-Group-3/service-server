package whenyourcar.api.web.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import whenyourcar.common.code.status.SuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import whenyourcar.api.apiPayload.ApiResponse;
import whenyourcar.application.dto.user.like.UserLikeResponse;
import whenyourcar.application.facade.user.UserLikeFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserLikeController {
    private final UserLikeFacade userLikeFacade;

    @PostMapping("/like")
    public ApiResponse<Void> postUserLike(HttpServletRequest httpServletRequest,
                                          @RequestParam Long carId)  {
        String email = httpServletRequest.getHeader("X-User-Email");
        userLikeFacade.postUserLike(email, carId);
        return ApiResponse.onSuccess(SuccessStatus.USER_LIKE_SUCCESS, null);
    }

    @GetMapping("/like")
    public ApiResponse<UserLikeResponse.UserLikesResponseDto> getUserLike(
            HttpServletRequest httpServletRequest,
            @RequestParam Integer page,
            @RequestParam Integer size
    )  {
        String email = httpServletRequest.getHeader("X-User-Email");
        return ApiResponse.onSuccess(SuccessStatus.USER_SEARCH_LIKE, userLikeFacade.getUserLikes(email, PageRequest.of(page, size)));
    }

    @DeleteMapping("/like")
    public ApiResponse<Void> deleteLike(HttpServletRequest httpServletRequest,
                                        @RequestParam Long userLikeId) {
        String email = httpServletRequest.getHeader("X-User-Email");
        userLikeFacade.deleteUserLike(email, userLikeId);
        return ApiResponse.onSuccess(SuccessStatus.USER_DELETE_LIKE, null);
    }
}
