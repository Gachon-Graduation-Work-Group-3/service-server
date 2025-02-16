package whenyourcar.application.converter.user;

import whenyourcar.domain.entity.Car;
import whenyourcar.domain.entity.User;
import whenyourcar.domain.entity.UserLike;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import whenyourcar.domain.query.car.SearchCarsQuery;
import whenyourcar.application.dto.user.like.UserLikeResponse;

@Component
public class UserLikeConverter {
    public UserLike toUserLike(Car car, User user){
        return UserLike.builder()
                .car(car)
                .user(user)
                .build();
    }

    public UserLikeResponse.UserLikesResponseDto toUserLikes(Page<SearchCarsQuery> searchCarsQueries, Long userId) {
        return UserLikeResponse.UserLikesResponseDto.builder()
                .userId(userId)
                .searchCarsQueries(searchCarsQueries)
                .build();
    }
}
