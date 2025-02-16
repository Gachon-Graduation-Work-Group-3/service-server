package whenyourcar.application.dto.user.like;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import whenyourcar.domain.query.car.SearchCarsQuery;

public class UserLikeResponse {
    @Builder
    @Getter
    @AllArgsConstructor
    public static class UserLikesResponseDto{
        Long userId;
        Page<SearchCarsQuery> searchCarsQueries;
    }
}
