package whenyourcar.presentation.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
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
    private final UserLikeFacade userLikeFacade;

    @PostMapping("/like")
    public ApiResponse<Void> postUserLike(HttpServletRequest request,
                                          @RequestParam Long carId)  {
        userLikeFacade.postUserLike(request, carId);
        return ApiResponse.onSuccess(SuccessStatus.USER_LIKE_SUCCESS, null);
    }

    @GetMapping("/like")
    public ApiResponse<UserLikeResponse.UserLikesResponse> getUserLike(
            HttpServletRequest request,
            @RequestParam Integer page,
            @RequestParam Integer size
    )  {
        return ApiResponse.onSuccess(SuccessStatus.USER_SEARCH_LIKE, userLikeFacade.getUserLikes(request, PageRequest.of(page, size)));
    }

    @DeleteMapping("/like")
    public ApiResponse<Void> deleteLike(HttpServletRequest request,
                                        @RequestParam Long userLikeId) {
        userLikeFacade.deleteUserLike(request, userLikeId);
        return ApiResponse.onSuccess(SuccessStatus.USER_DELETE_LIKE, null);
    }
}
