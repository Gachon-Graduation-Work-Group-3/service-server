package whenyourcar.domain.user.dto.userLike;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import whenyourcar.storage.mysql.data.query.SearchCarsQuery;

public class UserLikeResponse {
    @Builder
    @Getter
    @AllArgsConstructor
    public static class UserLikesResponse{
        Long userId;
        Page<SearchCarsQuery> searchCarsQueries;
    }
}
