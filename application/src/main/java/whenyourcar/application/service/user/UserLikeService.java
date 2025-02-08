package whenyourcar.application.service.user;

import whenyourcar.domain.entity.Car;
import whenyourcar.domain.entity.User;
import org.springframework.data.domain.Pageable;
import whenyourcar.application.dto.user.like.UserLikeResponse;

public interface UserLikeService {
    public void postUserLike(Car car, User user);
    public UserLikeResponse.UserLikesResponseDto getUserLikes(User user, Pageable pageable);
    public void deleteUserLike(String email, Long userLikeId);
}
