package whenyourcar.application.facade.user;

import whenyourcar.domain.entity.Car;
import whenyourcar.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import whenyourcar.application.dto.user.like.UserLikeResponse;
import whenyourcar.application.service.car.CarCommonService;
import whenyourcar.application.service.user.UserCommonService;
import whenyourcar.application.service.user.UserLikeService;


@Service
@RequiredArgsConstructor
public class UserLikeFacade {
    private final UserLikeService userLikeService;
    private final UserCommonService userCommonService;
    private final CarCommonService carCommonService;

    public void postUserLike(String email, Long carId) {
        User user = userCommonService.getUserByEmail(email);
        Car car = carCommonService.getCarById(carId);
        userLikeService.postUserLike(car, user);
    }

    public UserLikeResponse.UserLikesResponseDto getUserLikes(String email, Pageable pageable)  {
        User user = userCommonService.getUserByEmail(email);
        return userLikeService.getUserLikes(user, pageable);
    }

    public void deleteUserLike(String email, Long userLikeId) {
        userLikeService.deleteUserLike(email, userLikeId);
    }
}
