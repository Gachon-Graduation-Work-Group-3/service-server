package whenyourcar.api.web.controller.user;

import whenyourcar.common.annotation.EmailParam;
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
    public ApiResponse<Void> postUserLike(@EmailParam String email,
                                          @RequestParam Long carId)  {
        userLikeFacade.postUserLike(email, carId);
        return ApiResponse.onSuccess(SuccessStatus.USER_LIKE_SUCCESS, null);
    }

    @GetMapping("/like")
    public ApiResponse<UserLikeResponse.UserLikesResponseDto> getUserLike(
            @EmailParam String email,
            @RequestParam Integer page,
            @RequestParam Integer size
    )  {
        return ApiResponse.onSuccess(SuccessStatus.USER_SEARCH_LIKE, userLikeFacade.getUserLikes(email, PageRequest.of(page, size)));
    }

    @DeleteMapping("/like")
    public ApiResponse<Void> deleteLike(@EmailParam String email,
                                        @RequestParam Long userLikeId) {
        userLikeFacade.deleteUserLike(email, userLikeId);
        return ApiResponse.onSuccess(SuccessStatus.USER_DELETE_LIKE, null);
    }
}
