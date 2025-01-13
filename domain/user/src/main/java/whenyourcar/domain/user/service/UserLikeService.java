package whenyourcar.domain.user.service;

import org.springframework.data.domain.Pageable;
import whenyourcar.domain.user.dto.userLike.UserLikeResponse;

public interface UserLikeService {
    public void postUserLike(Long carId, Long userId);
    public UserLikeResponse.UserLikesResponse getUserLikes(Long userId, Pageable pageable);
    public void deleteUserLike(Long userId, Long userLikeId);
}
