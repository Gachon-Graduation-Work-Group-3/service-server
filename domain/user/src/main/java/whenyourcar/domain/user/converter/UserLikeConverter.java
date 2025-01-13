package whenyourcar.domain.user.converter;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import whenyourcar.domain.user.dto.userLike.UserLikeResponse;
import whenyourcar.storage.mysql.data.entity.Car;
import whenyourcar.storage.mysql.data.entity.User;
import whenyourcar.storage.mysql.data.mapping.UserLike;
import whenyourcar.storage.mysql.data.query.SearchCarsQuery;

@Component
public class UserLikeConverter {
    public UserLike toUserLike(Car car, User user){
        return UserLike.builder()
                .car(car)
                .user(user)
                .build();
    }

    public UserLikeResponse.UserLikesResponse toUserLikes(Page<SearchCarsQuery> searchCarsQueries, Long userId) {
        return UserLikeResponse.UserLikesResponse.builder()
                .userId(userId)
                .searchCarsQueries(searchCarsQueries)
                .build();
    }
}
